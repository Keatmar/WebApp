package unmarsalling;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Item")
public class Item {

	private int id;
	private List<String> category;
	private String first_Bid;
	private String currently;
	private int numberOfBinds;
	private Location location;
	private String description;
	private String country;
	private String ends;
	private String started;
	private String name;
	private Seller seller;
	private Binds binds;
	private String buy_Price;

	@XmlAttribute(name = "ItemID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name = "Category")
	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "Buy_Price")
	public String getBuy_Price() {
		return buy_Price;
	}

	public void setBuy_Price(String buy_Price) {
		this.buy_Price = buy_Price;
	}

	@XmlElement(name = "Started")
	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	@XmlElement(name = "Number_of_Bids")
	public int getNumberOfBinds() {
		return numberOfBinds;
	}

	public void setNumberOfBinds(int numberOfBinds) {
		this.numberOfBinds = numberOfBinds;
	}

	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@XmlElement(name = "Currently")
	public String getCurrently() {
		return currently;
	}

	public void setCurrently(String currently) {
		this.currently = currently;
	}

	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "Ends")
	public String getEnds() {
		return ends;
	}

	public void setEnds(String ends) {
		this.ends = ends;
	}

	@XmlElement(name = "First_Bid")
	public String getFirst_Bid() {
		return first_Bid;
	}

	public void setFirst_Bid(String first_Bid) {
		this.first_Bid = first_Bid;
	}

	@XmlElement(name = "Location")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@XmlElement(name = "Seller")
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	@XmlElement(name = "Bids")
	public Binds getBinds() {
		return binds;
	}

	public void setBinds(Binds binds) {
		this.binds = binds;
	}

	@Override
	public String toString() {
		return "\nItem id=" + id + ",Name=" + name + ", Category=" + category + ", First_Bid=" + first_Bid
				+ ", Buy_Price= " + buy_Price + ", Currently=" + currently + ", Number_Of_Binds=" + numberOfBinds
				+ ", Country=" + country + "," + location + ", Ends=" + ends + ", Started=" + started + ", " + seller
				+ "Bidds["  +binds + "Description=" + description + "]";
	}

}
