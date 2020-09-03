/**
 * <p><b>© 2020 深圳市天富康科技有限公司</b></p>
 **/
package com.gz.sample.config;

import com.gz.sample.infrastructure.domain.id.Identities;
import com.gz.sample.infrastructure.domain.id.IdentityGenerator;
import com.gz.sample.infrastructure.domain.id.SnowflakeIdentityGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用程序配置
 *
 * @author liguiqing created at 2020-08-10
 * @since V1.0.0
 **/
@Configuration
public class AppConfiguration {
    @Bean
    IdentityGenerator snowflakeIdentityGenerator() {
        var idGen = new SnowflakeIdentityGenerator();
        Identities.setGenerator(idGen);
        return idGen;
    }

}
