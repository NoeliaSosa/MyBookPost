package org.hibernate.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.beans.Post;
import org.hibernate.beans.Usuario;
import org.hibernate.dao.EntityManagers;
import org.hibernate.dao.PostDao;


public class PostDaoImpl extends DaoImpl<Post> implements PostDao {

	
	@SuppressWarnings("unchecked")
	public List<Post> obtenerPostsByUsuario(Usuario usuario){
		EntityManager entityManager = EntityManagers.createEntityManager();
		List<Post> ret = (List<Post>) entityManager.createQuery("SELECT * FROM POST p WHERE p.USER.ID = "+usuario.getId());
		return ret;
	}

}
