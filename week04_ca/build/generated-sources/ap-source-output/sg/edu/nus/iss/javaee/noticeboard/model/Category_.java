package sg.edu.nus.iss.javaee.noticeboard.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sg.edu.nus.iss.javaee.noticeboard.model.Note;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-04T14:01:19")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, String> categoryname;
    public static volatile ListAttribute<Category, Note> noteList;
    public static volatile SingularAttribute<Category, Integer> categoryid;

}