package com.gz.sample.lang;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

import static com.gz.sample.web.rest.errors.ErrorConstants.UNKNOWN_TYPE;

/**
 * <p>
 * 业务异常超类，所有业务处理过程中产生的异常都为此类的子类
 * </P>
 *
 * @author liguiqing
 * @since V1.0.0
 **/
public class BusinessException extends AbstractThrowableProblem {

    public BusinessException() {
       this(100000,"未知异常");
    }

    public BusinessException(int code, String message) {
        super(UNKNOWN_TYPE, message, new StatusType() {
            @Override
            public int getStatusCode() {
                return code;
            }

            @Override
            public String getReasonPhrase() {
                return message;
            }
        });
    }


    @Override
    public String getMessage(){
        return String.format("%s",super.getMessage());
    }
}
