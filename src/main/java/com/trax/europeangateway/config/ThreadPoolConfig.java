package com.trax.europeangateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.trax.europeangateway.model.dto.ThreadPoolProperties;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

	private ThreadPoolProperties threadPoolProperties;

	@Autowired
	public void setThreadPoolProperties(ThreadPoolProperties threadPoolProperties) {
		this.threadPoolProperties = threadPoolProperties;
	}

	@Bean(name = "europeanGatewayJobExecutor")
	public TaskExecutor specificTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
		executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
		executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
		executor.setThreadNamePrefix("europeanGateway-");
		executor.initialize();
		return executor;
	}
}
