package ConnectionFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import JpaUtils.EntityManagerHelper;
import model.Auction;
import model.Message;
import model.User;

public class MessageDAOImpl implements MessageDAO {
	
	@Override
	public void createMessage(Message message) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(message);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	@Override
	public Message searchMessageById(int id) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Message.searchById").setParameter("1", id);
			try {
				Message message= (Message) query.getSingleResult();
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
				return message;
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
	
	@Override
	public void editMessage(Message message) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Message.editMessage").setParameter(1,message.getText()).setParameter(2,message.getSubject()).setParameter(3,message.getId());
			query.executeUpdate();
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	}
	@Override
	public void deleteMessage(int id){
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
//		Statement s = this.createStatement();
//		s.addBatch("SET FOREIGN_KEY_CHECKS = 0");
		Query query = em.createNamedQuery("Message.delete").setParameter(1, id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
