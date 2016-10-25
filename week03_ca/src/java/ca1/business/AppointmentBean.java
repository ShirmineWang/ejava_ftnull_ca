/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.business;

import ca1.model.Appointment;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author wangxuemin
 */
@Stateless
public class AppointmentBean {
    @PersistenceContext EntityManager em;
    
    private static final String queryString="select ap from Appointment ap join ap.people as p where p.email=:email";
    
    public void create(Appointment ap){
        em.persist(ap);
    }
    
    public List<Appointment> findAppointmentsByEmail(String email){
        TypedQuery<Appointment> query = em.createQuery(
                queryString, Appointment.class);
        query.setParameter("email", email);
        return (query.getResultList());
    }
}
