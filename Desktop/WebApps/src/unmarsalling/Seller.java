package unmarsalling;

import javax.xml.bind.annotation.XmlAttribute;

public class Seller {

	private int ratting;
	private String userID;

	@XmlAttribute(name="Ratting")
	public int getRatting() {
		return ratting;
	}

	public void setRatting(int ratting) {
		this.ratting = ratting;
	}
	
	@XmlAttribute(name="UserID")
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}

	
	@Override
	public String toString() {
		return "Seller [ratting=" + ratting + ", userID=" + userID + "]";
	}
	
	
}
