package com.internetbanking.views;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.CardsPageDesign;
import com.internetbanking.entity.Card;
import com.internetbanking.entity.RealCard;
import com.vaadin.client.metadata.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class CardsPage extends CardsPageDesign implements View {
	public static final String NAME = "Cards";
	private HashMap<Integer, String> map;
	private String cardName;
	String number;
	Date date;
	String securePassword;
	
	public CardsPage(){
		this.setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
		
		table.addContainerProperty("Карта", String.class, null);
		table.setNullSelectionAllowed(false);
		map = new HashMap<Integer, String>();
		
		table.setSelectable(true);
		table.setImmediate(true);
		table.setMultiSelect(false);
		this.bNameChange.setVisible(true);
		this.changeNameBlock.setVisible(false);
		
		table.addValueChangeListener(new ValueChangeListener() {
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				String cardName = map.get(table.getValue());
				try {
					Card card = BDQuerys.getCardByName(cardName, CookieOperation.readUser().getLogin());
					RealCard realCard = BDQuerys.getRealCard(card.getNumber());
					refreshCardData(realCard);
					if(!card.isMainCard())
						bSetMainCard.setEnabled(true);
					else
						bSetMainCard.setEnabled(false);
				} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
		
		this.bDeleteCard.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
            	try{
            		if(BDQuerys.getMainCard(CookieOperation.readUser().getLogin()).getId().equals(table.getValue())){
            			Notification.show("Невозможно отвязать основную карту!", Type.ERROR_MESSAGE);
            			return;
            		}
            		String cardName = map.get(table.getValue());
					BDQuerys.deleteCardByName(CookieOperation.readUser().getLogin(), cardName);
					table.select(table.getNullSelectionItemId());
					refresh();
            	}
            	catch(Exception e){
            		refresh();
            	}
            }
        });
		
		this.bNameConfirm.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
            	try{
            		Card card = BDQuerys.getCardByName(cardName, CookieOperation.readUser().getLogin());
            		BDQuerys.updateCardName(card, tfName.getValue());
            		endNameChange();
            		refresh();
                	refreshCardData(BDQuerys.getRealCard(card.getSecuredNumber()));
            	}
            	catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
            		e.printStackTrace();
            	}
            }
        });
		
		this.bAddCard.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
            	Window window = builtWindow();
            	window.setCaption("Привязать новую карту");
            	window.setModal(true);
            	window.setWidth("500px");
            	window.setHeight("300px");
            	window.setResizable(false);
            	UI.getCurrent().addWindow(window);
            	refresh();
            }
        });
		
		this.bNameCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                bAddCard.setVisible(true);
                table.setSelectable(true);
                endNameChange();
            }
        });
		
		this.bNameChange.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				cardName = tfName.getValue();
				startNameChange();
			}
		});
		
		this.bSetMainCard.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				try{
					Card card = BDQuerys.getCard((Integer)table.getValue());
					BDQuerys.setMainCard(card);
					bSetMainCard.setEnabled(false);
				} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
					e.printStackTrace();
				}
			}
		});
		refresh();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		if(CookieOperation.readUser() == null)
			getUI().getNavigator().navigateTo(LoginPage.NAME);
		refresh();
	}
	
	private void refresh(){
		ArrayList<Card> list;
		try {
			list = BDQuerys.getCards(CookieOperation.readUser().getLogin());
			if(!table.isEmpty())
				table.removeAllItems();
			map.clear();
			for(Card i:list){
				table.addItem(new Object[]{i.getCardName()}, i.getId());
				map.put(i.getId(), i.getCardName());
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		table.refreshRowCache();
	}
	
	private void refreshCardData(RealCard realCard){
		try{
			if(realCard == null){
				this.tfName.setValue("");
				this.tfNumber.setValue("");
				this.tfSummRub.setValue("");
				this.tfSummCop.setValue("");
				this.tfType.setValue("");
			}
			Card card = BDQuerys.getCard(realCard.getNumber(), CookieOperation.readUser().getLogin());
			this.tfName.setValue(card.getCardName());
			this.tfNumber.setValue(card.getSecuredNumber());
			this.tfType.setValue(realCard.getType());
			String rub, cop;
			rub =(new Integer(realCard.getCash()/100)).toString();
			cop =(new Integer(realCard.getCash()%100)).toString();
			this.tfSummRub.setValue(rub);;
			this.tfSummCop.setValue(cop);;
		}
		catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}
	
	private void startNameChange(){
		this.bNameChange.setVisible(false);
		this.changeNameBlock.setVisible(true);
		this.tfName.setEnabled(true);
	}
	
	private void endNameChange(){
		this.bNameChange.setVisible(true);
		this.changeNameBlock.setVisible(false);
		this.tfName.setEnabled(false);
	}
	
	private Window builtWindow(){
		Window window = new Window();
		VerticalLayout mainLayout = new VerticalLayout();
		HorizontalLayout line1 = new HorizontalLayout(),
				line2 = new HorizontalLayout(),
				line3 = new HorizontalLayout(),
				line4 = new HorizontalLayout(),
				line5 = new HorizontalLayout();
		Label lCardName = new Label("Имя карты"),
				lNumberCard = new Label("Номер карты"),
				lValidDate = new Label("Действительна до"),
				lSecurePassword = new Label("Код безопасности");
		lCardName.setWidth("150px");
		lNumberCard.setWidth("150px");
		lValidDate.setWidth("150px");
		lSecurePassword.setWidth("150px");
		TextField tfCardNumber = new TextField(),
				tfCardName = new TextField();
		tfCardName.setWidth("200px");
		tfCardNumber.setWidth("200px");
		PasswordField tfSecureCode = new PasswordField();
		tfSecureCode.setWidth("50px");
		DateField dfValidDate = new DateField();
		dfValidDate.setWidth("150px");
		Button bCancel = new Button("Отмена"),
				bConfirm = new Button("Принять");
		bCancel.setId("Cancel");
		bConfirm.setId("Confirm");
		bConfirm.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Автоматически созданная заглушка метода
				try{
					RealCard realCard = BDQuerys.getRealCard(tfCardNumber.getValue());
					if(tfCardName.equals("")){
						Notification.show("Введити имя для карты!", Type.ERROR_MESSAGE);
						return;
					}
					if(realCard == null){
						Notification.show("Карты с таким номером не существует!", Type.ERROR_MESSAGE);
						return;
					}
					if(!realCard.getSecurePassword().equals(tfSecureCode.getValue())){
						Notification.show("Неверный код безопасности!", Type.ERROR_MESSAGE);
						return;
					}
					if(!dfValidDate.getValue().equals(realCard.getValidDate())){
						Notification.show("Дата указана неверно!", Type.ERROR_MESSAGE);
						return;
					}
					if(BDQuerys.getCardByName(realCard.getNumber(), CookieOperation.readUser().getLogin())!=null
							|| BDQuerys.getCardByName(tfCardName.getValue(), CookieOperation.readUser().getLogin())!=null){
						Notification.show("Карта уже привязана!", Type.ERROR_MESSAGE);
						return;
					}
					
					Card card = new Card();
					card.setCardName(tfCardName.getValue());
					card.setId(new Random().nextInt(10000000));
					card.setLogin(CookieOperation.readUser().getLogin());
					if(BDQuerys.getMainCard(CookieOperation.readUser().getLogin())==null)
						card.setMainCard(true);
					else
						card.setMainCard(false);
					card.setNumber(realCard.getNumber());
					card.setSecurePassword(realCard.getSecurePassword());
					card.setType(realCard.getType());
					card.setValidDate(realCard.getValidDate());
					BDQuerys.addCard(card);
					window.close();
				}
				catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
					e.printStackTrace();
				}
			}
		});
		
		bCancel.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Автоматически созданная заглушка метода
				window.close();
			}
		});
		
		line1.addComponent(lCardName);
		line1.addComponent(tfCardName);
		line2.addComponent(lNumberCard);
		line2.addComponent(tfCardNumber);
		line3.addComponent(lValidDate);
		line3.addComponent(dfValidDate);
		line4.addComponent(lSecurePassword);
		line4.addComponent(tfSecureCode);
		line5.addComponent(bCancel);
		line5.addComponent(bConfirm);		
		mainLayout.addComponent(line1);
		mainLayout.addComponent(line2);
		mainLayout.addComponent(line3);
		mainLayout.addComponent(line4);
		mainLayout.addComponent(line5);
		mainLayout.setMargin(true);
		window.setContent(mainLayout);
		
		return window;
	}
}
