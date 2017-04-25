package br.com.jvzsolutions.rp.services;

import java.util.List;

import com.sun.jersey.api.JResponse;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;
import br.com.jvzsolutions.rp.dao.persistence.DAOFactory;
import br.com.jvzsolutions.rp.dao.persistence.IEntity;
import br.com.jvzsolutions.rp.dao.persistence.IPersistenceOperationsDAO;
import br.com.jvzsolutions.rp.dao.persistence.OrderType;

public class AbstractService<T extends IEntity> {

	public JResponse<List<T>> getAll(Class<T> entityClass) throws Throwable {
		DAOFactory instance = DAOFactory.getInstance(ConfigConstants.puName);
		IPersistenceOperationsDAO<T> dao = (IPersistenceOperationsDAO<T>) instance.createDAO(entityClass);
		List<T> search = dao.search(OrderType.Asc);
		return JResponse.ok(search).build();
	}
	
	public T getById(Class<T> entityClass, long id) throws Throwable {
		DAOFactory instance = DAOFactory.getInstance(ConfigConstants.puName);
		IPersistenceOperationsDAO<T> dao = (IPersistenceOperationsDAO<T>) instance.createDAO(entityClass);
		List<T> search = dao.executeNamedQuery(entityClass.getSimpleName()+".searchById", new Object[]{id});
		if(search.isEmpty()){
			return null;
		}
		return search.get(0);
	}
	
	protected IPersistenceOperationsDAO<T> getDao(Class<T> entityClass) throws ExcecaoGenericaDAO{
		DAOFactory instance = DAOFactory.getInstance(ConfigConstants.puName);
		IPersistenceOperationsDAO<T> dao = (IPersistenceOperationsDAO<T>) instance.createDAO(entityClass);
		return dao;
	}
	
}
