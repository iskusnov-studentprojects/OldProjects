package com.internetbanking.views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.AutorechargeDesign;
import com.internetbanking.entity.Autorecharge;
import com.internetbanking.entity.Card;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Notification;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;

public class AutorechargePage extends AutorechargeDesign implements View {
	public static final String NAME = "Autorecharge";
	private Autorecharge arc;
	private String timeRub, timeCop,
					amountRub, amountCop,
					amountRechargeRub, amountRechargeCop;
	private Integer iTimeRub, iTimeCop,
					iAmountRub, iAmountCop,
					iAmountRechargeRub, iAmountRechargeCop,
					timeSumm, amountSumm, amountRechargeSumm;
	
	public AutorechargePage() {
		// TODO Автоматически созданная заглушка конструктора
		this.setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
		addCardsToComboBox(this.cbCardIn);
		cbTime.addValueChangeListener(new ValueChangeListener(){
			@Override
			public void valueChange(ValueChangeEvent event) {
				if(cbTime.getValue()){
					timeBlock.setEnabled(true);
				}
				else{
					timeBlock.setEnabled(false);
				}
			}
		});
		
		cbAmount.addValueChangeListener(new ValueChangeListener(){
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Автоматически созданная заглушка метода
				if(cbAmount.getValue()){
					amountBlock.setEnabled(true);
				}
				else{
					amountBlock.setEnabled(false);
				}
			}
		});

		bConfirm.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				try{
					Autorecharge arc = BDQuerys.getAutorecharge((Integer)cbCardIn.getValue());
					arc.setIdCard((Integer)cbCardIn.getValue());
					if(cbTime.getValue()){
						if(tfPeriodTime.getValue() == "" 
								|| tfPeriodSinceDate.getValue() == null
								|| tfPeriodToDate.getValue() == null){
							Notification.show("Не все поля заполнены!", Type.ERROR_MESSAGE);
				       		return;
						}
						timeRub = tfPeriodRechargeSummRub.getValue();
						timeCop = tfPeriodRechargeSummCop.getValue();
						iTimeRub=Integer.valueOf(timeRub);
						iTimeCop=Integer.valueOf(timeCop);
						if(iTimeCop>=100){
							Notification.show("Сумма введена некорректно!", Type.ERROR_MESSAGE);
				       		return;
						}
						timeSumm = iTimeRub*100+iTimeCop;
						arc.setByTime(true);
						arc.setTime(Integer.valueOf(tfPeriodTime.getValue()));
						arc.setTimeSinceDate(tfPeriodSinceDate.getValue());
						arc.setTimeSumm(timeSumm);
						arc.setTimeToDate(tfPeriodToDate.getValue());
					} else {
						arc.setByTime(false);
						arc.setTime(0);
						arc.setTimeSinceDate(new Date());
						arc.setTimeSumm(0);
						arc.setTimeToDate(new Date());
					}
				
					if(cbAmount.getValue()){
						if(tfAmountSinceDate.getValue() == null
								|| tfAmountToDate.getValue() == null){
							Notification.show("Не все поля заполнены!", Type.ERROR_MESSAGE);
				       		return;
						}
						amountRub = tfAmountSummRub.getValue();
						amountCop = tfAmountSummCop.getValue();
						amountRechargeRub = tfAmountRechargeSummRub.getValue();
						amountRechargeCop = tfAmountRechargeSummCop.getValue();
						
						iAmountRub=Integer.valueOf(amountRub);
						iAmountCop=Integer.valueOf(amountCop);
						iAmountRechargeRub=Integer.valueOf(amountRechargeRub);
						iAmountRechargeCop=Integer.valueOf(amountRechargeCop);
						if(iAmountCop>=100 || iAmountRechargeCop>=100){
							Notification.show("Сумма введена некорректно!", Type.ERROR_MESSAGE);
				       		return;
						}
						amountSumm = iAmountRub*100 + iAmountCop;
						amountRechargeSumm = iAmountRechargeRub*100 + iAmountRechargeCop;
						arc.setByAmount(true);
						arc.setAmountSinceDate(tfAmountSinceDate.getValue());
						arc.setAmountToDate(tfAmountToDate.getValue());
						arc.setAmountSumm(amountSumm);
						arc.setAmountRechargeSumm(amountRechargeSumm);
					} else {
						arc.setByAmount(false);
						arc.setAmountSinceDate(new Date());
						arc.setAmountToDate(new Date());
						arc.setAmountSumm(0);
						arc.setAmountRechargeSumm(0);
					}
					BDQuerys.updateAutorecharge(arc);
				} catch(NumberFormatException e){
					Notification.show("Сумма введена некорректно!", Type.ERROR_MESSAGE);
		       		return;
				}
				catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
					e.printStackTrace();
				}
			}
		});
		
		cbCardIn.addValueChangeListener(new ValueChangeListener(){

			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if(cbCardIn.getValue().toString() != ""){
						arc = BDQuerys.getAutorecharge((Integer)cbCardIn.getValue());
						
						cbTime.setValue(arc.getByTime());
						cbAmount.setValue(arc.getByAmount());
						if(arc.getByTime()){
							timeBlock.setEnabled(true);
							tfPeriodTime.setValue(arc.getTime().toString());
							tfPeriodSinceDate.setValue(arc.getTimeSinceDate());
							tfPeriodToDate.setValue(arc.getTimeToDate());
							tfPeriodRechargeSummRub.setValue((new Integer(arc.getTimeSumm()/100)).toString());
							tfPeriodRechargeSummCop.setValue((new Integer(arc.getTimeSumm()%100)).toString());
						}
						else{
							timeBlock.setEnabled(false);
							tfPeriodTime.setValue("");
							tfPeriodSinceDate.setValue(null);
							tfPeriodToDate.setValue(null);
							tfPeriodRechargeSummRub.setValue("");
							tfPeriodRechargeSummCop.setValue("");
						}
						
						if(arc.getByAmount()){
							amountBlock.setEnabled(true);
							tfAmountSinceDate.setValue(arc.getAmountSinceDate());
							tfAmountToDate.setValue(arc.getAmountToDate());
							tfAmountSummRub.setValue((new Integer(arc.getAmountSumm()/100)).toString());
							tfAmountSummCop.setValue((new Integer(arc.getAmountSumm()%100)).toString());
							tfAmountRechargeSummRub.setValue((new Integer(arc.getAmountRechargeSumm()/100)).toString());
							tfAmountRechargeSummCop.setValue((new Integer(arc.getAmountRechargeSumm()%100)).toString());
						}
						else{
							amountBlock.setEnabled(false);
							tfAmountSinceDate.setValue(null);
							tfAmountToDate.setValue(null);
							tfAmountSummRub.setValue("");
							tfAmountSummCop.setValue("");
							tfAmountRechargeSummRub.setValue("");
							tfAmountRechargeSummCop.setValue("");
						}
					}
				}
				catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException exc){
					exc.printStackTrace();
				}
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Автоматически созданная заглушка метода
		
	}
	
	private void addCardsToComboBox(ComboBox cb){
		try{
			cb.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
			cb.setNullSelectionAllowed(false);
			ArrayList<Card> cards = BDQuerys.getCards(CookieOperation.readUser().getLogin());
			for(Card i: cards){
				if(!i.isMainCard()){
					cb.addItem(i.getId());
					cb.setItemCaption(i.getId(), i.getCardName());
				}
			}
		} catch(SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
	}
}
