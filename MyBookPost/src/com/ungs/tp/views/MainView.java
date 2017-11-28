package com.ungs.tp.views;

import java.util.List;

import com.ungs.tp.beans.Post;
import com.ungs.tp.beans.Usuario;
import com.ungs.tp.services.PostService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MainView extends VerticalLayout implements View {

	private static final long serialVersionUID = 3959492020089277977L;
	public static final String NAME = "main";
	PostService service = new PostService();
	HorizontalLayout titulo = new HorizontalLayout();
	VerticalLayout costado = new VerticalLayout();
	TextArea txtPost;
	Window subWindow;
	Accordion accordion;

	MainView() {

		Button boton = new Button("Crear Post");
		boton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				crearWindowPost();
			}
		});
		Button botonMyPost = new Button("Ver mis Post");
		botonMyPost.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				verMisPost((Usuario) VaadinSession.getCurrent().getAttribute(
						"user"));
			}

		});
		Button botonEmi = new Button("Ver Emisiones");
		boton.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(EmisionesView.NAME);
			}
		});
		
		titulo.addComponent(boton);
		titulo.addComponent(botonMyPost);
		titulo.addComponent(botonEmi);
		addComponent(titulo);
		actualizar();

	}

	private void actualizar() {
		List<Post> posts = service.obtenerTodos();
		mostrar(posts);
	}

	private void verMisPost(Usuario attribute) {
		List<Post> posts = service.obtenerTodos(attribute);
		mostrar(posts);
	}

	private void mostrar(List<Post> posts) {
		if (accordion != null)
			removeComponent(accordion);
		accordion = new Accordion();
		
		for (Post post : posts) {
			if (post.getContenidoPost() != null) {
				VerticalLayout tab = new VerticalLayout();
				TextArea nuevo = new TextArea();
				nuevo.setMaxLength(300);
				nuevo.setWidth("100%");
				nuevo.setValue(post.getContenidoPost());
				tab.addComponent(nuevo);
				String titulo = post.getUser().getNombreUsuario() + " "
						+ post.getFechaCreacion().toString();
				accordion.addTab(tab, titulo, VaadinIcons.MAGIC);
			}
		}
		addComponent(accordion);
	}

	private void crearWindowPost() {
		subWindow = new Window("");
		subWindow.setWidth("90%");
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		subWindow.setContent(subContent);
		txtPost = new TextArea("Post");
		txtPost.setMaxLength(300);
		txtPost.setWidth("100%");
		subContent.addComponent(txtPost);

		Button button = new Button("Crear");
		button.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					if (validaPost(txtPost.getValue())) {
						Post post = new Post(txtPost.getValue(),
								(Usuario) VaadinSession.getCurrent()
										.getAttribute("user"));
						service.guardarPost(post);
						subWindow.close();
						actualizar();
					} else
						Notification.show("Debe ingresar al menos una letra");

				} catch (Exception e) {

					e.printStackTrace();
				}

			}

			private boolean validaPost(String value) {
				if (value.trim().isEmpty()) {

					return false;
				}
				return true;
			}
		});
		subContent.addComponent(button);
		subWindow.center();
		getUI().addWindow(subWindow);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}
}
