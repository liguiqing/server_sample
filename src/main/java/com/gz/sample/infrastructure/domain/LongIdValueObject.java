package com.gz.sample.infrastructure.domain;

import javax.persistence.MappedSuperclass;

/**
 * 值对象
 * @author liguiqing
 */
@MappedSuperclass
public abstract class LongIdValueObject extends LongIdEntityObject implements ValueObject<Long> {
}
