/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.business;

import java.sql.SQLException;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sg.edu.nus.iss.javaee.noticeboard.model.User;
import sg.edu.nus.iss.javaee.noticeboard.web.LoginView;
import javax.persistence.TypedQuery;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

/**
 *
 * @author linby
 */
@Stateless
public class AccountBean {

    @PersistenceContext
    private EntityManager em;

    private static final String queryLogin = "SELECT u FROM User u WHERE u.userid=:userid and u.password=:password";
    private static final String querySelect = "SELECT u FROM User u WHERE u.userid = :userid";
    
    public void register(User user) throws SQLException {
        em.persist(user);
    }

    public Optional<User> findUserById(String id){
        TypedQuery<User> query=em.createQuery(querySelect,User.class);
        query.setParameter("userid", id);
        return Optional.ofNullable(query.getSingleResult());
     //   return query.getResultList().get(0);
    //     return em.find(User.class, id);
     }
    
    public boolean validateLogin(User user) throws SQLException {
        TypedQuery<User> query = em.createQuery(
                queryLogin, User.class);
        query.setParameter("userid", user.getUserid());
        query.setParameter("password", user.getPassword());
        return (query.getResultList().size() == 1);
    }
}
