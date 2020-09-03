package com.gz.sample.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 反射包装器
 *
 * @author Liguiqing
 * @since V1.0
 */
public class ReflectionWrapper {

    private final static Logger log = LoggerFactory.getLogger(ReflectionWrapper.class);

    private ReflectionWrapper() {
        throw new AssertionError("No com.gz.jiebao.lang.ReflectionWrapper instances for you!");
    }
    /**
     * 通过Class 及 constructor 参数 实例化一个对象
     * @param cls of Constructor
     * @param parameterTypes {@link Class} of method parameter types
     * @param <T> return type
     * @return new instance of T
     * @throws
     */
    public static <T> T newInstanceOf(Class cls,Object... parameterTypes){
        if(Objects.isNull(cls)){
            return null;
        }

        try {
            if(Objects.isNull(parameterTypes)||parameterTypes.length == 0){
                Constructor c0=  cls.getDeclaredConstructor();
                c0.setAccessible(true);
                return (T) c0.newInstance();
            }

            Class[] clses = new Class[parameterTypes.length];
            int i = 0;
            for(Object o:parameterTypes){
                clses[i++] = o.getClass();
            }
            return (T)cls.getDeclaredConstructor(clses).newInstance(parameterTypes);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error(ThrowableToString.toString(e));
            throw new BusinessException(1000101,String.format("实例化%s错误",cls.getName()));
        }
    }
}
