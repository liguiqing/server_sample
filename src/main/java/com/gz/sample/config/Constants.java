package com.gz.sample.config;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static final String MOBILE_REGEX = "^1[3|4|5|7|8|9][0-9]\\d{8}$";
    public static final String EMAIL_REGEX = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "en";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_TIME_ZNOE = "Asia/Shanghai";
    public static final String ISO_ZONED_DATETIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.nnnnnn'Z'";
    private Constants() {
    }
}
