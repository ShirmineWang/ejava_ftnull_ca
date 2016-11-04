/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.javaee.noticeboard.model.Group;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

/**
 *
 * @author linby
 */
@Stateless
public class GroupBean {

    @PersistenceContext
    private EntityManager em;

    public void addToGroup(User user, String groupId) {
        if (user != null) {
            Group group = new Group();
            group.setGroupid(groupId);
            group.setUserid(user.getUserid());
            em.persist(group);
        }
    }
}
