package ConnectionFactory;

import java.util.List;
import model.Category;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import JpaUtils.EntityManagerHelper;
import model.Categories;

public class CategoriesDAOImpl implements CategoriesDAO {

	private String parent;
	
	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	@Override
	public void createCategories(Categories categories) {
		System.out.println(categories);
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			em.persist(categories);
			EntityManagerHelper.commit();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	@Override
	public int findCategories(String name) {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Categories.find").setParameter("1",name);
			try {
				query.getSingleResult();
				EntityManagerHelper.commit();
			
				return 0;
			} catch (NoResultException e) {
				return 1;
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		return 0;
	}
	
	@Override
	public List<Categories> getFirstGrade() {
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		Query query = em.createNamedQuery("Categories.getFirstGrade").setParameter("1", 1);
		try {
			@SuppressWarnings("unchecked")
			List<Categories> categories = (List<Categories>) query.getResultList();
			EntityManagerHelper.commit();
		
			return categories;
		} catch (NoResultException e) {
			return null;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	@Override
	public List<Categories> findCategoriesByGrade(int grade,String parent) {
		//System.out.println(parent);
		EntityManager em = EntityManagerHelper.getEntityManager();
		EntityManagerHelper.beginTransaction();
		Query query = em.createNamedQuery("Categories.getNextGrade").setParameter("1",grade).setParameter("2", parent);
		try {
			@SuppressWarnings("unchecked")
			List<Categories> categories = (List<Categories>) query.getResultList();
			EntityManagerHelper.commit();
		
			return categories;
		} catch (NoResultException e) {
			return null;
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	public void editCategory(String name, int id){
		try{
			EntityManager em = EntityManagerHelper.getEntityManager();
			EntityManagerHelper.beginTransaction();
			Query query = em.createNamedQuery("Category.editCategory").setParameter("1",(id)).setParameter("2", name);
			query.executeUpdate();
			EntityManagerHelper.commit();
		} catch (NullPointerException e) {
			System.out.println(e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
}
