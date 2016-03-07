package com.tax.websocket;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * author lzc
 * <coushuxiaolang@163.com>
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		//添加这个Endpoint，这样在网页中就可以通过websocket连接上服务了
		 registry.addEndpoint("/coordination").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean configureMessageConverters(
			List<MessageConverter> messageConverters) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		 System.out.println("服务器启动成功");
	     //这里设置的simple broker是指可以订阅的地址，也就是服务器可以发送的地址
	     /**
	      * userChat 用于用户聊天
	      */
		 registry.enableSimpleBroker("/userChat");
		 registry.setApplicationDestinationPrefixes("/app");
		
	}

}
