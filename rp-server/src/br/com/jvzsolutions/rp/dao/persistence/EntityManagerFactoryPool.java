package br.com.jvzsolutions.rp.dao.persistence;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Gerencia os EntitymanagerFactory<br>
 * Faz um papel de Pool de Conexões com as bases de dados<br>
 * Mantém uma conexão com cada base
 * 
 * @author Jean Zunino
 * 
 */
public class EntityManagerFactoryPool {
	private static HashMap<String, EntityManagerFactory> emfList = new HashMap<String, EntityManagerFactory>();;
	private EntityManagerFactoryPool() {
	}

	/**
	 * Fecha a conexão com o Banco
	 * 
	 * @param puName - a base de Dados
	 */
	public static void closeEntityManagerFactory(String puName) {
		if (emfList != null) {
			EntityManagerFactory emf = emfList.remove(puName);
			if (emf != null)
				if (emf.isOpen()) {
					System.out.println("Fechando EntityManager em " + puName);
					emf.close();
				}
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory(String puName) {
		EntityManagerFactory emf = emfList.get(puName);
		if (emf == null) {
			System.out.println("Criando EntityManager para " + puName);
			try {
				emf = Persistence.createEntityManagerFactory(puName);
				System.out.println("\n**** EntityManagerFactory criado, puName:"+ puName);
				emfList.put(puName, emf);
			} catch (Exception e) {
				System.err.println("\n*********Erro ao criar o EntityManagerFactory."+ e.getCause().getMessage()+ "******\n\n"+ e.getMessage());
				throw new RuntimeException("Erro ao criar o EntityManagerFactory.", e);
			}
		}
		return emf;
	}

}
