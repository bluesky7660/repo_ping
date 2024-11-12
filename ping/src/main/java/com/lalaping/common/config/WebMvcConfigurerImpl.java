package com.lalaping.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lalaping.common.interceptor.CheckLoginSessionInterceptor;

@Configuration 
public class WebMvcConfigurerImpl implements WebMvcConfigurer{
	@Override 
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(new CheckLoginSessionInterceptor()).order(1)
				
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
	}
}