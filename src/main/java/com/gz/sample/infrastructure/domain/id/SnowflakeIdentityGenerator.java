package com.gz.sample.infrastructure.domain.id;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import com.gz.sample.infrastructure.domain.EntityObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/***
 * 基于雪花算法实现的Long类型实体生成器
 *
 * @author chenyiling
 * @since v1.0
 */
public class SnowflakeIdentityGenerator<T extends EntityObject<Long>,C extends Class<T>> implements IdentityGenerator<Long,T,C>{

    private final Logger log = LoggerFactory.getLogger(SnowflakeIdentityGenerator.class);

    private long workerId = 0;

    private Snowflake snowflake;

    @PostConstruct
    void init() {
        try {
            //NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            workerId = 30;
            log.info("当前机器 workerId: {}", workerId);
            snowflake = new Snowflake(workerId, 1,false);
        } catch (Exception e) {
            log.warn("获取机器 ID 失败", e);
            workerId = NetUtil.getLocalhost().hashCode();
            log.info("当前机器 workerId: {}", workerId);
        }
    }

    @Override
    public Long genEntityId() {
        return snowflake.nextId();
    }

    @Override
    public Long genEntityId(C c) {
        return this.genEntityId();
    }
}
