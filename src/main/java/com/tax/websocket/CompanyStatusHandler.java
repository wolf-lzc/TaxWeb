//package com.tax.websocket;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
///**
// * author lzc
// * <coushuxiaolang@163.com>
// */
//@Configuration
//@EnableWebMvc
//@EnableWebSocket
//public class CompanyStatusHandler extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
//
//	@Override
//	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Bean
//	public WebSocketHandler systemWebSocketHandler(){
//		return new SystemWebSocketHandler();
//	}
//
//}
