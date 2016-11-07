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
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.javaee.epod.business.DeliveryBean;
import sg.edu.nus.iss.javaee.epod.business.PodBean;
import sg.edu.nus.iss.javaee.epod.model.Delivery;
import sg.edu.nus.iss.javaee.epod.model.Pod;

/**
 *
 * @author linby
 */
@RequestScoped
@Path("/items")
public class DeliveryResource {

    @EJB
    private PodBean podBean;
    @EJB
    private DeliveryBean deliveryBean;
    
    

    @GET
    public Response getAllDelivery() {
        List<Pod> deliveries=podBean.getAllPod();
        JsonArrayBuilder appJsonArrayBuilder=Json.createArrayBuilder();
        deliveries.stream().forEach((p) -> {
            appJsonArrayBuilder.add(Json.createObjectBuilder()
                    .add("teamId","04802a45")
                    .add("podId", p.getPod_id())
                    .add("address", p.getDelivery().getAddress())
                    .add("name", p.getDelivery().getName())
                    .add("phone", p.getDelivery().getPhone())
                    .build()
            );
          });
        return (Response.status(Response.Status.CREATED).entity(appJsonArrayBuilder.build()).build());
    }
}
