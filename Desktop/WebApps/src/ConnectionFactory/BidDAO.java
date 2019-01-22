package ConnectionFactory;

import model.Bid;

public interface BidDAO {
	public void createBid(Bid new_bid);
	public Bid getBidByID(int id);
}
