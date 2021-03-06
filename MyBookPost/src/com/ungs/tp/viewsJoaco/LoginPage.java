package com.ungs.tp.viewsJoaco;

import com.ungs.tp.services.UsuarioService;
import com.ungs.tp.views.RegistracionView;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
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

public class LoginPage extends VerticalLayout implements View {
	private static final long serialVersionUID = 1L;
	public static final String NAME = "";
	UsuarioService service = new UsuarioService();
	
	public LoginPage(){
		
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
					VaadinSession.getCurrent().setAttribute("user", username.getValue());
					getUI().getNavigator().addView(SecurePage.NAME, SecurePage.class);
					Page.getCurrent().setUriFragment("!"+SecurePage.NAME);
				}else{
					Notification.show("Contraseña Incorrecta", Notification.Type.ERROR_MESSAGE);
				}
			}
			
		});
		
		Button reg = new Button("Sing up");
		reg.addClickListener(new ClickListener() {

		private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().addView(RegistracionView.NAME, RegistracionView.class);
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