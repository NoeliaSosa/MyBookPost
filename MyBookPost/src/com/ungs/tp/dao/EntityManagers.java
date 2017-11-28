package com.ungs.tp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagers {
	
	
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("org.hibernate.tp.jpa");
	
	
	private EntityManagers()
	{			
	}
	
	public static EntityManager createEntityManager() {
	
		return entityManagerFactory.createEntityManager();
	}
	

}
