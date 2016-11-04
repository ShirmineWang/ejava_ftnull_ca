/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.web;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@ViewScoped
@Named
public class LoginView implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

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

    public String login() {
        HttpServletRequest req
                = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();
        try {
            req.login(username, password);
        } catch (Throwable t) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage("Incorrect login"));
            return (null);
        }

        return ("secure/invalid_page");
    }

}
