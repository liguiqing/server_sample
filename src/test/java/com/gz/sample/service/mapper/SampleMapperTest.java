package com.gz.sample.service.mapper;

import com.gz.sample.domain.Sample;
import com.gz.sample.domain.SampleChild;
import com.gz.sample.lang.ReflectionWrapper;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SampleMapperTest {
    private SampleMapper mapper;

    @BeforeEach
    public void setUp(){
        this.mapper = new SampleMapperImpl();
        var sampChildMapper = new SampleChildMapperImpl();
        ReflectionWrapper.writeField(this.mapper,"sampleChildMapper",sampChildMapper);
    }

    @Test
    public void test(){
        Long id = 1L;
        assertThat(this.mapper.fromId(id).getId()).isEqualTo(id);
        assertThat(this.mapper.fromId(null)).isNull();
        var date = LocalDate.now();
        var entity = new Sample().title("T").deadline(date).active(Boolean.TRUE)
            .child(new SampleChild().name("n1").joinedTime(ZonedDateTime.now()).active(Boolean.TRUE))
            .child(new SampleChild().name("n2").joinedTime(ZonedDateTime.now()).active(Boolean.TRUE));
        var dto = this.mapper.toDto(entity);
        assertThat(dto.getTitle()).isEqualTo(entity.getTitle());
        var entity2 = this.mapper.toEntity(dto);
        assertThat(entity2).isEqualTo(entity);
        assertThat(entity2.getChildren().size()).isEqualTo(2);
    }
}
