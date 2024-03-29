package com.hhhhhx.mbgl.ws;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hhhhhx.mbgl.entity.Message;
import org.springframework.stereotype.Component;

import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/mbgl/chat/{userId}")
@Component
public class WebSocketService {


    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketService> webSocketMap = new ConcurrentHashMap<>();
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 接收userId
     */
    private String userId = "";


    /**
     * 连接建立成功调用的方法
     * <p>
     * 1.用map存 每个客户端对应的MyWebSocket对象
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
            webSocketMap.put(userId, this);
            //加入set中
        } else {
            webSocketMap.put(userId, this);
            //加入set中
        }
    }


    /**
     * 报错
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    /**
     * 实现服务器推送到对应的客户端
     */
    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 自定义 指定的userId服务端向客户端发送消息
     */
    public static void sendInfo(Message message) {
        //log.info("发送消息到:"+userId+"，报文:"+message);
        if (!StrUtil.isEmpty(message.getReceiveUserId().toString())
                && webSocketMap.containsKey(message.getReceiveUserId().toString())) {

            // 两个同时在线

            webSocketMap.get(message.getSendUserId().toString()).sendMessage(JSONObject.toJSONString(message));
            webSocketMap.get(message.getReceiveUserId().toString()).sendMessage(JSONObject.toJSONString(message));
        } else {
            // 只有发消息的在线
            webSocketMap.get(message.getSendUserId().toString()).sendMessage(JSONObject.toJSONString(message));
        }
    }

    /**
     * 自定义关闭
     *
     * @param userId
     */
    public static void close(String userId) {
        if (webSocketMap.containsKey(userId)) {
            webSocketMap.remove(userId);
        }
    }

    /**
     * 获取在线用户信息
     *
     * @return
     */
    public static Map getOnlineUser() {
        return webSocketMap;
    }
}
