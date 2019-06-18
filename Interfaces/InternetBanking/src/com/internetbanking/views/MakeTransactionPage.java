package com.internetbanking.views;

import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.MakeTransactionPageDesign;
import com.internetbanking.entity.Card;
import com.internetbanking.entity.RealCard;
import com.internetbanking.entity.Transaction;
import com.internetbanking.entity.Transaction;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Notification.Type;

public class MakeTransactionPage extends MakeTransactionPageDesign implements View {
	public static final String NAME = "MakeTransactionPage";
	
	public MakeTransactionPage(){
		this.setComponentAlignment(mainLoyout, Alignment.MIDDLE_CENTER);
		String option1 = "Перевод между своими картами",
				option2 = "Перевод на внешнюю карту";
		
		transactionType.setNullSelectionAllowed(false);
		transactionType.addItem(option1);
		transactionType.addItem(option2);
		transactionType.select(option1);
		
		addCardsToComboBox(cbCardIn);
		addCardsToComboBox(cbCardOut);
		
		this.bMakeTransactoin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
            	try{
            		if(cbCardOut.getValue()=="" 
            				|| tfRub.getValue()=="" 
            				|| tfCop.getValue()=="" 
            				|| (transactionType.getValue().equals(option1) && cbCardIn.getValue()=="")
            				|| (transactionType.getValue().equals(option2) && tfCardIn.getValue()=="")){
            			Notification.show("Не все поля заполнены!", Type.ERROR_MESSAGE);
            			return;
            		}
            		
            		String rub = tfRub.getValue(),
            				cop = tfCop.getValue();	
            		int irub = Integer.valueOf(rub),
            			icop = Integer.valueOf(cop),
            			summ;
            		if(icop>=100){
            			Notification.show("Сумма введена некорректно!", Type.ERROR_MESSAGE);
            	       	return;
            		}
            		summ = irub*100 + icop;
            		Card card1, card2;
            		if(transactionType.getValue().equals(option1)){
            			card1 = BDQuerys.getCard((Integer)cbCardOut.getValue());
          				card2 = BDQuerys.getCard((Integer)cbCardIn.getValue());
            			if(card1.getId() == card2.getId()){
            				Notification.show("Невозможно сделать перевод на ту же карту!", Type.ERROR_MESSAGE);
                	       	return;
            			}
            			BDQuerys.makeTransaction(card1, card2, summ);
            		} else {
            			card1 = BDQuerys.getCardByName(cbCardOut.getValue().toString(), CookieOperation.readUser().getLogin());
            			card2 = BDQuerys.getCard(tfCardIn.getValue().toString(), CookieOperation.readUser().getLogin());
            			if(card2 == null){
            				Notification.show("Карты с таким номером не существует!", Type.ERROR_MESSAGE);
                	       	return;
            			}
            			if(card1.getId() == card2.getId()){
            				Notification.show("Невозможно сделать перевод на ту же карту!", Type.ERROR_MESSAGE);
                	       	return;
            			}
            		}
            		BDQuerys.makeTransaction(card1, card2, summ);
            		Notification.show("Денежный перевод успешно проведен!", Type.HUMANIZED_MESSAGE);
            	} catch (SQLException e) {
					e.printStackTrace();
				} catch (NumberFormatException e){
					Notification.show("Сумма введена некорректно!", Type.ERROR_MESSAGE);
                	return;
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
            }
        });
		
		transactionType.addValueChangeListener(new ValueChangeListener(){

			@Override
			public void valueChange(ValueChangeEvent event) {
				if(transactionType.getValue().equals(option1)){
					ourCard.setVisible(true);
					extendCard.setVisible(false);
				}
				else{
					ourCard.setVisible(false);
					extendCard.setVisible(true);
				}
			}
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Автоматически созданная заглушка метода
		if(CookieOperation.readUser() == null)
			getUI().getNavigator().navigateTo(LoginPage.NAME);
	}
	
	private void addCardsToComboBox(ComboBox cb){
		try{
			cb.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
			cb.setNullSelectionAllowed(false);
			ArrayList<Card> cards = BDQuerys.getCards(CookieOperation.readUser().getLogin());
			for(Card i: cards){
				cb.addItem(i.getId());
				cb.setItemCaption(i.getId(), i.getCardName());
			}
		} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}
}
