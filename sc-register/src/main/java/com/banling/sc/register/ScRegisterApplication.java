package com.banling.sc.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ScRegisterApplication {

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	private LoadBalancerClient loadBalancer;


	public static void main(String[] args) {
		SpringApplication.run(ScRegisterApplication.class, args);
	}

	@GetMapping(value = "/test")
	public String myService(){
		return "hello spring  cloud";
	}

	@RequestMapping("/")
	public ServiceInstance lb() {
		System.out.println("this.appName="+this.appName);
		return this.loadBalancer.choose(this.appName);
	}

}
