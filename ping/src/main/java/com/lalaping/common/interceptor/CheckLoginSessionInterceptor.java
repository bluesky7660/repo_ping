//package com.lalaping.common.interceptor;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.lalaping.common.util.Constants;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//public class CheckLoginSessionInterceptor implements HandlerInterceptor{
//		@Override
//		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//				throws Exception {
//
//			System.out.println("현재 세션 ID: " + request.getSession().getId());
//			System.out.println("세션의 sessIdXdm: " + request.getSession().getAttribute("sessIdXdm"));
//
//			if (request.getSession().getAttribute("sessIdXdm") != null) {
//				System.out.println("세션 로그인o : 요청 계속 처리");
//			} else {
//				response.sendRedirect(Constants.URL_XDMLOGINFORM);
//				System.out.println("세션 로그인x : 요청 처리 중단");
//				return false;
//			}
//
//			return HandlerInterceptor.super.preHandle(request, response, handler);
//		}
//}