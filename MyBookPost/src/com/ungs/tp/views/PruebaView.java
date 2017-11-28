package com.ungs.tp.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class PruebaView extends VerticalLayout implements View, ClickListener {

	private static final long serialVersionUID = 4139000354818488848L;

	Button click = new Button("Hola",new Button.ClickListener() {

		private static final long serialVersionUID = 4637271395455601867L;

		@Override
		 public void buttonClick(ClickEvent event) {
		 getUI().getNavigator().navigateTo("");
		 }
		 });
	
	PruebaView() {
		addComponent(click);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buttonClick(ClickEvent event) {
		

	}

}
