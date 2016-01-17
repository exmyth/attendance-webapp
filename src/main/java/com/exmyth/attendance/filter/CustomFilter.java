package com.exmyth.attendance.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CustomFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		// 不过滤的url
		String url = request.getRequestURI();
		if (url.contains("exmyth")) {
			System.out.println("使用自定义的过滤器");
			request.getRequestDispatcher("/WEB-INF/pages/exmyth.jsp").forward(req,res);
		} else {
            chain.doFilter(req,res);
        }
	}
	@Override
	public void destroy() {
	}
}
