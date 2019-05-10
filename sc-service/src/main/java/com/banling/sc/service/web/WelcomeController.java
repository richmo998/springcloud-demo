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
	private String message;//如果没有找到Key:application.message，那么就用"Hello World"代替?

	/**视图转发<br>
	 * 视图只单纯是文本，没有其它多媒体；否则，通过Zuul转发请求时，会找不到。<br>
	 * <b>
	 * 特别说明，不建议通过Zuul来转发视图。这里仅仅是做个测试。<br>
	 * 可以利用这个特性来实现视图的代码片段，让前端请求解释展示。
	 * </b>
	 * @param model
	 * @return
	 */
	@GetMapping("/welcome")
	public String welcome(Map<String, Object> model) {
		logger.info("********* welcome **********");
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
