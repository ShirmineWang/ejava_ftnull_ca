/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import sg.edu.nus.iss.javaee.noticeboard.business.AccountBean;
import sg.edu.nus.iss.javaee.noticeboard.business.NoteBean;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;
import sg.edu.nus.iss.javaee.noticeboard.web.instance.SessionInstance;

/**
 *
 * @author linby
 */
@RequestScoped
@ServerEndpoint("/displayupdate")
public class PostSocket {

    @EJB NoteBean noteBean;
    
    @OnOpen
    public void open(Session session) {
        SessionInstance.getInstance().session = session;
        System.out.println(">>> new session: " + session.getId());
        
        List<Note> notes = noteBean.getAllNotes();
        
        
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        try {
            notes.stream().map(n -> {
                return n.toJsonObject();
            }).forEach(j -> {
                arrayBuilder.add(j);
            });
            session.getBasicRemote().sendText(arrayBuilder.build().toString());
        } catch (IOException ex) {
            Logger.getLogger(PostSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @OnMessage
    public void message(final Session session, final String msg) {
        System.out.println(">>> message: " + msg);
        
        
        
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
        System.out.println(">>> exiting message");
    }

    @OnError
    public void onError(final Session session, final Throwable t) {
        System.out.println(">>> error occured:" + t.getMessage());
    }

    @OnClose
    public void onClose(final Session session) {
        System.out.println(">>> close connection" + session.getId());
    }
}
