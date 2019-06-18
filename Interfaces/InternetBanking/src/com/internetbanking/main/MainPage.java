package com.internetbanking.main;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.LoginPageDesign;
import com.internetbanking.desing.RegistryPageDesign;
import com.internetbanking.views.HeadView;
import com.internetbanking.views.LoginPage;
import com.internetbanking.views.RegistryPage;
import com.vaadin.annotations.*;
import com.vaadin.navigator.*;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.*;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
@Theme("internetbanking")
public class MainPage extends UI{
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MainPage.class)
	public static class Servlet extends VaadinServlet {
	}

    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	try {
			BDQuerys.connect();
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
        getPage().setTitle("InternetBanking");
        setContent(new HeadView());
    }
}
