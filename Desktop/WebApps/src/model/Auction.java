package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the auction database table.
 * 
 */
@Entity
@Table(name="auction")
@NamedQueries(value = { @NamedQuery(name = "Auction.findAll", query = "SELECT a FROM Auction a"),
		@NamedQuery(name = "Auction.searchById", query = "SELECT a FROM Auction a WHERE a.id = ?1"),
		@NamedQuery(name = "Auction.searchByName", query = "SELECT a FROM Auction a WHERE a.name LIKE ?1"),
		@NamedQuery(name =  "Auction.editByCurrently", query = "UPDATE Auction au SET au.currently= ?1 WHERE au.id = ?2"),
		@NamedQuery(name = "Auction.editAuction", query = "UPDATE Auction a SET "
							+ " a.buy_Price= ?2, a.country= ?3, a.ends= ?4,"
							+ "a.started= ?5, a.description= ?6, a.latitude= ?7, a.location= ?8," + "a.name= ?9, a.numberOfBids= ?10,"
							+"a.longitude = ?11, a.user= ?12" + " WHERE a.id= ?1"),
		@NamedQuery(name = "Auction.delete", query = "DELETE FROM Auction a WHERE a.id= ?1"),})

public class Auction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private BigDecimal buy_Price;

	private String country;

	private BigDecimal currently;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ends;

	private BigDecimal first_Bid;

	private String latitude;

	private String location;

	private String longitude;

	private String name;

	@Column(name = "number_of_bids")
	private int numberOfBids;

	@Column(name = "seller_users_id")
	private int sellerUsersId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date started;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id")
	private User user;

	// bi-directional many-to-one association to Bid
	@OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
	private List<Bid> bids;

	// bi-directional many-to-one association to Category
	@OneToMany(mappedBy = "auction", cascade = CascadeType.PERSIST)
	private List<Category> categories;

	// bi-directional many-to-one association to Photo
	@OneToMany(mappedBy = "auction", cascade = CascadeType.PERSIST)
	private List<Photo> photos;

	public Auction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getBuy_Price() {
		return this.buy_Price;
	}

	public void setBuy_Price(BigDecimal buy_Price) {
		this.buy_Price = buy_Price;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getCurrently() {
		return this.currently;
	}

	public void setCurrently(BigDecimal currently) {
		
		this.currently = currently;
		
	}	
	
	public void changeCurrently(BigDecimal currently){
		if (this.currently.equals(0)){
			this.currently = currently;
		}
		else if (this.currently.compareTo(currently) == -1){
			this.currently = currently;
		}
		
	}
	
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnds() {
		return this.ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public BigDecimal getFirst_Bid() {
		return this.first_Bid;
	}

	public void setFirst_Bid(BigDecimal first_Bid) {
		this.first_Bid = first_Bid;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberOfBids() {
		return this.numberOfBids;
	}

	public void setNumberOfBids(int numberOfBids) {
		this.numberOfBids = numberOfBids;
	}

	public int getSellerUsersId() {
		return this.sellerUsersId;
	}

	public void setSellerUsersId(int sellerUsersId) {
		this.sellerUsersId = sellerUsersId;
	}

	public Date getStarted() {
		return this.started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setAuction(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setAuction(null);

		return bid;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setAuction(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setAuction(null);

		return category;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setAuction(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setAuction(null);

		return photo;
	}

	
	public Boolean is_Purchased(){
		Date now = new Date();
		System.out.println(now);
		System.out.println(this.getEnds());
		if(this.getEnds().equals(null)){
			return true;
		}
		else{
			if (this.getEnds().before(now)){
				System.out.println("true");
				return true;
			}
			else {
				System.out.println("false");
				return false;
			}
		}
	}
	
	public String getStatus(){
		if (this.getBids().size() == 0 && !this.is_Purchased()){
			return "Active_Without_Bid";
		}
		else if (!this.is_Purchased()){
			return "Active_With_Bids";
		}
		else if(this.is_Purchased()){
			return "Purchased";
			
		}
		else {
			return "Inactive";
		} 
	}
	
	public int getWinningBidderId (){
		List<Bid> auctions_bid = this.getBids();
		if (!auctions_bid.isEmpty()){
			if(this.is_Purchased()){
				for(Bid bid : auctions_bid){
					
					if (this.getCurrently().equals(bid.getAmount())){
						
					
		 				return  bid.getUser().getId();
					}
				}
			}
		}
		
		return 0;
		
	}
		
	
	
}