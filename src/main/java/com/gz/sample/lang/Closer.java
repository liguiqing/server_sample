package com.gz.sample.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 资源释放器
 *
 * @author Liguiqing
 * @since V1.0
 */
public class Closer {
    private final static Logger log = LoggerFactory.getLogger(Closer.class);
    private Closer() {
        throw new AssertionError("No com.gz.jiebao.lang.Closer instances for you!");
    }

    public static <T extends AutoCloseable> void close(T t){
        try{
            t.close();
        }catch (Exception e){
            log.error(ThrowableToString.toString(e));
        }
    }
}
