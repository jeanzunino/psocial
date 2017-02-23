package br.com.jvzsolutions.rp.dao.persistence;

import java.lang.reflect.Constructor;
import java.util.HashMap;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;

/**
 * Fábrica de DAO
 * 
 * @author Jean Zunino
 * 
 */
@SuppressWarnings("unchecked")
public class DAOFactory {
	private HashMap<Class<? extends IEntity>, IPersistenceOperationsDAO<IEntity>> daoList;
	private static HashMap<String, DAOFactory> INSTANCES;
	private String puName = "";

	private IPersistenceOperationsDAO<IEntity> loadDAO(Class<? extends IEntity> entityClass)
			throws ExcecaoGenericaDAO {
		Class<IEntity> clazz = null;
		try {
			clazz = (Class<IEntity>) Class.forName("br.com.jvzsolutions.rp.dao."
					+ entityClass.getSimpleName() + "DAO");
			Constructor<?> constructor = clazz.getConstructor(String.class);

			IPersistenceOperationsDAO<IEntity> dao = (IPersistenceOperationsDAO<IEntity>) constructor
					.newInstance(this.puName);
			if (dao == null)
				throw new ClassNotFoundException(clazz.getName());

			return dao;
		} catch (Exception e) {
			throw new ExcecaoGenericaDAO(
					"Erro ao carregar DAO.\n"
							+ e.getMessage());
		}
	}

	/**
	 * @param entityClass
	 * @return Uma implementação de {@link DAOObject}
	 * @throws GenericPersistenceException
	 */
	public IPersistenceOperationsDAO<? extends IEntity> createDAO(
			Class<? extends IEntity> entityClass)
			throws ExcecaoGenericaDAO {
		IPersistenceOperationsDAO<IEntity> dao = (IPersistenceOperationsDAO<IEntity>) this.daoList
				.get(entityClass);
		if (dao == null) {
			dao = this.loadDAO(entityClass);
			this.daoList.put(entityClass, dao);
		}
		return dao;
	}

	private DAOFactory(String puName) {
		this.daoList = new HashMap<Class<? extends IEntity>, IPersistenceOperationsDAO<IEntity>>();
		this.puName = puName;
	}

	

	static{
		INSTANCES = new HashMap<String, DAOFactory>();
	}
	/**
	 * @param puName
	 * @return
	 */
	public static DAOFactory getInstance(String puName) {
		DAOFactory dao = INSTANCES.get(puName);
		if (dao == null) {
			dao = new DAOFactory(puName);
			INSTANCES.put(puName, dao);
		}
		return dao;
	}

}
