package com.internetbanking.views;

import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Pattern;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.desing.RegistryPageDesign;
import com.internetbanking.entity.RealCard;
import com.internetbanking.entity.User;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;

public class RegistryPage extends RegistryPageDesign implements View  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "RegistryPage";
	
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String repassword;
	private String code;
	private String inCode;
	
	public RegistryPage(){
		this.setComponentAlignment(this.mainLayout, Alignment.MIDDLE_CENTER);
		
		this.bRegistry.addClickListener(new Button.ClickListener() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                firstName = tfFirstName.getValue();
                lastName = tfLastName.getValue();
                login = tfLogin.getValue();
                password = tfPassword.getValue();
                repassword = tfConfirmPassword.getValue();
                inCode = tfConfirmCode.getValue();
                String cardNumber = tfCardNumber.getValue();
                
                if(firstName == "" || lastName == "" || login == "" || password == "" || repassword == ""){
                	Notification.show("�� ��� ���� ���������!", Notification.Type.ERROR_MESSAGE);
                	return;
                }
                if(password.compareTo(repassword)!= 0){
                	Notification.show("������ �� ���������!", Notification.Type.ERROR_MESSAGE);
                	return;
                }
                if(!inCode.equals(code)){
                	Notification.show("�������� ��� �������������!", Type.ERROR_MESSAGE);
                	return;
                }
                try {
					if(BDQuerys.loginPassword(login, password)){
						Notification.show("������ ��� ����������!", Type.ERROR_MESSAGE);
						return;
					}
				} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
                if(checkWord(login)){
                	Notification.show("������� ������� � ����� ����� �����������! ", Type.ERROR_MESSAGE);
					return;
                }
                if(checkWord(password)){
                	Notification.show("������� ������� � ����� ������ �����������! ", Type.ERROR_MESSAGE);
					return;
                }
                
                User user = new User();
                user.setLogin(login);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPassword(password);
                
                try {
                	if(BDQuerys.checkUser(login)){
                		Notification.show("������������ � ����� ������� ��� ����������", Type.ERROR_MESSAGE);
                		return;
                	}
                	RealCard realCard = BDQuerys.getRealCard(cardNumber);
                	if(realCard == null){
                		Notification.show("����� � ����� ������� �� ����������", Type.ERROR_MESSAGE);
                		return;
                	}
					
                	BDQuerys.addNewUser(user);
				} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					// TODO ������������� ��������� ���� catch
					e.printStackTrace();
				}
                getUI().getNavigator().navigateTo(LoginPage.NAME);
            }
        });
		
		this.bCancel.addClickListener(new Button.ClickListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getNavigator().navigateTo(LoginPage.NAME);
            }
        });
		
		this.bSendCode.addClickListener(new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO ������������� ��������� �������� ������
				Notification.show(code);
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO ������������� ��������� �������� ������
		code = new Integer((new Random()).nextInt(8999) + 1000).toString();
		this.tfCardNumber.setValue("");
		this.tfConfirmCode.setValue("");
		this.tfConfirmPassword.setValue("");
		this.tfFirstName.setValue("");
		this.tfLastName.setValue("");
		this.tfLogin.setValue("");
		this.tfPassword.setValue("");
	}
	
	//��������� �������� �� ������ �������
	public boolean checkWord(String word){
		StringBuffer sb = new StringBuffer(word);
	    for (int x = 0; x < sb.length(); x++) {
	      char c = sb.charAt(x);
	      if (Pattern.matches("[�-��-�]", Character.toString(c))) {
	        return true;
	      }
	    }
	    return false;
	}
}
