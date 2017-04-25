package br.com.jvzsolutions.rp.dao.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Query;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;

/**
 * @author usuario
 * 
 * @param <TEntity>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<TEntity extends IEntity> implements IPersistenceOperationsDAO<TEntity> {

	private Class<TEntity> entityClass;

	private String puName;

	/**
	 * @param puName
	 */
	public AbstractDAO(String puName) {
		this.entityClass = (Class<TEntity>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.puName = puName.toLowerCase();
	}

	private ExcecaoGenericaDAO createGenericPersistenceException(Exception ex) {
		ExcecaoGenericaDAO pEx = new ExcecaoGenericaDAO(ex.getMessage());
		if (ex.getMessage().indexOf(ErrorTypes.TRANSACTION_NOT_ACTIVE.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.TRANSACTION_NOT_ACTIVE);
		else if (ex.getMessage().indexOf(ErrorTypes.CANNOT_OPEN_CONNECTION.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.CANNOT_OPEN_CONNECTION);
		else if (ex.getMessage().indexOf(ErrorTypes.ENTITY_NOT_FOUND.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.ENTITY_NOT_FOUND);
		else if (ex.getMessage().indexOf(ErrorTypes.UNABLE_TO_CONFIGURE_EMF.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.UNABLE_TO_CONFIGURE_EMF);
		else if (ex.getMessage().indexOf(ErrorTypes.UNABLE_TO_BUILD_EMF.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.UNABLE_TO_BUILD_EMF);
		else if (ex.getMessage().indexOf(ErrorTypes.DETACHED_ENTITY.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.DETACHED_ENTITY);
		else if (ex.getMessage().indexOf(ErrorTypes.NO_PERSISTENCE_PROVIDER.getDesc()) >= 0)
			pEx.setErrorType(ErrorTypes.NO_PERSISTENCE_PROVIDER);

		return pEx;
	}

	private void setNullEntityId(TEntity entity) {
		try {
			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Id.class) && !field.isAnnotationPresent(JoinColumn.class)) {
					field.setAccessible(true);
					field.set(entity, null);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private long getEntityId(TEntity entity) {
		try {
			Field[] fields = entity.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(Id.class)) {
					String getMethodStr = "get" + field.getName().substring(0, 1).toUpperCase()
							+ field.getName().substring(1, field.getName().length());

					Method getMethod = null;

					getMethod = entity.getClass().getMethod(getMethodStr);
					Long returnValue = (Long) getMethod.invoke(entity, (Object[]) null);
					if (returnValue != null)
						return returnValue;
					else
						return -1;
				}
			}
		} catch (Exception ex) {
			return -1;
		}

		return -1;
	}

	public List<TEntity> search(OrderType orderType) throws ExcecaoGenericaDAO {
		List<TEntity> returnList;
		Object[] parameters = {};
		if (orderType == OrderType.Asc)
			returnList = this.executeQuery(
					"select obj from " + entityClass.getSimpleName() + " obj order by obj.id asc", parameters);
		else
			returnList = this.executeQuery(
					"select obj from " + entityClass.getSimpleName() + " obj order by obj.id desc", parameters);
		return returnList;
	}

	@Override
	public TEntity update(TEntity entity) throws ExcecaoGenericaDAO {
		EntityManager em = null;
		try {
			em = EntityManagerFactoryPool.getEntityManagerFactory(this.puName).createEntityManager();
			em.getTransaction().begin();
			entity = em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception ex) {
			if (em != null)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();

			throw (ExcecaoGenericaDAO) this.createGenericPersistenceException(ex).initCause(ex);
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void save(TEntity entity) throws ExcecaoGenericaDAO {
		EntityManager em = null;
		try {
			EntityManagerFactory entityManagerFactory = EntityManagerFactoryPool.getEntityManagerFactory(getPuName());
			em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();
			setNullEntityId(entity);
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();

			throw (ExcecaoGenericaDAO) this.createGenericPersistenceException(ex).initCause(ex);
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public void delete(TEntity entity) throws ExcecaoGenericaDAO {
		EntityManager em = null;
		try {
			em = EntityManagerFactoryPool.getEntityManagerFactory(this.puName).createEntityManager();
			em.getTransaction().begin();
			entity = (TEntity) em.find(entity.getClass(), this.getEntityId(entity));
			if (entity == null) {
				throw new Exception(ErrorTypes.ENTITY_NOT_FOUND.getDesc());
			}
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null)
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			throw (ExcecaoGenericaDAO) this.createGenericPersistenceException(ex).initCause(ex);
		} finally {
			if (em != null)
				em.close();
		}
	}

	@Override
	public ArrayList<TEntity> executeNamedQuery(String namedQuery, Object[] parameters) throws ExcecaoGenericaDAO {
		EntityManager em = null;
		ArrayList<TEntity> returnList = new ArrayList<TEntity>();
		try {
			em = EntityManagerFactoryPool.getEntityManagerFactory(this.puName).createEntityManager();
			em.getTransaction().begin();
			Query query = em.createNamedQuery(namedQuery);
			if (parameters != null)
				for (int count = 0; count < parameters.length; count++)
					query.setParameter((count + 1), parameters[count]);

			returnList = (ArrayList<TEntity>) query.getResultList();
			em.getTransaction().commit();
		} catch (Exception ex) {
			returnList.clear();
			if (em != null)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();

			throw (ExcecaoGenericaDAO) this.createGenericPersistenceException(ex).initCause(ex);
		}

		return returnList;
	}

	@Override
	public ArrayList<TEntity> executeQuery(String jpql, Object[] parameters) throws ExcecaoGenericaDAO {
		EntityManager em = null;
		ArrayList<TEntity> returnList = new ArrayList<TEntity>();
		try {
			em = EntityManagerFactoryPool.getEntityManagerFactory(this.puName).createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery(jpql);
			if (parameters != null)
				for (int count = 0; count < parameters.length; count++)
					query.setParameter((count + 1), parameters[count]);

			returnList = (ArrayList<TEntity>) query.getResultList();
			em.getTransaction().commit();
		} catch (Exception ex) {
			returnList.clear();
			if (em != null)
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();

			throw (ExcecaoGenericaDAO) this.createGenericPersistenceException(ex).initCause(ex);
		}

		return returnList;
	}

	/**
	 * Fecha a conexão com a base dados
	 */
	public void close() {
		EntityManagerFactoryPool.closeEntityManagerFactory(puName);
	}

	/**
	 * @return O nome da base de dados
	 */
	public String getPuName() {
		return puName;
	}

	/**
	 * @param puName
	 *            - O nome da base de dados
	 */
	public void setPuName(String puName) {
		this.puName = puName;
	}
}
