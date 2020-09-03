package com.gz.sample.infrastructure.repository;

import com.gz.sample.infrastructure.domain.EntityObject;
import com.gz.sample.infrastructure.domain.id.Identities;
import com.gz.sample.lang.ReflectionWrapper;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * 实体类持久化
 *
 * @param <E>  type of entity
 * @param <ID> type of entityId
 * @author liguiqing
 * @since v1.0.0
 */
public interface EntityRepository< E extends EntityObject<ID>, ID extends Serializable> {

    /**
     * 获取E的Class
     * 注：子类如果是通过动态代理进行实例化时，无法获取泛型参数，必须重写此方法。
     *
     * @return the {@link Class} of E
     */
    default Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    /**
     * 读取entityId
     *
     * @return a new Instance of type ID
     */
    default ID nextId() {
        return genEntityId(this.getEntityClass());
    }

    /**
     * 生成实体ID
     *
     * @param cls class type of Entity
     * @return a new Instance of type ID
     */
    default ID genEntityId(Class<? extends EntityObject<ID>> cls) {
        return Identities.genEntityId(cls);
    }

    /**
     * 创建一个E
     *
     * @return new instance of E with entityId
     */
    default E newEntity() {
        return newEntity(getEntityClass());
    }

    /**
     * 创建一个entityId有值实体对象T,
     *
     * @param cls class type of Entity
     * @param <K> type of entityId
     * @param <T> type of entity
     * @return new instance of E with entityId
     */
    default <K extends ID, T extends EntityObject<K>> T newEntity(Class<T> cls) {
        T t = ReflectionWrapper.newInstanceOf(cls);
        K k = Identities.genEntityId(cls);
        return t;
    }
}
