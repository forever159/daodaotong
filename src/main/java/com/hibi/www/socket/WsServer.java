package com.hibi.www.socket;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

public class WsServer extends WebSocketServer {

    public WsServer(int port){
        super(new InetSocketAddress(port));
    }

    public WsServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {

    }

    @Override
    public void onClose(WebSocket webSocket, int i, String s, boolean b) {
        userLive(webSocket);
    }

    @Override
    public void onMessage(WebSocket webSocket, String s) {
        System.out.print(s);
        if (s != null && s.startsWith("online")){
            String online = s.replaceFirst("online", s);
            userLiveAdd(webSocket,online);
        }else if(null != s && s.startsWith("offline")){
            userLive(webSocket);
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception e) {
        e.printStackTrace();
    }

    private void userLive(WebSocket cool){
        WsPool.removeUser(cool);
    }

    private void userLiveAdd(WebSocket cool,String username){
        WsPool.addUser(username,cool);
    }

}
