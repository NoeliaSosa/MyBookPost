package com.ungs.tp.dao;

import java.util.List;

import com.ungs.tp.beans.Usuario;

public interface UsuarioDao extends DAO<Usuario>{
	
	public List<Usuario> getUsuario(String userName);
	
	public Boolean verificarDuplicados(String userName);
}
