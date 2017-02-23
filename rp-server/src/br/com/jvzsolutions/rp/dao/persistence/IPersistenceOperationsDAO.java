package br.com.jvzsolutions.rp.dao.persistence;

import java.util.ArrayList;
import java.util.List;

import br.com.jvzsolutions.rp.dao.exception.ExcecaoGenericaDAO;

/**
 * Interface para os DAOS<br>
 * Define os métodos que são comum a todos os DAO
 * 
 * @author Jean
 * 
 * @param <TEntity>
 */
public interface IPersistenceOperationsDAO<TEntity extends IEntity> {
	/**
	 * @param entity - A entidade para ser salva
	 * @throws GenericPersistenceException Caso ocorra algum erro na operação
	 *             com a base
	 */
	public void save(TEntity entity) throws ExcecaoGenericaDAO;

	/**
	 * @param entity - a entidade para ser alterada
	 * @return A entidade alterada
	 * @throws GenericPersistenceException Caso ocorra algum erro na operação
	 *             com a base
	 */
	public TEntity update(TEntity entity) throws ExcecaoGenericaDAO;

	/**
	 * @param entity - a entidade para ser excluída
	 * @throws GenericPersistenceException Caso ocorra algum erro na operação
	 *             com a base
	 */
	public void delete(TEntity entity) throws ExcecaoGenericaDAO;

	/**
	 * @param namedQuery - A query nativa para ser executada
	 * @param parameters - o Array de parâmetros
	 * @return Uma lista com as entidades encontradas
	 * @throws GenericPersistenceException Caso ocorra algum erro na operação
	 *             com a base
	 */
	public List<TEntity> executeNamedQuery(String namedQuery,
			Object[] parameters) throws ExcecaoGenericaDAO;

	/**
	 * @param jpql - A jpql para ser executada
	 * @param parameters - o Array de parâmetros
	 * @return Uma lista com as entidades encontradas
	 * @throws GenericPersistenceException Caso ocorra algum erro na operação
	 *             com a base
	 */
	public List<TEntity> executeQuery(String jpql, Object[] parameters)
			throws ExcecaoGenericaDAO;
	
	/**
	 * @param clazze 
	 * @param orderType
	 * @return A lista de registros ordenado pela ordem desejada
	 * @throws GenericPersistenceException
	 */
	public List<TEntity> search(OrderType orderType)
			throws ExcecaoGenericaDAO;

}
