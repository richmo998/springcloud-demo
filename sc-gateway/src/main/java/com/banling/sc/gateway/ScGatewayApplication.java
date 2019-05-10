package com.banling.sc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.banling.sc.gateway.filter.UserFilter;

/**
 * 注意：
 *    在使用网关转发服务时候，get请求没有参数的，结尾一定要是/，否则会一直请求不通过
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ScGatewayApplication {
	
	@Bean
	public UserFilter userFilter(){
		return new UserFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ScGatewayApplication.class, args);
	}
}
