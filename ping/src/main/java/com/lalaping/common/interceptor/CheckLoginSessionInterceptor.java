package com.lalaping.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.lalaping.common.util.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CheckLoginSessionInterceptor implements HandlerInterceptor{
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {

			HttpSession session = request.getSession();
			String requestURL = request.getRequestURL().toString();
			String refererURL = request.getHeader("referer");
			String queryString = request.getQueryString();
			System.out.println("requestURL: "+ requestURL);
			System.out.println("refererURL: "+ refererURL);
			System.out.println("queryString: "+ queryString);
			System.out.println("주소: " + session.getAttribute("prevPage"));
			System.out.println("현재 세션 ID: " + request.getSession().getId());
			System.out.println("세션의 sessIdXdm: " + request.getSession().getAttribute("sessIdXdm"));

			if (request.getSession().getAttribute("sessIdXdm") != null) {
				System.out.println("세션 로그인o : 요청 계속 처리");
			} else {
				if ( (!requestURL.contains("/login")) || (!refererURL.contains("/login")) ) {
		            String fullURL = requestURL + (queryString != null ? "?" + queryString : "");
		            System.out.println("주소뺵: " +fullURL);
		            session.setAttribute("prevPage", fullURL);
		        }
				response.sendRedirect(Constants.URL_XDMLOGINFORM);
				System.out.println("세션 로그인x : 요청 처리 중단");
				return false;
			}

			return HandlerInterceptor.super.preHandle(request, response, handler);
		}
}