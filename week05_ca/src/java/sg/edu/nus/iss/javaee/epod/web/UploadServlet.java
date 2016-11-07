/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.web;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
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
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @EJB
    private PodBean podBean;

    @Override
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Client client = ClientBuilder.newBuilder()
                .register(MultiPartFeature.class)
                .build();
        Part imagePart = req.getPart("image");
        Part notePart = req.getPart("note");
        Part podIdPart = req.getPart("podId");
        byte[] noteByte = new byte[(int) notePart.getSize()]; 
//        String note = req.getParameter("note");
//        int podId = Integer.valueOf(req.getParameter("podId"));
//        String time = req.getParameter("time");
        byte[] image = new byte[(int) imagePart.getSize()];
        byte[] podIdByte = new byte[(int) podIdPart.getSize()]; 
        imagePart.getInputStream().read(image);
        notePart.getInputStream().read(noteByte);
        podIdPart.getInputStream().read(podIdByte);
        //save to dataBase
        Pod pod = new Pod();
        pod.setImage(image);
        pod.setPod_id(new BigInteger(podIdByte).intValue());
        pod.setNote(new String(noteByte));
        pod.setDelivery_date(new Timestamp(new Date().getTime()));
        podBean.UploadImageToPod(pod);
        //request to HQ
        MultiPart formData = new FormDataMultiPart()
                .field("teamId", "97c59d13", MediaType.TEXT_PLAIN_TYPE)
                .field("podId", req.getParameter("podId"), MediaType.TEXT_PLAIN_TYPE)
                .field("callback", "http://172.23.132.212/week05_ca/callback", MediaType.TEXT_PLAIN_TYPE)
                .field("note", req.getParameter("note"), MediaType.TEXT_PLAIN_TYPE)
                .bodyPart((BodyPart)req.getPart("image"));
        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
        WebTarget target = client.target("http://10.10.0.50:8080/epod/upload");
        Invocation.Builder inv = target.request();
        System.out.println(">>> part: " + formData);
        Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));
        System.out.println(">> call resp:" + callResp.getStatus());
    }

}
