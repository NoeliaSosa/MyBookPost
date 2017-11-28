package com.ungs.tp.views;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.ungs.tp.beans.Post;
import com.ungs.tp.beans.Usuario;
import com.ungs.tp.services.UsuarioService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.AlignmentInfo.Bits;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;


public class RegistracionView extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "registration";
	UsuarioService service = new UsuarioService();

	@SuppressWarnings({ "rawtypes" })
	public RegistracionView() {

		Panel panel = new Panel("Registrar");
		panel.setSizeUndefined();
		addComponent(panel);

		FormLayout content = new FormLayout();

		TextField nombre = new TextField("Nombre");
		nombre.setIcon(VaadinIcons.NURSE);
		content.addComponent(nombre);

		TextField apellido = new TextField("Apellido");
		apellido.setIcon(VaadinIcons.NURSE);
		content.addComponent(apellido);

		List<String> data = Arrays.asList("CUIL", "CUIT");
		RadioButtonGroup tipoDoc = new RadioButtonGroup<>("", data);
		content.addComponent(tipoDoc);


		TextField cuilCuit = new TextField("Número de CUIL/CUIT");
		cuilCuit.setIcon(VaadinIcons.TEXT_INPUT);
		content.addComponent(cuilCuit);

		TextField email = new TextField("Email");
		email.setIcon(VaadinIcons.MAILBOX);
		content.addComponent(email);

		TextField username = new TextField("Nombre de Usuario");
		username.setIcon(VaadinIcons.USER);
		content.addComponent(username);

		PasswordField password = new PasswordField("Contraseña");
		password.setIcon(VaadinIcons.PASSWORD);
		password.setMaxLength(16);
		content.addComponent(password);

		Button send = new Button("Send");
		send.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if (validaDatos(nombre.getValue(), apellido.getValue(),
						cuilCuit.getValue(), (String) tipoDoc.getValue(),
						email.getValue(), username.getValue(),
						password.getValue())) {
					Post p = new Post();
					Usuario nuevoUser = new Usuario(nombre.getValue(), apellido
							.getValue(), cuilCuit.getValue(), (String) tipoDoc
							.getValue(), email.getValue(), username.getValue(),
							password.getValue());
					nuevoUser.agregarPost(p);
					service.crearUsuario(nuevoUser);
					Notification.show("Su usuario se genero con éxito!!");
					clear(content.iterator());
					getUI().getNavigator().navigateTo("");
				}
			}



		});

		Button cancel = new Button("Cancelar");
		cancel.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				clear(content.iterator());
				getUI().getNavigator().navigateTo("");
			}

		});

		HorizontalLayout botonera = new HorizontalLayout();
		botonera.addComponent(send);
		botonera.addComponent(cancel);

		content.addComponent(botonera);
		content.setSizeUndefined();
		content.setMargin(true);
		panel.setContent(content);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

	}
	private void clear(Iterator<Component> iterator) {
		if(iterator.hasNext()){
			((AbstractTextField) iterator.next()).setValue("");
		}
		
	}

	private boolean validaDatos(String nombre, String apellido,
			String cuilCuit, String tipo, String email, String user, String pass) {
		String str = "";
		Boolean ret = true;
		if (nombre.isEmpty()) {
			str.concat(" nombre");
		} else if (apellido.isEmpty()) {
			str.concat(" apellido");
		} else if (cuilCuit.isEmpty()) {
			str.concat(" número de Cuil/Cuit");
		} else if (tipo.isEmpty()) {
			str.concat(" tipo");
		} else if (email.isEmpty()) {
			str.concat(" email");
		} else if (user.isEmpty()) {
			str.concat(" usuario");
		} else if (pass.isEmpty()) {
			str.concat(" contraseña");
		} else {
			return validarCuitCuil(cuilCuit) && validarContraseña(pass);
		}
		if (!str.equals("")) {
			Notification.show("Debe ingresar " + str + "!!");
			ret = false;
		}
		return ret;
	}

	private boolean validarContraseña(String pass) {
		String reg = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";
		return pass.matches(reg);
	}

	private boolean validarCuitCuil(String cuilCuit) {
		// Eliminamos todos los caracteres que no son números
		cuilCuit = cuilCuit.replaceAll("[^\\d]", "");
		// Controlamos si son 11 números los que quedaron, si no es el caso, ya
		// devuelve falso
		if (cuilCuit.length() < 11) {
			Notification.show("Debe ingresar un número de CUIL/CUIT correcto");
			return false;
		}

		String[] cuitArray = cuilCuit.split("");
		Integer aux = 0;
		Integer[] serie = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		for (int i = 0; i < 10; i++) {
			aux += Integer.valueOf(cuitArray[i]) * serie[i];
		}
		aux = 11 - (aux % 11);
		if (aux != Integer.valueOf(cuitArray[10])) {
			Notification.show("Debe ingresar un número de CUIL/CUIT correcto");
			return false;
		}

		return true;

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
