package model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-04T00:41:19.607+0300")
@StaticMetamodel(Bid.class)
public class Bid_ {
	public static volatile SingularAttribute<Bid, Integer> id;
	public static volatile SingularAttribute<Bid, BigDecimal> amount;
	public static volatile SingularAttribute<Bid, Date> time;
	public static volatile SingularAttribute<Bid, Auction> auction;
	public static volatile SingularAttribute<Bid, User> user;
}
