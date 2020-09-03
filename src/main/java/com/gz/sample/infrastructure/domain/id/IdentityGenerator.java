package com.gz.sample.infrastructure.domain.id;


import com.gz.sample.infrastructure.domain.EntityObject;

import java.io.Serializable;

/**
 * 实体Id生成器
 *
 * @author Liguiqing
 * @since V1.0
 */
public interface IdentityGenerator<R extends Serializable,T extends EntityObject<R>,C extends Class<T>> {

    R genEntityId();

    R genEntityId(C c);
}
