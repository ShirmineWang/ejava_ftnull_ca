/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.javaee.noticeboard.model;

import java.sql.Timestamp;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author linby
 */
@Entity
@Table(name = "note")
public class Note {

    @Id
    private int noteid;
    private String title;
    private String content;
    @Column(name = "timestamp")
    private Timestamp postTime;
    private String categoryid;
    @ManyToOne(optional = false)
    @JoinColumn(name = "userid", nullable = false)
    private User users;
    /*
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoryid", nullable = false)
    private Category category;
    */
    

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostTime() {
        return postTime;
    }

    public void setPostTime(Timestamp postTime) {
        this.postTime = postTime;
    }

    public User getUser() {
        return users;
    }

    public void setUser(User user) {
        this.users = user;
    }

    public String getCategory() {
        return categoryid;
    }

    public void setCategory(String category) {
        this.categoryid = category;
    }
    public JsonObject toJsonObject(){
        return Json.createObjectBuilder()
                    .add("title", title)
                    .add("category", categoryid)
                    .add("date", postTime.toString())
                    .add("content", content)
                    .add("user", users.getUserid())
                    .build();
    }
}
