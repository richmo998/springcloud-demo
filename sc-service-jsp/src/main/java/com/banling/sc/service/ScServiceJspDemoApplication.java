package com.banling.sc.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableEurekaClient
@PropertySource("classpath:others.properties")
public class ScServiceJspDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScServiceJspDemoApplication.class, args);
	}
}
