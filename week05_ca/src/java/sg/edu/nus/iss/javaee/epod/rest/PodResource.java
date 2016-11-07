/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.rest;

import java.sql.Timestamp;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.javaee.epod.business.PodBean;
import sg.edu.nus.iss.javaee.epod.model.Pod;
import sg.edu.nus.iss.javaee.epod.web.ClientInstance;

/**
 *
 * @author linby
 */
@RequestScoped
@Path("/upload")
public class PodResource {

    @EJB
    private PodBean podBean;
    @Inject
    private ClientInstance client;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(MultivaluedMap<String, Object> form) {
        String note = (String) form.getFirst("note");
        int podId = (Integer) form.getFirst("podId");
        byte[] image = (byte[]) form.getFirst("image");
        Timestamp time = (Timestamp) form.getFirst("time");
        //configure pod entity
        Pod pod = new Pod();
        pod.setNote(note);
        pod.setPod_id(podId);
        pod.setDelivery_date(time);
        pod.setImage(image);
        // save it
        podBean.UploadImageToPod(pod);
        //request callback for ackId
        WebTarget target = client.target("http://10.10.0.50:8080/epod");
        //response
        String output = "File uploaded succeed";
        return Response.status(200).entity(output).build();
    }

}
