/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web.instance;

import javax.websocket.Session;

/**
 *
 * @author linby
 */
public class SessionInstance {

    private static SessionInstance mInstance;
    public Session session;

    public static SessionInstance getInstance() {
        if (mInstance == null) {
            mInstance = new SessionInstance();
        }
        return mInstance;
    }

}
