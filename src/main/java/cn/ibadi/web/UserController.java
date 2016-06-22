
package cn.ibadi.web;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.ibadi.constant.ResultCode;
import cn.ibadi.vo.ResultVo;
import cn.ibadi.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public String toLogin(ModelMap model) {
		logger.debug("调用方法------");
		model.addAttribute("spring", "Hello Spring MVC Framework!");
		UserVo userVo=new UserVo();
		userVo.setName("</input><script>alert('dsds')</script>");
		model.put("name", userVo.getName());
		try {
			ResultVo resultVo=new ResultVo();
			resultVo.setResultContent("<script>alert('dsd')</script>");
			resultVo.setResultCode(ResultCode.SUCCESS);
			resultVo.setResultMsg("添加成功!");
			model.put("user", userVo);
		} catch (Exception e) {
			logger.info("方法错误:{}", e);
			logger.error("测试logj4j的打印地址");
			// TODO: handle exception
		}
		
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "login", method = { RequestMethod.POST },produces = "application/json")
	public ResultVo login(@RequestBody UserVo userVo){
		ResultVo resultVo=new ResultVo();
		
		resultVo.setResultContent("<script>alert('dsd')</script>");
		resultVo.setResultCode(ResultCode.SUCCESS);
		resultVo.setResultMsg("添加成功!");
		return resultVo;
	}
	
	
	

	public static void main(String[] args) {
		Random ranGen = new SecureRandom();
		byte[] aesKey = new byte[20];
		ranGen.nextBytes(aesKey);
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < aesKey.length; i++) {
			String hex = Integer.toHexString(0xff & aesKey[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		System.out.println(hexString);
	}

}
