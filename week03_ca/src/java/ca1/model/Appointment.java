/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author wangxuemin
 */
@Entity
@Table(name="appointment")
public class Appointment implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    private Integer appt_id;
    
    private String description;
    private Date appt_date;
    
    @ManyToOne(optional=false) 
    @JoinColumn(name="pid", nullable=false)
    private People people;

    public Integer getAppt_id() {
        return appt_id;
    }

    public void setAppt_id(Integer appt_id) {
        this.appt_id = appt_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAppt_date() {
        return appt_date;
    }

    public void setAppt_date(Date appt_date) {
        this.appt_date = appt_date;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
    
}
