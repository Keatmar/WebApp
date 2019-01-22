package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-24T22:55:28.601+0300")
@StaticMetamodel(Message.class)
public class Message_ {
	public static volatile SingularAttribute<Message, Integer> id;
	public static volatile SingularAttribute<Message, Date> date_created;
	public static volatile SingularAttribute<Message, Date> date_updated;
	public static volatile SingularAttribute<Message, String> text;
	public static volatile SingularAttribute<Message, String> subject;
	public static volatile SingularAttribute<Message, Boolean> is_read;
	public static volatile SingularAttribute<Message, User> sender;
	public static volatile SingularAttribute<Message, User> receiver;
}
