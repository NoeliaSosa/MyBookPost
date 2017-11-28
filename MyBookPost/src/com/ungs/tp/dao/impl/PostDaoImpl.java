package com.ungs.tp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.ungs.tp.beans.Post;
import com.ungs.tp.beans.Usuario;
import com.ungs.tp.dao.EntityManagers;
import com.ungs.tp.dao.PostDao;


public class PostDaoImpl extends DaoImpl<Post> implements PostDao {

	
	@SuppressWarnings("unchecked")
	public List<Post> obtenerPostsByUsuario(Usuario usuario){
		EntityManager entityManager = EntityManagers.createEntityManager();
		List<Post> ret = (List<Post>) entityManager.createQuery("SELECT * FROM POST p WHERE p.USER.ID = "+usuario.getId());
		return ret;
	}

}
