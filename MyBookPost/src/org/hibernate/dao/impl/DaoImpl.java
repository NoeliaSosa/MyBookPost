package org.hibernate.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.dao.DAO;
import org.hibernate.dao.EntityManagers;



public class DaoImpl<T> implements DAO<T>{
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public DaoImpl() {

		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	public void crear(T t) {
		EntityManager entityManager = EntityManagers.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		
	}
	
	public void actualizar(T t) {
		EntityManager entityManager = EntityManagers.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();		
	}

	public void eliminar(T t) {
		EntityManager entityManager = EntityManagers.createEntityManager();
		entityManager.getTransaction().begin();
		t = entityManager.merge(t);
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	public T porId(long id) {
		EntityManager entityManager = EntityManagers.createEntityManager();
		entityManager.getTransaction().begin();

		T resultado = (T) entityManager.find(entityClass, id);

		entityManager.getTransaction().commit();

		return resultado;
	}

	public List<T> todos() {
		EntityManager entityManager = EntityManagers.createEntityManager();
		entityManager.getTransaction().begin();

		@SuppressWarnings("unchecked")
		List<T> resultados = entityManager.createQuery(
				"from " + entityClass.getName()).getResultList();

		entityManager.getTransaction().commit();

		return resultados;
	}



	

}
