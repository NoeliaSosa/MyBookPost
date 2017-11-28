package org.hibernate.dao.impl;

import javax.persistence.EntityManager;

import org.hibernate.beans.Usuario;
import org.hibernate.dao.EntityManagers;
import org.hibernate.dao.UsuarioDao;

public class UsuarioDaoImpl extends DaoImpl<Usuario> implements UsuarioDao {
	
	
	public Usuario getUsuario(String userName){
			EntityManager entityManager = EntityManagers.createEntityManager();
			Usuario ret = (Usuario)entityManager.createQuery("SELECT * FROM USUARIO WHERE nombreUsuario = "+userName).getSingleResult();
			return ret;
		}
	

}
