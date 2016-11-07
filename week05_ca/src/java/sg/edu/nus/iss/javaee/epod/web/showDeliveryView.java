/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import sg.edu.nus.iss.javaee.epod.business.DeliveryBean;
import sg.edu.nus.iss.javaee.epod.model.Delivery;

/**
 *
 * @author wangxuemin
 */
@RequestScoped
@Named
public class showDeliveryView {
    private List<Delivery> deliveries = new ArrayList<>();

    @EJB
    private DeliveryBean deliveryBean;
    
    @PostConstruct
    private void init() {
        getAllDeliveries();
    }

    public void getAllDeliveries() {        
            deliveries = deliveryBean.getAllDelivery();        
    }

    public List<Delivery> getDeliveries(){
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
    
}
