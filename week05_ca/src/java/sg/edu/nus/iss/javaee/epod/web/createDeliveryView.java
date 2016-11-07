/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import sg.edu.nus.iss.javaee.epod.business.DeliveryBean;
import sg.edu.nus.iss.javaee.epod.business.PodBean;
import sg.edu.nus.iss.javaee.epod.model.Delivery;
import sg.edu.nus.iss.javaee.epod.model.Pod;

/**
 *
 * @author wangxuemin
 */
@RequestScoped
@Named
public class createDeliveryView {
    @EJB 
    private DeliveryBean deliveryBean;
    
    @EJB
    private PodBean podBean;
    
    private String name;
    private String adress;
    private String phone;
    private Timestamp create_date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }
    
    public void createDelivery() {

        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setAdress(adress);
        delivery.setPhone(phone);
        delivery.setCreate_date(new Timestamp(System.currentTimeMillis()));
        
        int pkid=deliveryBean.addDelivery(delivery);
        createPod(pkid);
        
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/faces/showDelivery.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(createDeliveryView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createPod(int pkgid){
        Pod pod=new Pod();
        pod.setPkg_id(pkgid);
        pod.setDelivery_date(new Timestamp(System.currentTimeMillis()));
        
        podBean.addPod(pod);
    }
}
