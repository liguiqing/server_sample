package com.gz.sample.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import static com.gz.sample.config.Constants.DEFAULT_TIME_ZONE;

/**
 * @author liguiqing
 */
@Configuration
public class JacksonConfiguration {


    @Bean
    @Primary
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        return converter;
    }

    @Bean
    @Primary
    MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        ObjectMapper objectMapper = objectMapper();
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter(objectMapper);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        // jackson2HttpMessageConverter.setDefaultCharset(StandardCharsets.UTF_8);
        return jackson2HttpMessageConverter;
    }

    @Bean
    @Primary
    public static ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule());
        objectMapper.registerModule(javaTimeModule());
        objectMapper.registerModule(jdk8TimeModule());
        objectMapper.registerModule(hibernate5Module());
        objectMapper.registerModule(problemModule());
        objectMapper.registerModule(constraintViolationProblemModule());

        objectMapper.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
        objectMapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return objectMapper;
    }

    @Bean
    public static SimpleModule simpleModule() {
        var simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        return simpleModule;
    }

    /**
     * Support for Java date and time API.
     *
     * @return the corresponding Jackson module.
     */
    @Bean
    public static JavaTimeModule javaTimeModule() {
        var javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer(Constants.DATE_TIME_PATTERN));
        javaTimeModule.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer(Constants.DATE_TIME_PATTERN));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(Constants.DATE_TIME_PATTERN));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(Constants.DATE_TIME_PATTERN));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(Constants.DATE_PATTERN));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(Constants.DATE_PATTERN));

        return javaTimeModule;
    }

    @Bean
    public static Jdk8Module jdk8TimeModule() {
        return new Jdk8Module();
    }

    /**
     * Support for Hibernate types in Jackson.
     *
     * @return the corresponding Hibernate to json Module
     */
    @Bean
    public static Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    /**
     * Module for serialization/deserialization of RFC7807 Problem.
     *
     * @return the corresponding Problem Module
     */
    @Bean
    public static ProblemModule problemModule() {
        var problemModule = new ProblemModule();
        problemModule.withStackTraces(false);
        return problemModule;
    }

    /**
     * Module for serialization/deserialization of ConstraintViolationProblem.
     *
     * @return the corresponding Constraint Violation Problem
     */
    @Bean
    public static ConstraintViolationProblemModule constraintViolationProblemModule() {
        return new ConstraintViolationProblemModule();
    }
}
