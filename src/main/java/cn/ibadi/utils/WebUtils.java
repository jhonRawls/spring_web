package cn.ibadi.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * web工具类
 * @author: fupf
 */
public class WebUtils {

    /**
     * 获取请求参数
     * @param request
     * @return
     */
    public static Map<String, String> getReqParams(HttpServletRequest request) {
        Map<String, String[]> requestParams = (Map<String, String[]>) request.getParameterMap();
        Map<String, String> params = new TreeMap<String, String>();
        for (Entry<String, String[]> entry : requestParams.entrySet()) {
            params.put(entry.getKey(), StringUtils.join(entry.getValue(), ","));
        }
        return params;
    }

    /**
     * 获取客户端ip
     * @param req
     * @return
     */
    public static String getClientIp(HttpServletRequest req) {
        String ip = req.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = req.getRemoteAddr();
        }
        return ip;
    }
}
