package com.banling.sc.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@EnableFeignClients
@ComponentScan(value = "com.banling.sc.*")
public class ScZKRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScZKRegisterApplication.class,args);
	}

}
