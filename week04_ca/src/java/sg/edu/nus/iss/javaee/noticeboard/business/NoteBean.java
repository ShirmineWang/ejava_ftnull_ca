/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;

/**
 *
 * @author linby
 */
@Stateless
public class NoteBean {
@PersistenceContext
    private EntityManager em;
    private static final String query1 = "SELECT n FROM Note n WHERE n.users.userId = :userId ORDER BY n.postTime DESC";
    private static final String query2 = "SELECT n FROM Note n ORDER BY n.postTime DESC";
	
    public void add(Note note){
            em.persist(note);
    }
        
    public List<Note> getAllNotes() { 
        TypedQuery<Note> query = em.createQuery(query2, Note.class);
        return query.getResultList();
    }
    
    public List<Note> getAllNotesofUser(String userId) {
        TypedQuery<Note> query = em.createNamedQuery(query1, Note.class);
        query.setParameter("userId", userId);
	return query.getResultList();
    }
    
}
