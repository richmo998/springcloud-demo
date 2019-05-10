package com.banling.sctrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer //启动ZipkinServer
public class StartApp {
	public static void main(String[] args) {
		SpringApplication.run(StartApp.class, args);
	}
	
//	@Bean
//    public MySQLStorage mySQLStorage(DataSource datasource) {
//        return MySQLStorage.builder().datasource(datasource).executor(Runnable::run).build();
//    }
}
