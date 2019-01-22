package unmarsalling;

import javax.xml.bind.annotation.XmlElement;

public class Bid {
	private String time;
	private String Amount;
	private Bidder bidder;

	
	@XmlElement(name="Bidder")
	public Bidder getBidder() {
		return bidder;
	}


	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	@XmlElement(name="Time")
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}

	@XmlElement(name="Amount")
	public String getAmount() {
		return Amount;
	}
	
	public void setAmount(String amount) {
		Amount = amount;
	}


	@Override
	public String toString() {
		return "Bid[" + bidder+ "[time=" + time + ", Amount=" + Amount + "]";
	}
	
	
	
	
}
