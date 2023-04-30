package com.anabada.config.socket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySocketIOHandler implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
  //  private final SocketIOServer server;
//
//    @Autowired
//    public MySocketIOHandler(SocketIOServer server) {
//        this.server = server;
//
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        server.addListeners(new MySocketIOListener());
//        server.start();
//    }
//
//    public void destroy() throws Exception {
//        server.stop();
//    }
//
//    private class MySocketIOListener implements DataListener<Object> {
//        @Override
//        public void onData(SocketIOClient client, Object data, AckRequest ackSender) throws Exception {
//            // 이벤트 핸들러 구현
//        }
}