/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.service.dto;

import javax.persistence.Column;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 *
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
public class SampleChildDTO implements Serializable {
    private Long id;
    private String name;
    private ZonedDateTime joinedTime;
    private Boolean active;

    public SampleChildDTO() {
    }

    public SampleChildDTO(String name, ZonedDateTime joinedTime, Boolean active) {
        this.name = name;
        this.joinedTime = joinedTime;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getJoinedTime() {
        return joinedTime;
    }

    public void setJoinedTime(ZonedDateTime joinedTime) {
        this.joinedTime = joinedTime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SampleChildDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", joinedTime=" + joinedTime +
            ", active=" + active +
            '}';
    }
}
