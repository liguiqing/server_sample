package com.gz.sample.service.mapper;

import com.gz.sample.infrastructure.domain.EntityObject;
import com.gz.sample.lang.ReflectionWrapper;

import java.io.Serializable;
import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper <K extends Serializable,D, E extends EntityObject<K>> {

    E toEntity(D dto);

    D toDto(E entity);

    List <E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);

    default E fromId(K id) {
        if (id == null) {
            return null;
        }
        E e = ReflectionWrapper.newInstanceOf(getEntityClass());
        e.setId(id);
        return e;
    }

    default Class<E> getEntityClass(){
        return (Class<E>) this.getClass();
    }
}
