package com.gz.sample.service.mapper;

import com.gz.sample.domain.SampleChild;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SampleChildMapperTest {

    private SampleChildMapper mapper;

    @BeforeEach
    public void setUp(){
        this.mapper = new SampleChildMapperImpl();
    }

    @Test
    public void test(){
        var entity = new SampleChild().name("Name").joinedTime(ZonedDateTime.now()).active(Boolean.TRUE);
        var dto = this.mapper.toDto(entity);
        assertThat(dto.getName()).isEqualTo(entity.getName());
        assertThat(dto.getJoinedTime()).isEqualTo(entity.getJoinedTime());
        assertThat(dto.getActive()).isEqualTo(entity.getActive());
        var entity2 = this.mapper.toEntity(dto);
        assertThat(entity2).isEqualTo(entity);
        assertThat(entity2.getName()).isEqualTo(entity.getName());
        assertThat(entity2.getActive()).isEqualTo(entity.getActive());
        assertThat(entity2.getJoinedTime()).isEqualTo(entity.getJoinedTime());
    }
}
