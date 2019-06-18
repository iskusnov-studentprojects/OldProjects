package com.internetbanking.cookies;

import javax.servlet.http.Cookie;

import com.internetbanking.entity.User;
import com.vaadin.server.VaadinService;

public class CookieOperation {
	private static final String USER_LOGIN ="internetbanking-user-login",
			USER_PASSWORD = "internetbanking-user-password";
	
	public static void writeUser(User user){
		Cookie login = new Cookie(USER_LOGIN, user.getLogin()),
				password = new Cookie(USER_PASSWORD, user.getPassword());
		
		login.setPath(VaadinService.getCurrentRequest().getContextPath());
		password.setPath(VaadinService.getCurrentRequest().getContextPath());
		
		VaadinService.getCurrentResponse().addCookie(login);
		VaadinService.getCurrentResponse().addCookie(password);
	}
	
	public static User readUser(){
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		Cookie login = null, password = null;
		User user = new User();
		
		for (Cookie cookie : cookies) { 
			if(cookie.getName().compareTo(USER_LOGIN) == 0)
				login = cookie;
			if(cookie.getName().compareTo(USER_PASSWORD) == 0)
				password = cookie;
		}
		if(login == null || password == null)
			return null;
		user.setLogin(login.getValue());
		user.setPassword(password.getValue());
		return user;
	}
	
	public static void deleteUser(){
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		Cookie login = null, password = null;
		
		for (Cookie cookie : cookies) { 
			if(cookie.getName().compareTo(USER_LOGIN) == 0)
				login = cookie;
			if(cookie.getName().compareTo(USER_PASSWORD) == 0)
				password = cookie;
		}
		login.setMaxAge(0);
		password.setMaxAge(0);
		login.setPath(VaadinService.getCurrentRequest().getContextPath());
		password.setPath(VaadinService.getCurrentRequest().getContextPath());
		
		VaadinService.getCurrentResponse().addCookie(login);
		VaadinService.getCurrentResponse().addCookie(password);
	}
}
