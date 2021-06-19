package com.trax.europeangateway.model.dto;

import javax.validation.constraints.Positive;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@ConfigurationProperties(prefix="threadpool")
@Component
@Data
@Validated
public class ThreadPoolProperties {
    @Positive
    private Integer maxPoolSize;

    @Positive
    private Integer corePoolSize;

    @Positive
    private Integer queueCapacity;
}