package com.internetbanking.main;

import com.internetbanking.views.CardsPage;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

public class TMPView extends VerticalLayout implements View{

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Автоматически созданная заглушка метода
		getUI().getNavigator().navigateTo(CardsPage.NAME);
	}

}
