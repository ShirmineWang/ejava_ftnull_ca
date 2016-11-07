/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.javaee.epod.business.DeliveryBean;

/**
 *
 * @author linby
 */
@RequestScoped
@Path("/items")
public class DeliveryResource {
    @EJB public DeliveryBean deliveryBean;

    @GET
    public Response getAllDelivery() {
        
        return (Response.ok().build());
    }
}
