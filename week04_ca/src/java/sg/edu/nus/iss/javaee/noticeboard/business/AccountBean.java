/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.business;

import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

/**
 *
 * @author linby
 */
@Stateless
public class AccountBean {
     @PersistenceContext private EntityManager em;
     
     public void register(User user) throws SQLException {
        em.persist(user);
    }
}
