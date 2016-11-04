/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import sg.edu.nus.iss.javaee.noticeboard.business.NoteBean;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

/**
 *
 * @author linby
 */
@RequestScoped
@Named
public class PostedNotesView {

    private List<Note> notes = new ArrayList<>();

    @EJB
    private NoteBean noteBean;
    
    @PostConstruct
    private void init() {
        getPersonalNotes();
    }

    public void getPersonalNotes() {
        HttpServletRequest req
                = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        User user = (User) req.getSession().getAttribute("user");
        if (user != null) {
            notes = noteBean.getAllNotesofUser(user.getUserid());
        }
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
    
    

}
