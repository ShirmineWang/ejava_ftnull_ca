///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package sg.edu.nus.iss.javaee.epod.rest;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.sql.Timestamp;
//import java.util.Map;
//import javax.ejb.EJB;
//import static javax.imageio.ImageIO.read;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
//import org.glassfish.jersey.media.multipart.FormDataMultiPart;
//import org.glassfish.jersey.media.multipart.FormDataParam;
//import org.glassfish.jersey.media.multipart.MultiPart;
//import org.glassfish.jersey.media.multipart.MultiPartFeature;
//import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
//import sg.edu.nus.iss.javaee.epod.business.PodBean;
//import sg.edu.nus.iss.javaee.epod.model.Pod;
//
///**
// *
// * @author linby
// */
//public class PodResource {
//
//    @EJB
//    private PodBean podBean;
//
//    @POST
//    @Consumes(MediaType.MULTIPART_FORM_DATA)
//    public Response uploadFile(@FormDataParam("image") InputStream fileInputStream,
//            @FormDataParam("image") FormDataContentDisposition contentDispositionHeader,
//            Map<String, Object> parmas) {
//
//        String note = (String) parmas.get("note");
//        int podId = (Integer) parmas.get("podId");
//        Timestamp time = (Timestamp) parmas.get("time");
//        //configure pod entity
//        Pod pod = new Pod();
//        pod.setNote(note);
//        pod.setPod_id(podId);
//        pod.setDelivery_date(time);
//        //parsing stream to byte[] image
//        byte[] image = getImageByte(fileInputStream);
//        pod.setImage(image);
//        // save it
//        podBean.UploadImageToPod(pod);
//        //request callback for ackId
//        uploadToHQ(pod.getPod_id(), pod.getNote());
//        //response
//        String output = "File uploaded succeed";
//        return Response.status(200).entity(output).build();
//    }
//
//    private byte[] getImageByte(InputStream fileInputStream) {
//        try {
//            byte[] images = new byte[1024];
//            int read = 0;
//            OutputStream out = new FileOutputStream(new File("/home/sky/Pictures/ca3.png"));
//            while ((read = fileInputStream.read(images)) != -1) {
//                out.write(images, 0, read);
//            }
//            fileInputStream.close();
//            return images;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private void uploadToHQ(int podId, String note) {
//        Client client = ClientBuilder.newBuilder()
//                .register(MultiPartFeature.class)
//                .build();
//        FileDataBodyPart imgPart = new FileDataBodyPart("image",
//                new File("/home/sky/Pictures/ca3.png"),
//                MediaType.APPLICATION_OCTET_STREAM_TYPE);
//        imgPart.setContentDisposition(
//                FormDataContentDisposition.name("image")
//                .fileName("ca3.png").build());
//        MultiPart formData = new FormDataMultiPart()
//                .field("teamId", "97c59d13", MediaType.TEXT_PLAIN_TYPE)
//                .field("podId", podId, MediaType.TEXT_PLAIN_TYPE)
//                .field("callback", "http://172.23.132.212/week05_ca/callback", MediaType.TEXT_PLAIN_TYPE)
//                .field("note", note, MediaType.TEXT_PLAIN_TYPE)
//                .bodyPart(imgPart);
//        formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
//        WebTarget target = client.target("http://10.10.0.50:8080/epod/upload");
//        Invocation.Builder inv = target.request();
//        System.out.println(">>> part: " + formData);
//        Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));
//        System.out.println(">> call resp:" + callResp.getStatus());
//    }
//
//}
