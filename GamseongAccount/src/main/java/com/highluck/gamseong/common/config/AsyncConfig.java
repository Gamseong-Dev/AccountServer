package com.highluck.gamseong.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableWebMvc
@Configuration
public class AsyncConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub

	    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setCorePoolSize(10);
	    taskExecutor.setMaxPoolSize(20);
	    taskExecutor.setQueueCapacity(50);
	    taskExecutor.initialize();
	    configurer.setTaskExecutor(taskExecutor);
		
	}
}