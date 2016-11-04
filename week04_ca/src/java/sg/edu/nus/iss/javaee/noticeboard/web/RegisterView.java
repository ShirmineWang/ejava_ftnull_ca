/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import sg.edu.nus.iss.javaee.noticeboard.business.AccountBean;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

/**
 *
 * @author linby
 */
@ViewScoped
@Named("registerView")
public class RegisterView implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String repassword;
    
    @EJB private AccountBean accountBean;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String register() {
        HttpServletRequest req
                = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        try {
            if (password.equals(repassword)&& username!=null&&!username.isEmpty()) {
                //do register
                User user = new User();
                user.setUserid(username);
                user.setPassword(password);
                accountBean.register(user);
                return ("login");
            }else{
                return ("secure/invalid_page");
            }
        } catch (Throwable t) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Incorrect login"));
            return (null);
        }
    }

}
