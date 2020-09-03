package com.gz.sample.infrastructure.domain;

import java.io.Serializable;

/**
 * 值对象
 *
 * @author Liguiqing
 * @since V1.0
 */
public interface ValueObject<T extends Serializable> extends EntityObject<T> {
}
