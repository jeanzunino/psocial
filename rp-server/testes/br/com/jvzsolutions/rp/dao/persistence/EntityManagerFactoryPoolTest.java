package br.com.jvzsolutions.rp.dao.persistence;

import static org.junit.Assert.*;

import javax.persistence.EntityManagerFactory;

import org.junit.Test;

public class EntityManagerFactoryPoolTest {

	@Test
	public void getEntityManagerFactoryTest() {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryPool.getEntityManagerFactory("psocial");
		assertNotNull("Não foi possivel criar o EntityManagerFactory", entityManagerFactory);
		entityManagerFactory.createEntityManager();
		
		EntityManagerFactoryPool.getEntityManagerFactory("psocial");
		
		entityManagerFactory.createEntityManager();
	}

}
