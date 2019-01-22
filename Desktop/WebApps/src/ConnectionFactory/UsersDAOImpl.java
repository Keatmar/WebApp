package ConnectionFactory;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import JpaUtils.EntityManagerHelper;
import model.User;

public class UsersDAOImpl implements UsersDAO {

	@Override
	public List<User> list() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		Query query = em.createNamedQuery("User.findAll");
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) query.getResultList();
		EntityManagerHelper.commit();
		EntityManagerHelper.closeEntityManager();
		return users;
	}

	@Override
	public User getUserByEmailOrUsername(String input) {

		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("User.getUserByEmail").setParameter("1", input);
			try {
				User user = (User) query.getSingleResult();
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
				return user;
			} catch (NoResultException e) {
				System.out.println("Did not found user with that email: " + e);
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

		return null;
	}
	
	
	
	@Override
	public void createUser(User users) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(users);
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public User loginUser(String username, String password) {

		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("User.login").setParameter("1", username).setParameter("2", password);
			try {
				User user = (User) query.getSingleResult();
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
				return user;
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
	public void editUser(User user) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("User.edit").setParameter("1", user.getFirstName())
					.setParameter(2, user.getLastName()).setParameter(3, user.getUsername())
					.setParameter(4, user.getPassword()).setParameter(5, user.getPhone())
					.setParameter(6, user.getAddress()).setParameter(7, user.getCity()).setParameter(8, user.getZip())
					.setParameter(9, user.getAfm()).setParameter(10, user.getEmail()).setParameter(11, user.getId())
					.setParameter(12, user.getNewInboxMessages()).setParameter(13, user.getNewSendedMessages());
			query.executeUpdate();
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}

	}
	
	@Override
	public void confirmUser(int id) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("User.confirm").setParameter("1", id);
			query.executeUpdate();
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {

			System.out.println(e);

		} finally {

			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public void deleteUser(Long id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("User.delete").setParameter("1", id);
		query.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public User searchUserById(int id) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("User.searchById").setParameter("1", id);
			try {
				User user = (User) query.getSingleResult();
				EntityManagerHelper.commit();
				EntityManagerHelper.closeEntityManager();
				return user;
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
