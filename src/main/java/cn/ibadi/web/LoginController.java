package cn.ibadi.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value = "welcome", method = { RequestMethod.GET })
	public String doWelcome(ModelMap model) {
		model.put("user", "testname");
		return "welcome";
	}
}
