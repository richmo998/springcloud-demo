package com.banling.sc.zk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Registration;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {


    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private Environment env;

//    @Autowired
//    private AppClient appClient;

    @Autowired(required = false)
    private Registration registration;

    @Autowired
    private RestTemplate rest;

    @RequestMapping("/")
    public ServiceInstance lb() {
        System.out.println("this.appName="+this.appName);
        return this.loadBalancer.choose(this.appName);
    }

    @GetMapping(value = "/test")
    public String myService(){
        return "hello spring  cloud";
    }


    @RequestMapping("/hi")
    public String hi() {
        return "Hello World! from " + this.registration;
    }

//    @RequestMapping("/self")
//    public String self() {
//        return this.appClient.hi();
//    }

    @RequestMapping("/myenv")
    public String env(@RequestParam("prop") String prop) {
        return this.env.getProperty(prop, "Not Found");
    }

    public String rt() {
        return this.rest.getForObject("http://" + this.appName + "/hi", String.class);
    }

    @Bean
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }


//    @FeignClient("testZookeeperApp")
//    interface AppClient {
//
//        @RequestMapping(path = "/hi", method = RequestMethod.GET)
//        String hi();
//


    @GetMapping("/services")
    public List<String> serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances(appName);
        List<String> services = new ArrayList<>();
        if (list != null && list.size() > 0 ) {
            list.forEach(serviceInstance -> {
                services.add(serviceInstance.getUri().toString());
            });
        }
        return services;
    }


}
