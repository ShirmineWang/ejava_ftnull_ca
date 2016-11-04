/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import sg.edu.nus.iss.javaee.noticeboard.business.AccountBean;
import sg.edu.nus.iss.javaee.noticeboard.business.NoteBean;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;
import sg.edu.nus.iss.javaee.noticeboard.model.User;
import sg.edu.nus.iss.javaee.noticeboard.web.instance.SessionInstance;

/**
 *
 * @author wangxuemin
 */
@RequestScoped
@Named
public class CreateNotesView {

    @EJB
    private NoteBean noteBean;
    @EJB
    private AccountBean accountBean;

    private String category;
    private String title;
    private String content;
    private Timestamp postTime;
    private List<String> categories;

    @PostConstruct
    private void init() {
        this.categories = new ArrayList<String>();
        this.categories.add("Social");
        this.categories.add("For Sale");
        this.categories.add("Jobs");
        this.categories.add("Tuition");
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getpostTime() {
        return postTime;
    }

    public void setpostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public List<String> getAllCategory() {

        return categories;
    }

    public void createNote() throws IOException {

        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        Timestamp postTime = new Timestamp(new Date().getTime());
        note.setPostTime(postTime);
        HttpServletRequest req
                = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        User user = (User) req.getSession().getAttribute("user");
        Optional<User> userLogged = accountBean.findUserById(user.getUserid());
        if (userLogged != null) {
            note.setUser(userLogged.get());
        }
        //FIXME
        /*
        Category ca = new Category();
        ca.setCategoryid(0);
        ca.setCategoryname("tuition");
*/
        note.setCategory(category);
        noteBean.add(note);
        sendMessage(note);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/menu.xhtml");

    }

    public void sendMessage(Note note) {
        final JsonObject message = Json.createObjectBuilder()
                .add("title", note.getTitle())
                .add("message", note.getContent())
                .add("category", "tuition")
                .add("timestamp", (new Date()).toString())
                .build();

        for (Session s : SessionInstance.getInstance().session.getOpenSessions()) {
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

}
