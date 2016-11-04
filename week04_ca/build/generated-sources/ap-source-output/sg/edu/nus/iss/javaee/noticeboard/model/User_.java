package sg.edu.nus.iss.javaee.noticeboard.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-04T14:51:48")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, Note> notes;
    public static volatile SingularAttribute<User, String> userid;

}