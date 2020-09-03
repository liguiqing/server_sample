package com.gz.sample.domain;

import java.time.Instant;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends com.gz.sample.infrastructure.domain.AbstractAuditingEntity_ {

	public static volatile SingularAttribute<User, Instant> resetDate;
	public static volatile SingularAttribute<User, Long> mobile;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, String> activationKey;
	public static volatile SingularAttribute<User, String> uuid;
	public static volatile SingularAttribute<User, String> resetKey;
	public static volatile SetAttribute<User, Authority> authorities;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> langKey;
	public static volatile SingularAttribute<User, String> imageUrl;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, Boolean> activated;

	public static final String RESET_DATE = "resetDate";
	public static final String MOBILE = "mobile";
	public static final String LOGIN = "login";
	public static final String ACTIVATION_KEY = "activationKey";
	public static final String UUID = "uuid";
	public static final String RESET_KEY = "resetKey";
	public static final String AUTHORITIES = "authorities";
	public static final String PASSWORD = "password";
	public static final String LANG_KEY = "langKey";
	public static final String IMAGE_URL = "imageUrl";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String ACTIVATED = "activated";

}

