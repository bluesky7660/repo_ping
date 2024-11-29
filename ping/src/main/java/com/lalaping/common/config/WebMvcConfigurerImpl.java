package com.lalaping.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lalaping.common.interceptor.CheckLoginSessionInterceptor;
import com.lalaping.common.interceptor.UsrCheckLoginSessionInterceptor;

@Configuration 
public class WebMvcConfigurerImpl implements WebMvcConfigurer{
	@Override 
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new CheckLoginSessionInterceptor())
				
				.addPathPatterns("/*/*/*/*Xdm*","/xdm/infra/*Xdm*") 
				
				.excludePathPatterns(
						"/resources/**",
						"/xdm/v1/css/**",
						"/xdm/v1/jquery/**",
						"/xdm/v1/js/**",
						"/xdm/v1/template/**",
						"/v1/loginXdm",
						"/v1/signupXdm",
						"/v1/infra/loginProc",
						"/loginUsrProc"
						);
		registry.addInterceptor(new UsrCheckLoginSessionInterceptor())
		
		.addPathPatterns("/usr/v1/*/**",
				"/v1/mapPoint/mapPointAdd",
				"/v1/member/editMember", 
				"/v1/member/orderList",
				"/v1/member/orderReturn",
				"/v1/checkout/ping_checkout"
				
				) 
		
		.excludePathPatterns(
				"/resources/**",
				"/usr/v1/css/**",
				"/usr/v1/jquery/**",
				"/usr/v1/js/**",
				"/usr/v1/template/**",
				"/v1/index",
				"/v1/ship/shipList",
				"/v1/port/portList",
				"/v1/login",
				"/v1/register",
				"/loginUsrProc",
				"/logoutUsrProc"
				);
	}
}