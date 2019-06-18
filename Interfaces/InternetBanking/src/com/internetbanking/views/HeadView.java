package com.internetbanking.views;

import java.sql.SQLException;
import java.util.Iterator;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.HeadViewDesign;
import com.internetbanking.entity.Autorecharge;
import com.internetbanking.entity.User;
import com.internetbanking.main.TMPView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

public class HeadView extends HeadViewDesign implements ViewDisplay {
	private static final String STYLE_SELECTED = "selected";
	public static final String NAME = "Head Page";
	private Navigator navigator;
	User user;
	
	public HeadView(){
		navigator = new Navigator(UI.getCurrent(), (ViewDisplay) this);
		addNavigatorView(CardsPage.NAME, CardsPage.class, bCards);
		addNavigatorView(TransactionsPage.NAME, TransactionsPage.class, bTransactionList);
		addNavigatorView(MakeTransactionPage.NAME, MakeTransactionPage.class, bTransaction);
		addNavigatorView(AutorechargePage.NAME, AutorechargePage.class, bAutorecharge);
		navigator.addView("", new TMPView());
		navigator.addView(LoginPage.NAME, new LoginPage(this));
		this.bExit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                CookieOperation.deleteUser();
                doNavigate(LoginPage.NAME);
            }
        });
		navigator.addView(RegistryPage.NAME, new RegistryPage());
		if((user = CookieOperation.readUser()) == null)
			doNavigate(LoginPage.NAME);
		else{
			try {
				user=BDQuerys.getUser(user.getLogin());
			} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if(user != null){
				this.lFirstName.setValue(user.getFirstName());
			this.lLastName.setValue(user.getLastName());
			}
		}
	}
	
	private void doNavigate(String viewName) {
		navigator.navigateTo(viewName);
	}

	private void addNavigatorView(String viewName,
	            Class<? extends View> viewClass, Button menuButton) {
		navigator.addView(viewName, viewClass);
		menuButton.addClickListener(event -> doNavigate(viewName));
		menuButton.setData(viewClass.getName());
	}
	
	private void adjustStyleByData(Component component, Object data) {
        if (component instanceof Button) {
            if (data != null && data.equals(((Button) component).getData())) {
                component.addStyleName(STYLE_SELECTED);
            } else {
                component.removeStyleName(STYLE_SELECTED);
            }
        }
    }

	@Override
	public void showView(View view) {
		// TODO Автоматически созданная заглушка метода
		if (view instanceof Component) {
            panel.setContent((Component) view);
            Iterator<Component> it = header_bar.iterator();
            while (it.hasNext()) {
                adjustStyleByData(it.next(), view.getClass().getName());
            }
        } else {
            throw new IllegalArgumentException("View is not a Component");
        }
	}
	
	public CssLayout getHeader(){
		return this.header_bar;
	}
}
