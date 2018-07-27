package com.hibi.www.filter;

import com.hibi.www.socket.WsServer;
import org.java_websocket.WebSocketImpl;

import javax.servlet.*;
import java.io.IOException;

public class WsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("启动监听!");
//        this.startWs();
    }

    @Override
    public void destroy() {

    }

    public void startWs(){
//        WebSocketImpl.DEBUG =false;
//        WsServer server;
//        server = new WsServer(8887);
//        server.start();
    }

}
