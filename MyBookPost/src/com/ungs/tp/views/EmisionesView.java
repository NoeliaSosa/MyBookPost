package com.ungs.tp.views;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.ungs.tp.beans.Post;
import com.ungs.tp.services.PostService;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;

public class EmisionesView extends VerticalLayout implements View {

	private static final long serialVersionUID = -1611270775872565526L;
	protected static final String NAME = "emisiones";
	PostService service = new PostService();
	Accordion accordion;
	HorizontalLayout tituloEmi = new HorizontalLayout();

	EmisionesView() {
		
		Button botonVer = new Button("Volver");
		botonVer.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(MainView.NAME);
			}
		});
		Button empe = new Button("Play");
		empe.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				empezar();
			}
		});
		
		tituloEmi.addComponent(botonVer);
		tituloEmi.addComponent(empe);
		addComponent(tituloEmi);
	}
	private void empezar(){
		TimerTask task = new RunMeTask();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 1000, 6000);
	}
	@Override
	public void enter(ViewChangeEvent event) {

	}

	class RunMeTask extends TimerTask {
		@Override
		public void run() {

			if (accordion != null)
				removeComponent(accordion);
			accordion = new Accordion();
			List<Post> posts = service.obtenerTodos();

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
	}

}
