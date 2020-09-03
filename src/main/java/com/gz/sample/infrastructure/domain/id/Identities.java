package com.gz.sample.infrastructure.domain.id;


import com.gz.sample.infrastructure.domain.EntityObject;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 唯一标识工厂
 *
 * @author Liguiqing
 * @since V1.0
 */
public class Identities {

    private static IdentityGenerator generator = new IdentityGenerator<Long, EntityObject<Long>,Class<EntityObject<Long>>>(){
        private AtomicLong singleId = new AtomicLong(1);

        @Override
        public Long genEntityId() {
            return singleId.getAndAdd(1);
        }

        @Override
        public Long genEntityId(Class<EntityObject<Long>> entityClass) {
            return this.genEntityId();
        }
    };

    private Identities(){
        throw new AssertionError("No com.gz.jiebao.domain.model.id.Identities instances for you!");
    }

    public static void setGenerator(IdentityGenerator generator){
        Identities.generator = generator;
    }

    public static <T extends Serializable> T genEntityId(){
        return (T)generator.genEntityId();
    }

    public static <T extends Serializable,C extends Class<? extends EntityObject<T>>> T genEntityId(C c){
        return (T)generator.genEntityId(c);
    }
}
