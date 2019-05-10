package com.banling.sc.service.web;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**非rest接口测试
 * @author Ban
 *
 */
@Controller
public class WelcomeController {
	
	private final Logger logger=Logger.getLogger(this.getClass());

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@GetMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		logger.info("welcome");
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}
 
	@RequestMapping(value="/test",method=RequestMethod.POST)
	public String test(Map<String, Object> model) {
		logger.info("test");
		throw new RuntimeException("test");
	}

}
