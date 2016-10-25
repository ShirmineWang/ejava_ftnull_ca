/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.resource;

import ca1.business.AppointmentBean;
import ca1.business.PeopleBean;
import ca1.model.Appointment;
import ca1.model.People;
import com.sun.faces.action.RequestMapping;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author wangxuemin
 */
@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
    @Resource(lookup = "concurrent/thread1")
    private ManagedExecutorService executorService;
    
    @EJB private AppointmentBean em;
    @EJB private PeopleBean pb;
    
    @GET
    @Path("/{email}")
    @Produces("Application/json")
    //@Produces(MediaType.APPLICATION_JSON)
    public void find(@Suspended AsyncResponse asyncResponse,@PathParam("email")String email){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doFind(email));
            }
        });
    }
    
    public Response doFind(@PathParam("email") String email){
        List<Appointment> ap= em.findAppointmentsByEmail(email);
        
        JsonArrayBuilder apBuilder=Json.createArrayBuilder();
        
        for (Appointment appointment : ap) {
            JsonObject appointmentJsonObject=Json.createObjectBuilder().add("appointmentId", appointment.getAppt_id()).add("dateTime", appointment.getAppt_date().getTime()).add("description",appointment.getDescription()).add("personId",appointment.getPeople().getPid()).build();        
            apBuilder.add(appointmentJsonObject);
        }
        
        return (Response.ok().entity(apBuilder.build()).build());
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void create(@Suspended AsyncResponse asyncResponse,MultivaluedMap<String,String> form){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                asyncResponse.resume(doCreate(form));
            }
        });
    }
    
    public Response doCreate(MultivaluedMap<String,String> form){
        String email=form.getFirst("email");
        String date=form.getFirst("date");
        String description=form.getFirst("description");
        
        Long datemilli=Long.parseLong(date);
        Timestamp tsdate=new Timestamp(datemilli);
        
        Optional<People> pp=pb.find(email);
        
        if (!pp.isPresent()) {
            return (Response.status(Response.Status.NOT_FOUND)
                    .build());
        }
        
        Appointment p=new Appointment();
        p.setDescription(description);
        p.setAppt_date(tsdate);
        p.setPeople(pp.get());
        
        em.create(p);
        return (Response.ok()
                .build());
    }
}
