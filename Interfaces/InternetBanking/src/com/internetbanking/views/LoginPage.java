package com.internetbanking.views;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.LoginPageDesign;
import com.internetbanking.entity.User;
import com.internetbanking.main.MainPage;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

public class LoginPage extends LoginPageDesign implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String NAME = "LoginPage";
	String login;
	String password;
	User user;
	HeadView headView;
	
	public LoginPage(HeadView hv){
		this.setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);
		headView = hv;
		this.bSignin.addClickListener(new Button.ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                login = tfLogin.getValue();
                password = tfPassword.getValue();
                try {
                    if (!BDQuerys.loginPassword(login, password)){
                        Notification.show("Неверный логин или пароль!", Notification.Type.ERROR_MESSAGE);
                        return;
                    }
                    headView.getHeader().setVisible(true);
                    CookieOperation.writeUser(BDQuerys.getUser(login));
                    getUI().getNavigator().navigateTo(CardsPage.NAME);
                } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    Notification.show(e.getMessage());
                }
            }
        });
        this.bRegistry.addClickListener(new Button.ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(RegistryPage.NAME);
            }
        });
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Автоматически созданная заглушка метода
		headView.getHeader().setVisible(false);
	}
}
