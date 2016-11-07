/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import sg.edu.nus.iss.javaee.epod.business.PodBean;
import sg.edu.nus.iss.javaee.epod.model.Pod;

/**
 *
 * @author linby
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @EJB
    private PodBean podBean;
    
    @Override
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();
        BodyPart imgPart = new BodyPart(byte[].class,
                MediaType.APPLICATION_OCTET_STREAM_TYPE);
        imgPart.setContentDisposition(
                FormDataContentDisposition.name("image")
                .fileName("ca3.png").build());
        byte[] image = imgPart.getEntityAs(byte[].class);
        //save to dataBase
        Pod pod = new Pod();
        pod.setImage(image);
        pod.setPod_id(Integer.valueOf(req.getParameter("podId")));
        pod.setNote(req.getParameter("note"));
        pod.setDelivery_date(new Timestamp(new Date().getTime()));
        podBean.UploadImageToPod(pod);
        //request to HQ
        MultiPart formData = new FormDataMultiPart()
                .field("teamId", "97c59d13", MediaType.TEXT_PLAIN_TYPE)
                .field("podId", req.getParameter("podId"), MediaType.TEXT_PLAIN_TYPE)
                .field("callback", "http://172.23.132.212/week05_ca/callback", MediaType.TEXT_PLAIN_TYPE)
                .field("note", req.getParameter("note"), MediaType.TEXT_PLAIN_TYPE)
                .bodyPart(imgPart);
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        WebTarget target = client.target("http://10.10.0.50:8080/epod/upload");
        Invocation.Builder inv = target.request();
        System.out.println(">>> part: " + formData);
        Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));
        System.out.println(">> call resp:" + callResp.getStatus());
    }

}
