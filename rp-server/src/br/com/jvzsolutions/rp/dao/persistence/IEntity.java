package br.com.jvzsolutions.rp.dao.persistence;

import java.io.Serializable;

/**
 * 
 */
public interface IEntity extends Serializable{

	/**
	 * 
	 * @return o id da entidade
	 */
	public Integer getId();
	
	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id);
}
