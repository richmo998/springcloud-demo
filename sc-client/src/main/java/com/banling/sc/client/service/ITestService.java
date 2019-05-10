package com.banling.sc.client.service;

import java.util.Collection;
import java.util.List;

import com.banling.sc.module.User;

/**客户端消费服务的接口
 * @author Ban
 *
 */
public interface ITestService {
	String getMsg(String msg);
	
	List<User> getUserList(Integer count);
	
	Collection<String> getServerMetadata();
	
	public String noRestApi();
	
	byte[] downloadFile(String fileid);
}
