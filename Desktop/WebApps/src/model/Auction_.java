package model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-04T00:41:19.548+0300")
@StaticMetamodel(Auction.class)
public class Auction_ {
	public static volatile SingularAttribute<Auction, Integer> id;
	public static volatile SingularAttribute<Auction, BigDecimal> buy_Price;
	public static volatile SingularAttribute<Auction, String> country;
	public static volatile SingularAttribute<Auction, BigDecimal> currently;
	public static volatile SingularAttribute<Auction, String> description;
	public static volatile SingularAttribute<Auction, Date> ends;
	public static volatile SingularAttribute<Auction, BigDecimal> first_Bid;
	public static volatile SingularAttribute<Auction, String> latitude;
	public static volatile SingularAttribute<Auction, String> location;
	public static volatile SingularAttribute<Auction, String> longitude;
	public static volatile SingularAttribute<Auction, String> name;
	public static volatile SingularAttribute<Auction, Integer> numberOfBids;
	public static volatile SingularAttribute<Auction, Integer> sellerUsersId;
	public static volatile SingularAttribute<Auction, Date> started;
	public static volatile SingularAttribute<Auction, User> user;
	public static volatile ListAttribute<Auction, Bid> bids;
	public static volatile ListAttribute<Auction, Category> categories;
	public static volatile ListAttribute<Auction, Photo> photos;

}
