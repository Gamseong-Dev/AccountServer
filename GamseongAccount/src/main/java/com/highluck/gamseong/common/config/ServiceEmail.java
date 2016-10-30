package com.highluck.gamseong.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.mail")
public class ServiceEmail {

	private String host;
	private int port;
	private String username;
	private String password;

	
}