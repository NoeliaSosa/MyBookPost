package org.hibernate.services;

import java.util.List;

import org.hibernate.beans.Post;
import org.hibernate.beans.Usuario;
import org.hibernate.dao.PostDao;
import org.hibernate.dao.impl.PostDaoImpl;

public class PostService {
	
	private PostDao postDao = new PostDaoImpl();
	
	public void guardarPost(Post post) throws Exception{
		postDao.crear(post);
	}
	
	public List<Post> obtenerTodos(){
		List<Post> ret = postDao.todos();
		return ret;
	}
	
	public List<Post> obtenerTodos(Usuario usuario){
		List<Post> ret = postDao.obtenerPostsByUsuario(usuario);
		return ret;
	}

}
