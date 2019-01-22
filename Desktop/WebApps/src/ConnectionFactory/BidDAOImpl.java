package ConnectionFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import JpaUtils.EntityManagerHelper;
import model.Bid;
import model.User;

public class BidDAOImpl implements BidDAO {
	
	public BidDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void createBid(Bid new_bid){
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(new_bid);
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	@Override
	public Bid getBidByID(int id){
		
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Bid.findById").setParameter("1", id);
			try {
				Bid bid = (Bid) query.getSingleResult();
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
				return bid;
			} catch (NoResultException e) {
				System.out.println("Do not Register: " + e);
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return null;
	}
} 
