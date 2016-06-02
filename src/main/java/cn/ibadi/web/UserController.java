package cn.ibadi.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger=Logger.getLogger(UserController.class);
	@RequestMapping(value = "login",method={RequestMethod.GET})
	public String toLogin(ModelMap model) {
		logger.debug("调用方法------");
		 model.addAttribute("spring", "Hello Spring MVC Framework!");
		 try {
			
		} catch (Exception e) {
			logger.info("方法错误:{}",e);
			logger.error("测试logj4j的打印地址");
			// TODO: handle exception
		}
		return "login";
	}
	
	
}
