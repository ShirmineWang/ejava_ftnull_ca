/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.business;

import ca1.model.People;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author wangxuemin
 */
@Stateless
public class PeopleBean {
    @PersistenceContext private EntityManager em;
    
    public void create(People people){
        String tid = UUID.randomUUID().toString().substring(0, 8);

		System.out.println(">>> tid: " + tid);

		people.setPid(tid);

        em.persist(people);
    }
    
}
