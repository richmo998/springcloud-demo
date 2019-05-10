package com.banling.sc.client;

import com.banling.sc.client.service.ITestService;
import com.banling.sc.client.service.impl.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
//@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.banling.sc.client"})
public class ScClientApplication {
	
	@Bean
    @LoadBalanced //添加负载均衡支持，很简单，只需要在RestTemplate上添加@LoadBalanced注解，那么RestTemplate即具有负载均衡的功能
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
	
	@Bean
	public ITestService testService(){
		return new TestService();
	}

	public static void main(String[] args) {
		SpringApplication.run(ScClientApplication.class, args);
	}
}
