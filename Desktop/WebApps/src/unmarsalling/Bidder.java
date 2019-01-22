package unmarsalling;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Bidder")
public class Bidder {
	
	private int ratting;
	private String userId;
	private String location;
	private String country;

	@XmlAttribute(name="Rating")
	public int getRatting() {
		return ratting;
	}


	public void setRatting(int ratting) {
		this.ratting = ratting;
	}

	@XmlAttribute(name="UserID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@XmlElement(name="Location")
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "Bidder [ratting=" + ratting + ", userId=" + userId + ", location=" + location + ", country=" + country
				+ "]";
	}
	
	
	
	
}
