/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.model;

import java.util.Collection;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author linby
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    private String userid;
    private String password;
    
    @OneToMany(mappedBy = "users")
    private Collection<Note> notes;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Collection<Note> getNoteList() {
        return notes;
    }

    public void setNoteList(Collection<Note> noteList) {
        this.notes = noteList;
    }
    

    public JsonObject toJSON() {
        return (Json.createObjectBuilder()
                .add("userid", userid)
                .add("password", password)
                .build());
    }

}
