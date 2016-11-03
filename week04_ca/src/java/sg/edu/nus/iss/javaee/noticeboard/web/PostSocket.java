/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.io.IOException;
import java.util.Date;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author linby
 */
@RequestScoped
@ServerEndpoint("/displayupdate")
public class PostSocket {

    @Resource(lookup = "concurrent/mExecutorThread")
    private ManagedScheduledExecutorService executor;

    @OnOpen
    public void open(Session session) {
        System.out.println(">>> new session: " + session.getId());
    }

    @OnMessage
    public void message(final Session session, final String msg) {
        System.out.println(">>> message: " + msg);

        executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(">>> in thread");
                final JsonObject message = Json.createObjectBuilder()
                        .add("message", msg)
                        .add("timestamp", (new Date()).toString())
                        .build();

                for (Session s : session.getOpenSessions()) {
                    try {
                        s.getBasicRemote().sendText(message.toString());
                    } catch (IOException ex) {
                        try {
                            s.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        });
        System.out.println(">>> exiting message");
    }

    @OnError
    public void onError(final Session session,final Throwable t) {
        System.out.println(">>> error occured:" + t.getMessage());
    }
//
//    @OnClose
//    public void onClose(final Session session) {
//        System.out.println(">>> close connection" + session.getId());
//    }
}
