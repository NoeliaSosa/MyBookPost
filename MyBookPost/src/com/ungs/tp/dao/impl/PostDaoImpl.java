package com.ungs.tp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ungs.tp.beans.Post;
import com.ungs.tp.beans.Usuario;
import com.ungs.tp.dao.EntityManagers;
import com.ungs.tp.dao.PostDao;


public class PostDaoImpl extends DaoImpl<Post> implements PostDao {

	
	@SuppressWarnings("unchecked")
	public List<Post> obtenerPostsByUsuario(Usuario usuario){
		EntityManager entityManager = EntityManagers.createEntityManager();
		Query query= entityManager.createQuery("select p from Post p WHERE p.user_id = :userId");
		query.setParameter("userId", usuario.getId());
		List<Post> ret = (List<Post>)query.getResultList();
		return ret;
	}

}
