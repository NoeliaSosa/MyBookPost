package com.ungs.tp.services;

import java.util.List;

import com.ungs.tp.beans.Usuario;
import com.ungs.tp.dao.UsuarioDao;
import com.ungs.tp.dao.impl.UsuarioDaoImpl;
import com.vaadin.server.VaadinSession;

public class UsuarioService {

	UsuarioDao service = new UsuarioDaoImpl();

	public Boolean verificarContraseña(String user, String contraseña) {
		List<Usuario> usuarios = service.getUsuario(user);
		Usuario userRet = usuarios.get(0);
		if (userRet.getContraseña().equals(contraseña)) {
			VaadinSession.getCurrent().setAttribute("user", userRet);
			return true;
		}

		return false;
	}

	public void crearUsuario(Usuario usuario) {
		service.crear(usuario);
	}

	public void cambiarContraseña(Usuario usuario) {
		service.actualizar(usuario);
	}

	public boolean verificarDuplicados(String cuilCuit) {
		return service.verificarDuplicados(cuilCuit);
	}

}
