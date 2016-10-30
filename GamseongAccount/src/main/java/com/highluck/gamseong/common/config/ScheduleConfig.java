package com.highluck.gamseong.common.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

@Configuration
public class ScheduleConfig {

	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorService() {
		
		ScheduledExecutorFactoryBean scheduledExecutorBean = new ScheduledExecutorFactoryBean();
        scheduledExecutorBean.setPoolSize(5);

        return scheduledExecutorBean;
    }
}
