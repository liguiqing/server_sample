/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.service.dto;

import com.google.common.base.Objects;
import com.gz.sample.domain.SampleChild;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * SampleDTO
 *
 * @author liguiqing created at 2020-09-03
 * @since V1.0.0
 **/
public class SampleDTO implements Serializable {
    private Long id;
    private String title;
    private LocalDate deadline;
    private Boolean active;
    private Long userId;
    private List<SampleChildDTO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<SampleChildDTO> getChildren() {
        return children;
    }

    public void setChildren(List<SampleChildDTO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "SampleDTO{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", deadline=" + deadline +
            ", active=" + active +
            ", userId=" + userId +
            ", children=" + children +
            '}';
    }
}
