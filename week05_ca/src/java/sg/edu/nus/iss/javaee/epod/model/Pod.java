/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.epod.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 *
 * @author linby
 */
@Entity
public class Pod implements Serializable {

    @Id
    private int pod_id;
    private int pkg_id;
    private String note;
    @Lob
    private byte[] image;
    private Timestamp delivery_date;
    private String act_id;

    public int getPod_id() {
        return pod_id;
    }

    public void setPod_id(int pod_id) {
        this.pod_id = pod_id;
    }

    public int getPkg_id() {
        return pkg_id;
    }

    public void setPkg_id(int pkg_id) {
        this.pkg_id = pkg_id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Timestamp getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Timestamp delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }

}
