package com.gz.sample.infrastructure.domain.id;

import java.io.Serializable;

/**
 * 唯一标识(非对象持久化时的主键)
 *
 * @author Liguiqing
 * @since V1.0
 */
public interface Identity<T extends Serializable> extends Serializable {

    T getEntityId();

    void setEntityId(T id);
}
