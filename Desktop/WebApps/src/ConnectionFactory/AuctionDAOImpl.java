package ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.mysql.jdbc.Statement;

import JpaUtils.EntityManagerHelper;
import model.Auction;
import model.Category;
import model.User;

public class AuctionDAOImpl implements AuctionDAO {

	@Override
	public void createAuction(Auction auction) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(auction);
			EntityManagerHelper.commit();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	/* Return all Auction with this categoriespath */
	@Override
	public List<Auction> searchAuctionByCategory(String categoriesPath) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();

			String[] category = categoriesPath.split("->");
			Query query1 = em.createNamedQuery("Category.findByName").setParameter("1", category[category.length - 1]);
			@SuppressWarnings("unchecked")
			List<Category> categoriesList = (List<Category>) query1.getResultList();
			List<Auction> auctionList = new ArrayList<Auction>();
			for(Category categ : categoriesList){
				auctionList.add(categ.getAuction());
			}
			EntityManagerHelper.commit();
			return auctionList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}
	
	@Override
	public List<Auction> searchAuctionByName(String name){
		try{
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Auction.searchByName").setParameter("1","%"+name+"%");
			@SuppressWarnings("unchecked")
			List<Auction> auctionList = (List<Auction>) query.getResultList();
			EntityManagerHelper.commit();
			return auctionList;
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}

	@Override
	public Auction searchAuctionById(int id) {
		try{
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Auction.searchById").setParameter("1", id);
			Auction auction = (Auction) query.getSingleResult();
			EntityManagerHelper.commit();
			return auction;
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
		return null;
	}
	
	@Override
	public void editCurrentlyAmmount(Auction auction) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Auction.editByCurrently").setParameter(1,auction.getCurrently()).setParameter(2,auction.getId());
			query.executeUpdate();
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}
	@Override
	public void editAuction(Auction auction) {
		try{
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
//			Query query = em.createNamedQuery("Auction.editAuction").setParameter("1",auction.getId()).setParameter("2",auction.getBuy_Price()).setParameter("3",auction.getCountry())
//																	.setParameter("4",auction.getEnds()).setParameter("5", auction.getStarted()).setParameter("6", auction.getDescription())
//																	.setParameter("7", auction.getLatitude()).setParameter("8", auction.getLocation()).setParameter("9", auction.getName())
//																	.setParameter("10", auction.getNumberOfBids()).setParameter("11", auction.getLongitude())
//																	.setParameter("12", auction.getUser());
			Query query = em.createNamedQuery("Auction.editAuction").setParameter(1,auction.getId()).setParameter(2,auction.getBuy_Price()).setParameter(3,auction.getCountry())
					.setParameter(4,auction.getEnds()).setParameter(5, auction.getStarted()).setParameter(6, auction.getDescription())
					.setParameter(7, auction.getLatitude()).setParameter(8, auction.getLocation()).setParameter(9, auction.getName())
					.setParameter(10, auction.getNumberOfBids()).setParameter(11, auction.getLongitude())
					.setParameter(12, auction.getUser());
			query.executeUpdate();
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
			
	}
	@Override
	public void deleteAuction(Long id){
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
//		Statement s = this.createStatement();
//		s.addBatch("SET FOREIGN_KEY_CHECKS = 0");
		Query query = em.createNamedQuery("Auction.delete").setParameter(1, id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
	
}
