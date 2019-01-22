package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-09-04T00:41:19.609+0300")
@StaticMetamodel(Photo.class)
public class Photo_ {
	public static volatile SingularAttribute<Photo, Integer> id;
	public static volatile SingularAttribute<Photo, byte[]> photo;
	public static volatile SingularAttribute<Photo, Auction> auction;
}
