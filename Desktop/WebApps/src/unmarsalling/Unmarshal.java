package unmarsalling;

import java.io.FileReader;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import ConnectionFactory.AuctionDAO;
import ConnectionFactory.AuctionDAOImpl;
import model.Auction;
import model.Bid;
import model.Category;

public class Unmarshal {

	private Part file;

	public void UnmarshalXml() throws Exception {
		JAXBContext context = JAXBContext.newInstance(Items.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Items items = (Items) unmarshaller.unmarshal((file.getInputStream()));

		Auction auction = new Auction();
		List<Item> itemList = items.getItems();
		List<String> listCategory = new ArrayList<String>();
		List<unmarsalling.Bid> listBid = new ArrayList<unmarsalling.Bid>();

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		String pattern = "##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);
		DateFormat inFormat = new SimpleDateFormat("MMM-dd-yy hh:mm:ss");
		String tempStr;

		if (!itemList.isEmpty()) {
			for (Object object : itemList) {
				Item item = (Item) object;

				auction.setName(item.getName());

				// auction.setId(item.getId());

				if (item.getFirst_Bid() != null) {
					tempStr = item.getFirst_Bid().replace("$", "");
					BigDecimal firstBid = (BigDecimal) decimalFormat.parse(tempStr);
					auction.setFirst_Bid(firstBid);
				}

				if (item.getBuy_Price() != null) {
					tempStr = item.getBuy_Price().replace("$", "");
					BigDecimal buyPrice = (BigDecimal) decimalFormat.parse(tempStr);
					auction.setBuy_Price(buyPrice);
				}

				if (item.getCurrently() != null) {
					tempStr = item.getCurrently().replace("$", "");
					BigDecimal currently = (BigDecimal) decimalFormat.parse(tempStr);
					auction.setCurrently(currently);
				}
				auction.setStarted(inFormat.parse(item.getStarted()));
				auction.setDescription(item.getDescription());

				if (item.getLocation().getLatitude() != null) {
					auction.setLatitude(item.getLocation().getLatitude());
				}

				auction.setCountry(item.getCountry());
				if (item.getLocation().getLongitude() != null) {
					auction.setLongitude(item.getLocation().getLongitude());
				}

				auction.setLocation(item.getLocation().getLocation());
				auction.setNumberOfBids(item.getNumberOfBinds());
				

				listCategory = item.getCategory();
				List<Category> category_init = new ArrayList<Category>();
				auction.setCategories(category_init);
				Category[] categories = new Category[listCategory.size()];
				int i = 0;
				for (Object obj1 : listCategory) {
					String category = (String) obj1;
					categories[i] = new Category();
					categories[i].setGrade(i+1);
					categories[i].setName(category);
					categories[i].setAuction(auction);
					auction.addCategory(categories[i]);
					i++;
				}
				
				if (!(item.getBinds().getBid().isEmpty())) {
					listBid = item.getBinds().getBid();
					List<Bid> bid_init = new ArrayList<Bid>();
					auction.setBids(bid_init);
					Bid[] bids = new Bid[listBid.size()];
					i = 0;
					for (Object obj1 : listBid) {
						unmarsalling.Bid bid = (unmarsalling.Bid) obj1;
						tempStr = bid.getAmount().replace("$", "");
						BigDecimal amount = (BigDecimal) decimalFormat.parse(tempStr);

						bids[i] = new Bid();
						bids[i].setAmount(amount);
						bids[i].setAuction(auction);
						
						//Date time = inFormat.parse(bid.getTime());
						bids[i].setTime(inFormat.parse(bid.getTime()));
						auction.addBid(bids[i]);
						i++;
					}
				}

				AuctionDAO auctionDAO = new AuctionDAOImpl();
				auctionDAO.createAuction(auction);

			}
		}
		// System.out.println(items);

	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

}
