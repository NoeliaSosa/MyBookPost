package com.ungs.tp.services;

import java.util.List;

import com.ungs.tp.beans.Post;
import com.ungs.tp.beans.Usuario;
import com.ungs.tp.dao.PostDao;
import com.ungs.tp.dao.impl.PostDaoImpl;

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
