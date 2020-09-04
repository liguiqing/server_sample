package com.gz.sample.web.rest;

import com.gz.sample.SampleApp;
import com.gz.sample.domain.Sample;
import com.gz.sample.domain.User;
import com.gz.sample.repository.SampleRepository;
import com.gz.sample.repository.UserRepository;
import com.gz.sample.security.AuthoritiesConstants;
import com.gz.sample.service.dto.SampleChildDTO;
import com.gz.sample.service.dto.SampleDTO;
import com.gz.sample.service.mapper.SampleMapper;
import org.apache.commons.compress.utils.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@WithMockUser(authorities = {AuthoritiesConstants.ADMIN,AuthoritiesConstants.USER})
@SpringBootTest(classes = SampleApp.class)
class SampleResourceIT {

    @Autowired
    private SampleRepository sampleRepository;

    @Autowired
    private SampleMapper sampleMapper;

    @Autowired
    private MockMvc restApiMockMvc;

    private User user;

    @Autowired
    private CacheManager cacheManager;
    @BeforeEach
    public void setup() {
        // you can init something here
        this.user = new User();
        user.setId(1000L);
    }

    @Test
    @Transactional
    void createUser() throws Exception{
        var databaseSizeBeforeCreate = this.sampleRepository.findAll().size();
        var sampleDto = createSampleDTO();
        restApiMockMvc.perform(post("/api/samples")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(createSampleDTO())))
            .andExpect(status().isCreated());

        // Validate the Sample in the database
        assertPersistedSamples(samples -> {
            assertThat(samples).hasSize(databaseSizeBeforeCreate + 1);
            var testSample = samples.get(samples.size() - 1);
            assertThat(testSample.getTitle()).isEqualTo(sampleDto.getTitle());
        });
    }

    @Test
    void getAllSamples() throws Exception{
        var sampleDto = createSampleDTO();
        var sample = sampleMapper.toEntity(sampleDto);
        sample.user(this.user);
        sampleRepository.saveAndFlush(sample);

        // Get all the samples
        restApiMockMvc.perform(get("/api/samples?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].title").value(hasItem(sampleDto.getTitle())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(sampleDto.getActive())));
    }

    private SampleDTO createSampleDTO(){
        List<SampleChildDTO> children = Lists.newArrayList();
        children.add(new SampleChildDTO("N5", ZonedDateTime.now().minusWeeks(5),Boolean.TRUE));
        children.add(new SampleChildDTO("N2", ZonedDateTime.now().minusWeeks(2),Boolean.TRUE));
        children.add(new SampleChildDTO("N1", ZonedDateTime.now().minusWeeks(1),Boolean.TRUE));
        children.add(new SampleChildDTO("N10", ZonedDateTime.now().minusWeeks(10),Boolean.FALSE));
        var sampleDto = new SampleDTO();
        sampleDto.setTitle("T1");
        sampleDto.setDeadline(LocalDate.now());
        sampleDto.setActive(Boolean.TRUE);
        sampleDto.setChildren(children);
        return sampleDto;
    }

    private void assertPersistedSamples(Consumer<List<Sample>> sampleAssertion) {
        sampleAssertion.accept(sampleRepository.findAll());
    }
}
