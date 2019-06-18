package com.internetbanking.views;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import com.internetbanking.bdquerys.BDQuerys;
import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.desing.TransactionsPageDesign;
import com.internetbanking.entity.Card;
import com.internetbanking.entity.Transaction;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Container.Filterable;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.themes.ValoTheme;

public class TransactionsPage extends TransactionsPageDesign implements View {
	public static final String NAME = "TransactionsPage";
	
	public TransactionsPage(){
		table.addContainerProperty("date", String.class, null);
		table.addContainerProperty("time", String.class, null);
		table.addContainerProperty("type", String.class, null);
		table.addContainerProperty("cardName", String.class, null);
		table.addContainerProperty("cardNumber", String.class, null);
		table.addContainerProperty("category", String.class, null);
		table.addContainerProperty("summ", String.class, null);
		table.setColumnWidth("date", 150);
		table.setColumnWidth("time", 100);
		table.setColumnWidth("type", 200);
		table.setColumnWidth("cardName", 300);
		table.setColumnWidth("cardNumber", 200);
		table.setColumnWidth("category", 300);
		table.setColumnWidth("summ", 300);
		table.setColumnHeaders("Дата","Время","Тип","Имя карты","Номер карты","Категория","Сумма");
		buildFilter();
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		refresh();
		if(CookieOperation.readUser() == null)
			getUI().getNavigator().navigateTo(LoginPage.NAME);
	}
	
	private void refresh(){
		ArrayList<Card> clist;
		try {
			clist = BDQuerys.getCards(CookieOperation.readUser().getLogin());
			ArrayList<Transaction> tlist;
			int index = 1;
			if(!table.isEmpty())
				table.clear();
			for(Card i: clist){
				try {
					tlist = BDQuerys.getTransactions(i.getNumber());
					for(Transaction j: tlist){
						String summa = (new Integer(j.getSumm()/100)).toString() + " р. " + (new Integer(j.getSumm()%100)).toString() + " к.";
						table.addItem(new Object[]{j.getDate().toString(), j.getTime().toString(),
								j.getType(), i.getCardName(), i.getSecuredNumber(), j.getCategory(), summa}, index);
						index++;
					}
				} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
					// TODO Автоматически созданный блок catch
					e.printStackTrace();
				}
				
			}
		} catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}
	}
	
	private void buildFilter(){
		filter.addTextChangeListener(new TextChangeListener() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public void textChange(final TextChangeEvent event) {
                Filterable data = (Filterable) table.getContainerDataSource();
                data.removeAllContainerFilters();
                data.addContainerFilter(new Filter() {
                    /**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
                    public boolean passesFilter(final Object itemId,
                            final Item item) {

                        if (event.getText() == null
                                || event.getText().equals("")) {
                            return true;
                        }

                        return filterByProperty("cardName", item,
                                event.getText())
                                || filterByProperty("cardNumber", item,
                                        event.getText());

                    }

                    @Override
                    public boolean appliesToProperty(final Object propertyId) {
                        if (propertyId.equals("cardName")
                                || propertyId.equals("cardNumber")) {
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        filter.setInputPrompt("Filter");
        filter.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        filter.addShortcutListener(new ShortcutListener("Clear", KeyCode.ESCAPE, null) {
            @Override
            public void handleAction(final Object sender, final Object target) {
                filter.setValue("");
                ((Filterable) table.getContainerDataSource())
                        .removeAllContainerFilters();
            }
        });
	}
	
	private boolean filterByProperty(final String prop, final Item item,
            final String text) {
        if (item == null || item.getItemProperty(prop) == null
                || item.getItemProperty(prop).getValue() == null) {
            return false;
        }
        String val = item.getItemProperty(prop).getValue().toString().trim()
                .toLowerCase();
        if (val.contains(text.toLowerCase().trim())) {
            return true;
        }
        return false;
    }
}
