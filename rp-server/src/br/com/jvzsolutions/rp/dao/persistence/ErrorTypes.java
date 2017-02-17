package br.com.jvzsolutions.rp.dao.persistence;

/**
 * Enumera��o com os tipos de erros de Persist�ncia
 * 
 * @author Jean
 * 
 */
public enum ErrorTypes {
	/***/
	NO_PERSISTENCE_PROVIDER("No Persistence provider for EntityManager"),

	/***/
	TRANSACTION_NOT_ACTIVE("Transaction not active"),

	/***/
	CANNOT_OPEN_CONNECTION("Cannot open connection"),

	/***/
	ENTITY_NOT_FOUND("Entity not found"),

	/***/
	UNABLE_TO_CONFIGURE_EMF("Unable to configure EntityManagerFactory"),

	/***/
	UNABLE_TO_BUILD_EMF("Unable to build EntityManagerFactory"),

	/***/
	DETACHED_ENTITY("detached entity passed to persist");

	private String desc;

	private ErrorTypes(String desc) {
		this.desc = desc;
	}

	/**
	 * 
	 * @return A descri��o do erro
	 */
	public String getDesc() {
		return desc;
	}

}