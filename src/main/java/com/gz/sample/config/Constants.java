package com.gz.sample.config;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Application constants.
 */
public final class Constants {

    /**
     * Regex for acceptable logins
     */
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String MOBILE_REGEX = "^1[3|4|5|7|8|9][0-9]\\d{8}$";
    /**
     *  其他写法 "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
     *          "^([a-zA-Z0-9_]|[\\u4e00-\\u9fa5])+?([\\.-]??([a-zA-Z0-9_]|[\\u4e00-\\u9fa5])+?)*@[a-zA-Z0-9_]([\\.-]?[a-zA-Z0-9_]+)*\\.[a-zA-Z0-9_]{2,8}$"
     *          ^[\\u4e00-\\u9fa5\w]++([\.]?+[\\u4e00-\\u9fa5\w])*@\w++([\.]?+\w)*+\.a$
     */
    public static final String EMAIL_REGEX = "^([\\w\\u4e00-\\u9fa5])++([\\.-]?+[\\w\\u4e00-\\u9fa5]+)*+@\\w++([\\.-]?\\w++)*?(\\.\\w{2,8}?)+$";
    public static final String DEFAULT_LANGUAGE = "zh";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_TIME_ZONE = "Asia/Shanghai";
    public static final String ISO_ZONED_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ISO_ZONED_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.nnnnnn'Z'";

    public static final ZoneId DEFAULT_ZONE_ID = TimeZone.getTimeZone(DEFAULT_TIME_ZONE).toZoneId();
    public static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern(ISO_ZONED_DATE_PATTERN).withZone(DEFAULT_ZONE_ID);
    public static final DateTimeFormatter DATE_TIME_PATTERN = DateTimeFormatter.ofPattern(ISO_ZONED_DATETIME_PATTERN).withZone(DEFAULT_ZONE_ID);
    private Constants() {
        throw new AssertionError("No com.jc.lgp.config.Constants!");
    }
}
