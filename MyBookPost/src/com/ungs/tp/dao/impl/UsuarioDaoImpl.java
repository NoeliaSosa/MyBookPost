package com.ungs.tp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ungs.tp.beans.Usuario;
import com.ungs.tp.dao.EntityManagers;
import com.ungs.tp.dao.UsuarioDao;

public class UsuarioDaoImpl extends DaoImpl<Usuario> implements UsuarioDao {
	
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuario(String userName){
			EntityManager entityManager = EntityManagers.createEntityManager();
			Query query = entityManager.createQuery("select u FROM Usuario u WHERE u.nombreUsuario = :user");
			query.setParameter("user", userName);
			List<Usuario> results=(List<Usuario>)query.getResultList();
			return results;
		}
	

}
