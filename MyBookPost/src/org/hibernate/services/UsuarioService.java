package org.hibernate.services;

import org.hibernate.beans.Usuario;
import org.hibernate.dao.UsuarioDao;
import org.hibernate.dao.impl.UsuarioDaoImpl;

public class UsuarioService {

	UsuarioDao service = new UsuarioDaoImpl();
	
	@SuppressWarnings("unused")
	public Boolean verificarContraseña(String user,String contraseña){
		String reg = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";
		Usuario usuario = service.getUsuario(user);
		return usuario.getContraseña().equals(contraseña);
	}
	
	public void crearUsuario(Usuario usuario){
		service.crear(usuario);
	}
	
	public void cambiarContraseña(Usuario usuario){
		service.actualizar(usuario);
	}
	
}
