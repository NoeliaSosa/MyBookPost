package com.ungs.tp.dao;

import java.util.List;

import com.ungs.tp.beans.Post;
import com.ungs.tp.beans.Usuario;


public interface PostDao extends DAO<Post>  {

	public List<Post> obtenerPostsByUsuario(Usuario usuario);
}
