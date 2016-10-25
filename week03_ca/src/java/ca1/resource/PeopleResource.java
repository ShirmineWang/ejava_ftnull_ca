/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.resource;

import ca1.business.PeopleBean;
import ca1.model.People;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
@Path("/people")
public class PeopleResource {
    
    @Resource(lookup = "concurrent/thread1")
    private ManagedExecutorService executorService;
    
    @EJB private PeopleBean peopleBean;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void create(@Suspended AsyncResponse asyncResponse,MultivaluedMap<String,String> form){
        executorService.execute(() -> {
            asyncResponse.resume(doCreate(form));
        });
    }
    
    public Response doCreate(MultivaluedMap<String,String> form){
        String name=form.getFirst("name");
        String email=form.getFirst("email");
        
        People p=new People();
        p.setName(name);
        p.setEmail(email);
        
        peopleBean.create(p);

        return (Response.ok()
                .build());
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void findPeople(@Suspended AsyncResponse asyncResponse,@QueryParam("email") String email){
        executorService.execute(() -> {
            asyncResponse.resume(dofindPeople(email));
        });
    }
    
    public Response dofindPeople(@QueryParam("email") String email) {
        Optional<People> findPeople = peopleBean.find(email);

        if (!findPeople.isPresent()) {
            return (Response.status(Response.Status.NOT_FOUND)
                    .build());
        }

        People people = findPeople.get();

        JsonObject returnPeopleJsonObject = Json.createObjectBuilder()
                .add("pid", people.getPid())
                .add("name", people.getName())
                .add("email", people.getEmail())
                .build();

        return (Response.ok().entity(returnPeopleJsonObject).build());
    }
    
}
