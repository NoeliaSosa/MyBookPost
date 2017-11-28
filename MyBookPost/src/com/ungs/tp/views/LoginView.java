package com.ungs.tp.views;

import com.ungs.tp.services.UsuarioService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends VerticalLayout implements View   {
	private static final long serialVersionUID = 6571640731692761117L;
	UsuarioService service = new UsuarioService();
	
	public LoginView(){
		
		Panel panel = new Panel("Login");
		panel.setSizeUndefined();
		addComponent(panel);
		
		FormLayout content = new FormLayout();
		
		TextField username = new TextField("Usuario");
		username.setIcon(VaadinIcons.USER);
		username.setRequiredIndicatorVisible(true);
		content.addComponent(username);
		
		PasswordField password = new PasswordField("Contraseña");
		password.setIcon(VaadinIcons.PASSWORD);
		password.setRequiredIndicatorVisible(true);
		content.addComponent(password);

		Button send = new Button("Login");
		send.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(service.verificarContraseña(username.getValue(), password.getValue())){
					getUI().getNavigator().navigateTo(MainView.NAME);
				}else{
					Notification.show("Invalid credentials", Notification.Type.ERROR_MESSAGE);
				}
			}
			
		});
		
		Button reg = new Button("Sing up");
		reg.addClickListener(new ClickListener() {

		private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(RegistracionView.NAME);
			}
			
		});
		
		HorizontalLayout botonera = new HorizontalLayout();
		botonera.addComponent(send);
		botonera.addComponent(reg);
		
		content.addComponent(botonera);
		content.setSizeUndefined();
		content.setMargin(true);
		panel.setContent(content);
		setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
	
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}




}
