package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-24T22:55:28.652+0300")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, Boolean> active;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, Integer> afm;
	public static volatile SingularAttribute<User, String> city;
	public static volatile SingularAttribute<User, String> email;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> phone;
	public static volatile SingularAttribute<User, Integer> rattingBidder;
	public static volatile SingularAttribute<User, Integer> rattingSeller;
	public static volatile SingularAttribute<User, String> role;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Integer> zip;
	public static volatile SingularAttribute<User, Integer> new_inbox_messages;
	public static volatile SingularAttribute<User, Integer> new_sended_messages;
	public static volatile ListAttribute<User, Auction> auctions;
	public static volatile ListAttribute<User, Bid> bids;
	public static volatile ListAttribute<User, Message> sended_messages;
	public static volatile ListAttribute<User, Message> received_messages;
}
