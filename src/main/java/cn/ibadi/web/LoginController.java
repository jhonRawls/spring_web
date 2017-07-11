package cn.ibadi.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.ibadi.utils.HttpClientUtil;
import cn.ibadi.utils.MD5;

@Controller
@RequestMapping("/pay")
public class LoginController {
	Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping(value = "welcome", method = { RequestMethod.GET })
	public String doWelcome(ModelMap model) {
		model.put("user", "testname");
		return "welcome";
	}

	@RequestMapping(value = "notify")
	public String notify(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
		
		String text = IOUtils.toString(request.getInputStream(), "UTF-8");
		logger.info("支付回调"+JSON.toJSONString(text));
		
		StringBuffer xmlStr = new StringBuffer();
		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				xmlStr.append(line);
			}
			logger.info("支付回调通知：" + xmlStr.toString());

			// 返回成功给微信

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.put("xmlStr", xmlStr.toString());
		return "notify";
	}

	@RequestMapping(value = "topay", method = { RequestMethod.GET })
	public String topay(HttpServletRequest request, ModelMap model) {

		

		String jsonStr = HttpClientUtil.get(
				"");
		JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		JSONObject jsonData = jsonObject.getJSONObject("data");
		model.put("sdknonceStr", jsonData.get("nonceStr"));
		model.put("sdksignature", jsonData.get("signature"));
		model.put("sdktimestamp", jsonData.get("timestamp"));

		System.out.println();

		String timeSpan = Calendar.getInstance().getTimeInMillis() + "";
		timeSpan = timeSpan.substring(0, 10);
		System.out.println(timeSpan);
		String nonceStr = RandomStringUtils.randomAlphanumeric(16);
		String appId = "";
		String prePayId = "prepay_id=" + "";

		Map<String, String> map = new HashMap<>();
		model.put("appId", appId);
		model.put("timeStamp", timeSpan);
		model.put("nonceStr", nonceStr);
		model.put("package", prePayId);
		model.put("signType", "MD5");

		map.put("appId", appId);
		map.put("timeStamp", timeSpan);
		map.put("nonceStr", nonceStr);
		map.put("package", prePayId);
		map.put("signType", "MD5");
		String signStr = doSign(map);

		model.put("signStr", signStr);

		return "topay";
	}

	public static void main(String[] args) {
		
		Map<String, String> map = new HashMap<>();
		map.put("appId", "");
		map.put("timeStamp", "");
		map.put("nonceStr", "");
		map.put("package", "");
		map.put("signType", "");
		String signStr = doSign(map);

		System.out.println(signStr);
	}

	protected static String doSign(final Map<String, String> params) {
		// String signing = HttpParams.buildSigning(params, new String[] { SIGN
		// });
		// signing += "&key=" + wechatpay.getAppKey();
		String signing = "";
		try {
			// 签名--start
			ArrayList<String> list = new ArrayList<String>();
			for (Entry<String, String> entry : params.entrySet()) {
				if (entry.getValue() != "") {
					list.add(entry.getKey() + "=" + entry.getValue() + "&");
				}
			}
			int size = list.size();
			String[] arrayToSort = list.toArray(new String[size]);
			Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				sb.append(arrayToSort[i]);
			}
			signing = sb.toString();
			signing += "key=" + "";
			signing = MD5.MD5Encode(signing).toUpperCase();

			// 签名--end

		} catch (Exception e) {
			e.printStackTrace();
		}
		return signing;
	}

}
