package org.hibernate.dao;

import org.hibernate.beans.Usuario;

public interface UsuarioDao extends DAO<Usuario>{
	
	public Usuario getUsuario(String userName);
}
