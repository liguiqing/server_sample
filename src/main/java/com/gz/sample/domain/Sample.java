/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.domain;

import com.google.common.base.Objects;
import com.gz.sample.infrastructure.domain.LongIdEntityObject;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * sample
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
@Entity
@Table(name = "sample")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sample extends LongIdEntityObject {

    @Column(name="title")
    private String title;

    @Column(name="deadline")
    private LocalDate deadline;

    @Column(name="is_active")
    private Boolean active;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    @JoinColumn(name="sample_id")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<SampleChild> children = new ArrayList<>();

    public Sample() {
    }

    public Sample(User user){
        this.user = user;
    }

    public Sample title(String title){
        this.title = title;
        return this;
    }

    public Sample user(User user){
        this.user = user;
        return this;
    }

    public Sample child(SampleChild child) {
        this.children.add(child);
        return this;
    }

    public Sample deadline(LocalDate deadline){
        this.deadline = deadline;
        return this;
    }

    public Sample active(Boolean active){
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
        if (!super.equals(o)) {
            return false;
        }
        Sample sample = (Sample) o;
        return  fieldsNotNull(title) && Objects.equal(title, sample.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SampleChild> getChildren() {
        return children;
    }

    public void setChildren(List<SampleChild> children) {
        this.children = children;
    }
}
