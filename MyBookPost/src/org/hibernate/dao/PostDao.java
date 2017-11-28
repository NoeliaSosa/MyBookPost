package org.hibernate.dao;

import java.util.List;

import org.hibernate.beans.Post;
import org.hibernate.beans.Usuario;


public interface PostDao extends DAO<Post>  {

	public List<Post> obtenerPostsByUsuario(Usuario usuario);
}
