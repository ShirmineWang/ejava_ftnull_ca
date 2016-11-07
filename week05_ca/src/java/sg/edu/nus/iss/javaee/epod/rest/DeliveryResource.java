/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.javaee.epod.business.DeliveryBean;
import sg.edu.nus.iss.javaee.epod.model.Delivery;

/**
 *
 * @author linby
 */
@RequestScoped
@Path("/items")
public class DeliveryResource {

    @EJB
    private DeliveryBean deliveryBean;

    @GET
    public Response getAllDelivery() {
        List<Delivery> deliveryList = deliveryBean.getAllDelivery();
        if (deliveryList == null || deliveryList.isEmpty()) {
            return (Response.status(Response.Status.NOT_FOUND).entity("no delivery data").build());
        }
        JsonArrayBuilder jarrayBuilder = Json.createArrayBuilder();
        for (Delivery delivery : deliveryList) {
            jarrayBuilder.add(Json.createObjectBuilder()
                    .add("name", delivery.getName())
                    .add("address", delivery.getAdress())
                    .add("phone", delivery.getPhone())
                    .add("pkg_id", delivery.getPkg_id())
            );
        }
        return (Response.ok(jarrayBuilder.build()).build());
    }
}
