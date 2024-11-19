package com.lalaping.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.lalaping.common.util.Constants;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UsrCheckLoginSessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터1");
		HttpSession session = request.getSession();
		String requestURL = request.getRequestURL().toString();
		String refererURL = request.getHeader("referer");
		String queryString = request.getQueryString();
		System.out.println("requestURL: "+ requestURL);
		System.out.println("refererURL: "+ refererURL);
		System.out.println("queryString: "+ queryString);
		System.out.println("seq: " + session.getAttribute("sessSeqUsr"));
		System.out.println("이름: " + session.getAttribute("sessNameUsr"));
		System.out.println("주소: " + session.getAttribute("prevPage"));
		System.out.println("인터셉터2");
//		System.out.println("seq: "+request.getSession().getAttribute("sessSeqXdm"));
		if(request.getSession().getAttribute("kakoLogin") != null) {
			
		}else {
			if(request.getSession().getAttribute("sessSeqUsr") != null) {
				//by pass
			}else {
				
//				if(requestURL.contains("product_buy")) {
//					session.setAttribute("prevPage", requestURL);
//				}else {
////					session.setAttribute("prevPage", null);
//				}
				if ( (!requestURL.contains("/v1/login")) || (!refererURL.contains("/v1/login")) ) {
		            String fullURL = requestURL + (queryString != null ? "?" + queryString : "");
		            System.out.println("주소뺵: " +fullURL);
		            session.setAttribute("prevPage", fullURL);
		        }
//				session.setAttribute("prevPage", null);
//				System.out.println("sessionURL: "+ session.getAttribute("prevPage"));
				response.sendRedirect(Constants.URL_USRLOGINFORM);
				return false;
			}
		}
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
