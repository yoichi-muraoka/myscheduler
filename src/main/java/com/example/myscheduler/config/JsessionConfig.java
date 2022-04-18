package com.example.myscheduler.config;

import java.util.Collections;

import javax.servlet.SessionTrackingMode;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * URLにjsessionidが入らないようにする
 * 参考：https://monakaice88.hatenablog.com/entry/2018/12/28/145412
 */
@Configuration
public class JsessionConfig {

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		// 初回アクセス時に、URLにSessionIDが付与されるのを防ぐ
		// https://blog.ik.am/entries/353
		ServletContextInitializer initializer = servletContext -> {
			servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
		};
		return initializer;
	}

}
