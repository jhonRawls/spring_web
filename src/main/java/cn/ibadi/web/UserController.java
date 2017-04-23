
package cn.ibadi.web;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.ibadi.constant.ResultCode;
import cn.ibadi.utils.QrcodeGenerate;
import cn.ibadi.vo.ResultVo;
import cn.ibadi.vo.UserVo;



@Controller
@RequestMapping("/user")
public class UserController {

	Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value = "login", method = { RequestMethod.GET })
	public String toLogin(ModelMap model,HttpServletRequest  request) {
		logger.debug("调用方法------");
		
		HttpSession session = request.getSession();
		session.setAttribute("data", "dsds");
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
	
	@RequestMapping(value = "outPutQrcodeImage", method = { RequestMethod.GET })
	public void generateImg(HttpServletResponse response){
			
		try {
			response.setContentType("image/png;");
			int width=400;
			int height=400;
			String imageUrl="http://www.baidu.com";
			QrcodeGenerate.createRqCode(imageUrl, width, height, response.getOutputStream());
		} catch (Exception e) {
			logger.error("生成二维码失败");
		}
	}

	
	@RequestMapping(value = "outPutExcel", method = { RequestMethod.GET })
	public String outPutExcel(ModelMap model) {
		
		return null;
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
	
	/**ModelAndView即为 mode+view的合体
	 * setViewName，可以映射地址对应逻辑真实视图，prefix前缀+视图名称+后缀
	 * @return
	 */
	@RequestMapping(value="logout",method={RequestMethod.GET})
	public ModelAndView logout(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("logout");
		mav.addObject("message","hello user");
		return mav;
	}
	
	/**
	 * 没有设置名称 直接映射 /user/show.html
	 * map可以直接在view中取值
	 * @return
	 */
	@RequestMapping("show")
	public Map<String, String> getMap(){
		Map<String, String> map=new HashMap<String, String>();
		map.put("key1","value-1");
		map.put("key2","value-2");
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "json_view", method = { RequestMethod.POST,RequestMethod.GET },produces = "application/json")
	public ModelAndView json(){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("json/json_view");
		List<UserVo> userVos=new ArrayList<UserVo>();
		for(int i=0;i<5;i++){
			UserVo userVo=new UserVo();
			userVo.setName("123:"+i);
			userVo.setPwd("pwd_123"+i);
			userVos.add(userVo);
		}
		mav.addObject("userList",userVos);
		return mav;
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
