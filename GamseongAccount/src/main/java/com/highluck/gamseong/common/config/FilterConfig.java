package com.highluck.gamseong.common.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;


@Component
public class FilterConfig implements Filter{

	//@Autowired
	//private AuthRepository authRepository;
	
	@Override
	public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse req = (HttpServletResponse) response;
        req.setHeader("Access-Control-Allow-Origin", "*");
        req.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT");
        req.setHeader("Access-Control-Max-Age", "3600");
        req.setHeader("Access-Control-Allow-Headers", "x-requested-with, origin, content-type, accept");
		
		chain.doFilter(request, req);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
