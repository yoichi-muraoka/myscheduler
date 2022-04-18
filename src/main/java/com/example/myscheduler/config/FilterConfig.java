package com.example.myscheduler.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.myscheduler.filter.AuthFilter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

	@Bean
	public FilterRegistrationBean<AuthFilter> authFilter() {
		var bean = new FilterRegistrationBean<AuthFilter>(new AuthFilter());
		bean.addUrlPatterns("/");
		bean.addUrlPatterns("/daily/*");
		return bean;
	}

}
