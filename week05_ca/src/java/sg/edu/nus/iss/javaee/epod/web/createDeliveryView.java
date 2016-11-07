/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.web;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import sg.edu.nus.iss.javaee.epod.business.DeliveryBean;
import sg.edu.nus.iss.javaee.epod.model.Delivery;

/**
 *
 * @author wangxuemin
 */
@RequestScoped
@Named
public class createDeliveryView {
    @EJB 
    private DeliveryBean deliveryBean;
    
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
        
        deliveryBean.addDelivery(delivery);
    }
            
}
