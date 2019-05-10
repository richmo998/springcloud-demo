package com.banling.sc.client.service.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.client.RestTemplate;

import com.banling.sc.client.service.ITestService;
import com.banling.sc.module.User;

public class TestService implements ITestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired  
    private LoadBalancerClient loadBalancerClient;

	@Override
	public String getMsg(String msg) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("msg", msg);
		return restTemplate.getForObject("http://sc-service/sc-service/hello/getMsg?msg={msg}", String.class,params);
	}

	@Override
	public List<User> getUserList(Integer count) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("count", count);		
		return restTemplate.getForObject("http://sc-service/sc-service/hello/userList/{count}",List.class,params);
	}

	@Override
	public Collection<String> getServerMetadata() {
		// TODO Auto-generated method stub
		// 获得调用服务的信息,并打印出来
		ServiceInstance serviceInstance = this.loadBalancerClient.choose("sc-service");
		System.out.println("打印服务信息！！！");
		
		System.out.println("serviceInstance.getServiceId():"+serviceInstance.getServiceId());
		System.out.println("serviceInstance.getHost():"+serviceInstance.getHost());
		System.out.println("serviceInstance.getPort():"+serviceInstance.getPort());
		System.out.println("serviceInstance.getUri().getPath():"+serviceInstance.getUri().getPath());
		
		System.out.println("serviceInstance.getUri().toString:"+serviceInstance.getUri().toString());
		System.out.println("serviceInstance.getUri().getScheme:"+serviceInstance.getUri().getScheme());
		System.out.println("serviceInstance.getUri().getSchemeSpecificPart:"+serviceInstance.getUri().getSchemeSpecificPart());
		System.out.println("serviceInstance.getUri().getRawSchemeSpecificPart:"+serviceInstance.getUri().getRawSchemeSpecificPart());
		System.out.println("serviceInstance.getUri().getQuery:"+serviceInstance.getUri().getQuery());
		System.out.println("serviceInstance.getUri().getRawQuery:"+serviceInstance.getUri().getRawQuery());
		System.out.println("serviceInstance.getUri().getAuthority:"+serviceInstance.getUri().getAuthority());
		System.out.println("serviceInstance.getUri().getRawAuthority:"+serviceInstance.getUri().getRawAuthority());
		System.out.println("serviceInstance.getUri().getUserInfo:"+serviceInstance.getUri().getUserInfo());
		System.out.println("serviceInstance.getUri().getRawUserInfo:"+serviceInstance.getUri().getRawUserInfo());
		
		return serviceInstance.getMetadata().values();
	}

	@Override
	public String noRestApi() {
		// TODO Auto-generated method stub
		return restTemplate.getForObject("http://sc-service/sc-service/welcome", String.class);
	}

	@Override
	public byte[] downloadFile(String fileid) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("fileid", fileid);
        byte[] buffer=null;
        
        buffer=restTemplate.getForObject("http://sc-service/sc-service/hello/download/{fileid}", byte[].class,params);
		return buffer;
	}

}
