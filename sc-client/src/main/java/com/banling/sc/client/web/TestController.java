package com.banling.sc.client.web;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.banling.sc.client.service.ITestService;
import com.banling.sc.module.User;

import java.util.List;

/**
 * 不使用feign，而是用RestTemplate
 *
 */
@RestController
public class TestController {
	
	@Autowired
	private ITestService testService;
	
	/**简单测试
	 * @param msg
	 * @return
	 */
	@GetMapping("/test/{msg}")
	public String test(@PathVariable String msg){
		return testService.getMsg(msg);
	}
	
	/**代理下载。
	 * <br>
	 * 从Server下载，再提供给消费端下载
	 * @param fileid, String
	 */
	@GetMapping("/download/{fileid}")
	public void download(@PathVariable String fileid,HttpServletResponse response){
		try {

			OutputStream out=new BufferedOutputStream(response.getOutputStream());
			
			response.setContentType("application/force-download");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String("pic.jpg".getBytes("utf-8"), "iso8859-1"));
			
			Map<String,Object> params=new HashMap<String,Object>();
	        params.put("fileid", fileid);
	        byte[] buffer=testService.downloadFile(fileid);
	        
	        out.write(buffer);
	        out.flush();

	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/**代理非RestFull接口
	 * @param msg String
	 * @return String
	 */
	@GetMapping("/norestapi/{msg}")
	public String noRestApi(@PathVariable String msg){
		return testService.noRestApi();
	}
	
	@GetMapping("/userList/{count}")
	public List<User> getUserList(@PathVariable Integer count){
		return testService.getUserList(count);
	}
	
	@GetMapping("getMeta")
	public Collection<String> getMeta(){
		return testService.getServerMetadata();
	}
}
