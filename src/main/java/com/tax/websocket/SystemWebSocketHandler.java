//package com.tax.websocket;
//
//import java.util.ArrayList;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//
///**
// * author lzc
// * <coushuxiaolang@163.com>
// */
//public class SystemWebSocketHandler implements WebSocketHandler {
//	
//	private static final Logger logger;
//	
//	 private static final ArrayList<WebSocketSession> users;
//	
//	
//	static{
//		users = new ArrayList<>();
//		logger = LoggerFactory.getLogger(SystemWebSocketHandler.class);
//	}
//	
//	@Autowired
//    private WebSocketService webSocketService;
//	
//	@Override
//	public void afterConnectionEstablished(WebSocketSession session)
//			throws Exception {
//		logger.debug("connect to the websocket success......");
//        users.add(session);
//        String userName = (String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME);
//        if(userName!= null){
//            //查询未读消息
//            int count = webSocketService.getUnReadNews((String) session.getAttributes().get(Constants.WEBSOCKET_USERNAME));
// 
//            session.sendMessage(new TextMessage(count + ""));
//        }
//
//	}
//
//	@Override
//	public void handleMessage(WebSocketSession session,
//			WebSocketMessage<?> message) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void handleTransportError(WebSocketSession session,
//			Throwable exception) throws Exception {
//		// TODO Auto-generated method stub
//		 if(session.isOpen()){
//            session.close();
//         }
//         logger.debug("websocket connection closed......");
//         users.remove(session);
//
//	}
//
//	@Override
//	public void afterConnectionClosed(WebSocketSession session,
//			CloseStatus closeStatus) throws Exception {
//		 logger.debug("websocket connection closed......");
//	     users.remove(session);
//	}
//
//	@Override
//	public boolean supportsPartialMessages() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
