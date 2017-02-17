package br.com.jvzsolutions.rp.dao.exception;

import br.com.jvzsolutions.rp.dao.persistence.ErrorTypes;

/**
 * @author jean
 */
public class ExcecaoGenericaDAO extends Exception {

	private ErrorTypes errorType;

	/**
	 * Cria uma exceção
	 * 
	 * @param message - uma mensagem para a exceção
	 */
	public ExcecaoGenericaDAO(String message) {
		super(message);
	}

	/**
	 * Cria uma exceção
	 */
	public ExcecaoGenericaDAO() {
		super();
	}

	/**
	 * @return tipo de erro
	 */
	public ErrorTypes getErrorType() {
		return errorType;
	}

	/**
	 * @param errorType
	 */
	public void setErrorType(ErrorTypes errorType) {
		this.errorType = errorType;
	}

}
