package unmarsalling;


import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Bids")
public class Binds {

	private ArrayList<Bid> bid;
	


	@XmlElement(name="Bid")
	public ArrayList<Bid> getBid() {
		if (null == bid) {
			bid = new ArrayList<Bid>();
		}
		return bid;
	}

	public void setBid(ArrayList<Bid> bid) {
		this.bid= bid;
	}

	
	@Override
	public String toString() {
		return "Binds [bid=" + bid + "]";
	}
	
	

}
