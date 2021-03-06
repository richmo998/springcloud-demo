package com.banling.sc.register;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableConfigurationProperties(com.banling.sc.register.model.DruidConfig.class)
@EnableDiscoveryClient
@RefreshScope
//@MapperScan("com.banling.sc.register.mapper.*")
public class ScZKRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScZKRegisterApplication.class,args);
	}

}
