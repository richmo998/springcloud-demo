package com.banling.sc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@PropertySource("classpath:others.properties")
public class VdServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VdServiceDemoApplication.class, args);
	}
}




