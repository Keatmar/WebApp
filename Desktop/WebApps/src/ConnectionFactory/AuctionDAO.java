package ConnectionFactory;

import java.util.List;
import model.Auction;

public interface AuctionDAO {
	public void createAuction(Auction auction);
	public List<Auction> searchAuctionByCategory(String categoriesPath);
	public List<Auction> searchAuctionByName(String name);
	public Auction searchAuctionById(int id);
	public void editCurrentlyAmmount(Auction auction);
	public void editAuction(Auction auction);
	public void deleteAuction(Long id);
}
