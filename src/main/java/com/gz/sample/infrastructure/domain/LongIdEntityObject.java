package com.gz.sample.infrastructure.domain;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * 以Long为Id实体类
 * @author liguiqing
 */
@MappedSuperclass
public abstract class LongIdEntityObject implements EntityObject<Long> {
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "com.gz.sample.infrastructure.repository.hibernate.MyIdGenerator")
    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        LongIdEntityObject that = (LongIdEntityObject) o;

        //id相同的时候，entityId必须相同
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
