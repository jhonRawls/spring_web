package cn.ibadi.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;

import cn.ibadi.utils.DateUtil;

@Controller
@RequestMapping("/alipay")
public class PayController {
	
	String URL = "",
			APP_ID = "",
			APP_PRIVATE_KEY = "",
			FORMAT = "json", CHARSET = "UTF-8",
			ALIPAY_PUBLIC_KEY = "",
			SIGN_TYPE = "RSA",
			parterKey="";
	String returnUrl="";
	String notifyUrl="";
	@RequestMapping(value = "topay", method = { RequestMethod.GET })
	public String topay(HttpServletRequest request,HttpServletResponse httpResponse , ModelMap model) throws IOException {
		return "alipay";
	}
	
	@RequestMapping(value = "wap_pay", method = { RequestMethod.GET })
	public void wap_pay(HttpServletRequest httpRequest,HttpServletResponse httpResponse , ModelMap modelMap) throws IOException, AlipayApiException {
		AlipayClient alipayClient=alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, parterKey,SIGN_TYPE);
//		String orderNo=DateUtil.getNowDate("yyyyMMddHHmmssSSS");
		String orderNo=httpRequest.getParameter("orderNo");
		AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();// 创建API对应的request
		AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
		model.setOutTradeNo(orderNo);
		model.setProductCode("QUICK_WAP_PAY");
		model.setTotalAmount("0.01");
		model.setSubject("Iphone6 16G");
		model.setPassbackParams("ALIPAY_WAP");
		request.setReturnUrl(returnUrl);
		request.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		request.setBizModel(model);
//		alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"20170515010101002\"," + "    \"total_amount\":\"0.01\","
//						+ "    \"subject\":\"Iphone6 16G\"," + "    \"product_code\":\"QUICK_WAP_PAY\"" + "  }");// 填充业务参数
		String form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
	
		System.out.println(form);
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
	
	
	@RequestMapping(value = "pc_pay", method = { RequestMethod.GET })
	public void pc_pay(HttpServletRequest httpRequest,HttpServletResponse httpResponse , ModelMap modelMap) throws IOException, AlipayApiException {
		AlipayClient alipayClient=alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,SIGN_TYPE);
//		String orderNo=DateUtil.getNowDate("yyyyMMddHHmmssSSS");
		String orderNo=httpRequest.getParameter("orderNo");
		
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();//创建API对应的request
		AlipayTradePagePayModel model=new AlipayTradePagePayModel(); 
		model.setOutTradeNo(orderNo);
		model.setProductCode("FAST_INSTANT_TRADE_PAY");
		model.setTotalAmount("0.01");
		model.setSubject("Iphone6 16G");
		model.setBody("Iphone6 16G");
		model.setPassbackParams("ALIPAY_WEB");
		request.setReturnUrl(returnUrl);
		request.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		request.setBizModel(model);
	
		String form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
		System.out.println(form);
		httpResponse.setContentType("text/html;charset=" + CHARSET);
		httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}
}
