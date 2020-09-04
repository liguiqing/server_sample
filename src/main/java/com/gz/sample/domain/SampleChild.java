/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.domain;

import com.google.common.base.Objects;
import com.gz.sample.infrastructure.domain.LongIdValueObject;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 *
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
@Entity
@Table(name = "sample_children")
public class SampleChild extends LongIdValueObject {
    @Column(name="name")
    private String name;

    @Column(name="joined_time")
    private ZonedDateTime joinedTime;

    @Column(name="is_alive")
    private Boolean active;

    public SampleChild name(String name){
        this.name = name;
        return this;
    }

    public  SampleChild joinedTime(ZonedDateTime joinedTime){
        this.joinedTime = joinedTime;
        return this;
    }

    public SampleChild active(Boolean active){
        this.active = active;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SampleChild that = (SampleChild) o;
        return super.equals(that) && Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
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
}
