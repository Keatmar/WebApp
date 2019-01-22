package ConnectionFactory;

import javax.persistence.EntityManager;

import JpaUtils.EntityManagerHelper;
import model.Photo;

public class PhotoDAOImpl implements PhotoDAO{
	
	@Override
	public void createPhoto(Photo photo){
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(photo);
			EntityManagerHelper.commit();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			e.printStackTrace();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
}
