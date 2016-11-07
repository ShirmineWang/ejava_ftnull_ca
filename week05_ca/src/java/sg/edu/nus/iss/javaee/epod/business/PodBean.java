/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.business;

import java.sql.Timestamp;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.javaee.epod.model.Pod;

/**
 *
 * @author linby
 */
public class PodBean {

    @PersistenceContext
    private EntityManager em;
    
    private static final String queryString = "select p from Pod p";
    
    public List<Pod> getAllPod() {
        TypedQuery<Pod> query = em.createQuery(
                queryString, Pod.class);
        return (query.getResultList());
    }
    
    public void addPod(Pod pod) {
        if (pod != null) {
            em.persist(pod);
        }        
    }
    
    public void UploadImageToPod(Pod pod) {
        Pod db_pod = em.find(Pod.class, pod.getPod_id());
        db_pod.setImage(pod.getImage());
        db_pod.setNote(pod.getNote());
        db_pod.setDelivery_date(pod.getDelivery_date());
    }
}
