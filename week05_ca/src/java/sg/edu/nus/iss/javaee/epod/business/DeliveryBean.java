/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.javaee.epod.model.Delivery;

/**
 *
 * @author linby
 */
@Stateless
public class DeliveryBean {
     @PersistenceContext
    private EntityManager em;
     
    private static final String queryString= "select d from Delivery d";
     
    public List<Delivery> getAllDelivery(){
         TypedQuery<Delivery> query = em.createQuery(
                queryString, Delivery.class);
        return (query.getResultList());
    }
    
    public int addDelivery(Delivery delivery){
    
            em.persist(delivery);
            em.flush();
            int pkgkey=delivery.getPkg_id();
            return pkgkey;
    }
}
