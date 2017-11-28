package com.ungs.tp.views;

import javax.servlet.annotation.WebServlet;

import com.ungs.tp.services.PostService;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("valo")
public class InitUI extends UI {
	// pasar a view
	PostService service = new PostService();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = InitUI.class)
	public static class Servlet extends VaadinServlet {
	}

	public Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView("", new LoginView());
		navigator.addView(RegistracionView.NAME, new RegistracionView());
		navigator.addView(MainView.NAME, new MainView());
        navigator.addView(EmisionesView.NAME, new EmisionesView());

	}
	

}