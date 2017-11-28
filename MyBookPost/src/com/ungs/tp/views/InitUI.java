package com.ungs.tp.views;

import javax.servlet.annotation.WebServlet;

import org.hibernate.services.PostService;

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

	/*
	 * private TextArea txtPost; private Window subWindow;
	 */
	public Navigator navigator;
	protected static final String MAINVIEW = "main";
	@Override
	protected void init(VaadinRequest request) {
		navigator = new Navigator(this, this);
		navigator.addView("", new LoginView());
        navigator.addView(MAINVIEW, new PruebaView());

	}
	/*
	 * VerticalLayout layout = new VerticalLayout(); layout.setMargin(true);
	 * setContent(layout);
	 * 
	 * Button button = new Button("Crear Post"); button.addClickListener(new
	 * Button.ClickListener() { public void buttonClick(ClickEvent event) {
	 * crearWindowPost(); } }); layout.addComponent(button);
	 * 
	 * }
	 * 
	 * private void crearWindowPost() { subWindow = new Window("");
	 * subWindow.setWidth("90%"); VerticalLayout subContent = new
	 * VerticalLayout(); subContent.setMargin(true);
	 * subWindow.setContent(subContent); txtPost = new TextArea("Post");
	 * txtPost.setCursorPosition(getScrollLeft()); txtPost.setMaxLength(300);
	 * txtPost.setWidth("100%"); subContent.addComponent(txtPost);
	 * 
	 * Button button = new Button("Crear"); button.addClickListener(new
	 * Button.ClickListener() {
	 * 
	 * @SuppressWarnings({ "static-access" }) public void buttonClick(ClickEvent
	 * event) { try { Notification notificador = new Notification(""); if
	 * (validaPost(txtPost.getValue())){
	 * service.guardarPost(txtPost.getValue()); subWindow.close(); }else
	 * notificador.show("Debe ingresar al menos una letra");
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * private boolean validaPost(String value) { if (value.trim().isEmpty()) {
	 * 
	 * return false; } return true; } });
	 * 
	 * subContent.addComponent(button); subWindow.center();
	 * addWindow(subWindow);
	 * 
	 * }
	 */

}