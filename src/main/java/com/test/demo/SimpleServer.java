package com.test.demo;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/4/17.
 */
@ServerEndpoint("/SimpleWebSocket")
public class SimpleServer {
    private Logger logger = Logger.getLogger(this.getClass().getName());
    @OnOpen
    public void onOpen(Session session){
        logger.info("websocket open ... ");
        System.out.println(session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        logger.info("websocket close .... ");
    }

    @OnError
    public void onError(Throwable e){
        logger.severe("error～～～～"+e.getMessage());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info("receive the message from client ["+message+"]");

        session.getBasicRemote().sendText("服务器收到客户端的信息:"+message);
    }
}
