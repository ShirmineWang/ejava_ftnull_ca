package sg.edu.nus.iss.javaee.noticeboard.model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sg.edu.nus.iss.javaee.noticeboard.model.Category;
import sg.edu.nus.iss.javaee.noticeboard.model.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-04T14:51:48")
@StaticMetamodel(Note.class)
public class Note_ { 

    public static volatile SingularAttribute<Note, Timestamp> postTime;
    public static volatile SingularAttribute<Note, Integer> noteid;
    public static volatile SingularAttribute<Note, String> title;
    public static volatile SingularAttribute<Note, Category> category;
    public static volatile SingularAttribute<Note, String> content;
    public static volatile SingularAttribute<Note, User> users;

}