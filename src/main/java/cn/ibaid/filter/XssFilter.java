package cn.ibaid.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class XssFilter implements Filter {
	Logger logger = Logger.getLogger(XssFilter.class);

	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("filter init ");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("start do something");
		chain.doFilter(request, response);
		
	}

	public void destroy() {
		logger.info("destroy filter");
	}
	
	private void printParams(HttpServletRequest request){
		
		if(request.getMethod().equalsIgnoreCase("get")){//get处理转码问题
			Map<String, String[]> map=request.getParameterMap();
			
		}
	}

}
