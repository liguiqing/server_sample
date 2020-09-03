/**
 * <p><b>© 2020 深圳市国智共通科技有限公司</b></p>
 **/
package com.gz.sample.infrastructure.repository.hibernate;

import com.gz.sample.infrastructure.domain.id.Identities;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * id generator
 *
 * @author liguiqing created at 2020-09-02
 * @since V1.0.0
 **/
public class MyIdGenerator implements Configurable, IdentifierGenerator {
    @Override
    public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {

    }

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return Identities.genEntityId();
    }
}
