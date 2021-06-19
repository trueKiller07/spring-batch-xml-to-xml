package com.trax.europeangateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EntityScan(basePackages = { "com.trax.europeangateway.datastore.domain" })
@EnableJpaRepositories("com.trax.europeangateway.datastore.repository")
@EnableTransactionManagement
public class EuropeangatewayApplication {

	public static void main(String[] args) {

		log.info("Starting Europeangateway Application");

		SpringApplication.run(EuropeangatewayApplication.class, args);

		log.info("Started Europeangateway Application");
	}
	
}
