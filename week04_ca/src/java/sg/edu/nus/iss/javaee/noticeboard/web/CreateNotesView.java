/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import sg.edu.nus.iss.javaee.noticeboard.business.AccountBean;
import sg.edu.nus.iss.javaee.noticeboard.business.NoteBean;
import sg.edu.nus.iss.javaee.noticeboard.model.Category;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

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

    public void createNote() {

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
        note.setCategory(category);
        noteBean.add(note);
        /*
            JsonObjectBuilder jsonNote = Json.createObjectBuilder()
                    .add("title", title)
                    .add("content", content)
                    .add("postTime", postTime.toString())
                    .add("category", category);  
            
            JsonArray ja = Json.createArrayBuilder().add(jsonNote).add(jsonNote).build();
            String messageString=ja.toString();
         */
        //    UserSessionHandler us = UserSessionHandler.getInstance();

//        try {
//            //   sendMessageOverSocket(ja.toString());
//            ec.redirect(ec.getRequestContextPath() + "/faces/index.html");
//        } catch (IOException ex) {
//            Logger.getLogger(CreateNotesView.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
