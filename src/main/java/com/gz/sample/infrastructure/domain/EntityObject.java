package com.gz.sample.infrastructure.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * 实体对象
 * @param <T> type of EntityObject id
 * @author Liguiqing
 * @since V1.0
 */
public interface EntityObject<T extends Serializable> extends Serializable {

    /***
     * 获取数据库主键
     * @return
     */
    T getId();

    /***
     * 设置数据库主键
     * @param id
     */
    void setId(T id);

    default boolean fieldsNotNull(Object... fields) {
        for (Object o : fields) {
            if (Objects.isNull(o)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
