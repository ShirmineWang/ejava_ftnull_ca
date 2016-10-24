/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.resource;

import ca1.business.PeopleBean;
import ca1.model.People;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    
    @EJB private PeopleBean peopleBean;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
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
    
}
