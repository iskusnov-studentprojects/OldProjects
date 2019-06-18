package com.internetbanking.bdquerys;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.internetbanking.cookies.CookieOperation;
import com.internetbanking.entity.Autorecharge;
import com.internetbanking.entity.Card;
import com.internetbanking.entity.RealCard;
import com.internetbanking.entity.Transaction;
import com.internetbanking.entity.Transaction;
import com.internetbanking.entity.User;
import com.internetbanking.views.AutorechargePage;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * Created by Sergey on 22.10.2015.
 */
public class BDQuerys {
    static final String url = "jdbc:mysql://localhost:3306/InternetBanking";
    static final String userName = "root";
    static final String password = "456852";
    static Connection connection = null;
    static Statement statement = null;
    static public final int OUTTRANSACTION = 1;
	static public final int INTRANSACTION = 2;
	static public final String CATEGORY_MONEYTANSACTION = "Денежный перевод";

	//Установить соединение с БД
    static public void connect() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if(connection == null){
        	Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, userName, password);
            statement = connection.createStatement();
        }
    }

    //Проверить соединение с БД
    static  public boolean isConnected(){
        if(connection == null)
            return false;
        else
            return true;
    }

    //Проверить наличие аккаунта и совпадение пароля
    static public boolean loginPassword(String log, String pass) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "select * from klient where login = \"" + log + "\";";
    	ResultSet result = statement.executeQuery(query);
        return (!result.next() || result.getString("password").compareTo(pass)!=0)?false:true;
    }

    //Проверить наличие аккаунта
    static public boolean checkUser(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "select * from klient where login = \"" + login + "\";";
    	ResultSet result = statement.executeQuery(query);
        return result.next();
    }

    //Добавить новый аккаунт
    static public void addNewUser(User user) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "insert into klient (login, firstName, lastName, password) values (\"" +
    					user.getLogin() + "\", \"" + user.getFirstName() + "\", \"" + user.getLastName() + "\", \"" + user.getPassword() + "\");";
    	statement.execute(query);
    }
    
    //Добавить карту
    static public void addCard(Card card) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	Random rand = new Random();
    	String query1 = "select * from cardtype where name=" + "\"" + card.getType() + "\";";
    	ResultSet result = statement.executeQuery(query1);
    	result.next();
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String query2 = "insert into card (idCard, number, validDate, securePassword, idCardType, login, mainCard, cardName)" + 
    				"values (" + (new Integer(card.getId())).toString() + ", \"" + card.getNumber() + "\", " +
    				"STR_TO_DATE(\"" + df.format(card.getValidDate()) + "\", \"%d/%m/%Y\"), \"" + card.getSecurePassword() + "\", " + 
    				(new Integer(result.getInt("idCardType"))).toString() + ", \"" + card.getLogin() + "\", " + 
    				(new Boolean(card.isMainCard())).toString() + ", \"" + card.getCardName() + "\");";
    	statement.execute(query2);
    	addAutorecharge(rand.nextInt(10000000), card.getId(), card.getLogin());
    }
    
    //Получить весь список карт
    static public ArrayList<Card> getCards() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from cardtype;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idCardType"),result.getString("name"));
    	}
		
    	String query = "select * from card;";
    	Card card;
    	ArrayList<Card> list = new ArrayList<Card>();
    	result = statement.executeQuery(query);
    	while( result.next()){
    		card = new Card();
    		card.setId(result.getInt("idCard"));
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setLogin(result.getString("login"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setMainCard(result.getBoolean("mainCard"));
    		card.setCardName(result.getString("cardName"));
    		list.add(card);
    	}
    	result.close();
    	return list;
    }
    
    //Получить список карт пользователя по логину
    static public ArrayList<Card> getCards(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	Map<Integer, String> types = getCardTypes();
		
    	String query = "select * from card where login=\"" + login + "\";";
    	Card card;
    	ArrayList<Card> list = new ArrayList<Card>();
    	ResultSet result = statement.executeQuery(query);
    	while( result.next()){
    		card = new Card();
    		card.setId(result.getInt("idCard"));
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setLogin(result.getString("login"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setMainCard(result.getBoolean("mainCard"));
    		card.setCardName(result.getString("cardName"));
    		list.add(card);
    	}
    	result.close();
    	return list;
    }
    
    //Получить список реальных карт
    static public ArrayList<RealCard> getRealCards() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from RealCard;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idCardType"),result.getString("name"));
    	}
		
    	String query = "select * from card;";
    	RealCard card;
    	ArrayList<RealCard> list = new ArrayList<RealCard>();
    	result = statement.executeQuery(query);
    	while( result.next()){
    		card = new RealCard();
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setLastName(result.getString("lastName"));
    		card.setFirstName(result.getString("firstName"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setCash(result.getInt("cash"));
    		list.add(card);
    	}
    	result.close();
    	return list;
    }
    
    //Получить типы карт
    static public Map<Integer, String> getCardTypes() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "select * from cardtype";
    	ResultSet result = statement.executeQuery(query);
    	Map<Integer, String> map = new HashMap<Integer, String>();
    	while(result.next()){
    		map.put(result.getInt("idCardType") ,result.getString("name"));
    	}
    	return map;
    }
    
    //Получить типы операций
    static public Map<Integer,String> getTransactionTypes() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "select * from transactiontype";
    	ResultSet result = statement.executeQuery(query);
    	HashMap<Integer,String> map = new HashMap<Integer,String>();
    	while(result.next()){
    		map.put(result.getInt("idTransactionType"),result.getString("typeName"));
    	}
    	return map;
    }
    
    //Получить аккаунт
    static public User getUser(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "select * from klient where login=" + "\"" + login + "\";";
    	User user = new User();
    	ResultSet result = statement.executeQuery(query);
    	if(result.next()){
    		user.setLogin(login);
    		user.setFirstName(result.getString("firstName"));
    		user.setLastName(result.getString("lastName"));
    		user.setPassword(result.getString("password"));
    		return user;
    	}
    	else return null;
    }
    
    //Получить карту по номеру и логину
    static public Card getCard(String number, String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from cardtype;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idCardType"),result.getString("name"));
    	}
    	
    	String query = "select * from card where number= \"" + number + "\" and login=\"" + login + "\";";
    	Card card = new Card();
    	result = statement.executeQuery(query);
    	if( result.next()){
    		card.setId(result.getInt("idCard"));
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(number);
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setLogin(result.getString("login"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setMainCard(result.getBoolean("mainCard"));
    		card.setCardName(result.getString("cardName"));
    		return card;
    	}
    	else return null;
    }
    
  //Получить карту по номеру и логину
    static public Card getCardByName(String name, String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from cardtype;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idCardType"),result.getString("name"));
    	}
    	
    	String query = "select * from card where cardName= \"" + name + "\" and login=\"" + login + "\";";
    	Card card = new Card();
    	result = statement.executeQuery(query);
    	if( result.next()){
    		card.setId(result.getInt("idCard"));
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setLogin(result.getString("login"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setMainCard(result.getBoolean("mainCard"));
    		card.setCardName(result.getString("cardName"));
    		return card;
    	}
    	else return null;
    }
    
    //Получить карту по идентификатору
    static public Card getCard(int id) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from cardtype;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idCardType"),result.getString("name"));
    	}
    	
    	String query = "select * from card where idCard=" + id + ";";
    	Card card = new Card();
    	result = statement.executeQuery(query);
    	if( result.next()){
    		card.setId(result.getInt("idCard"));
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setLogin(result.getString("login"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setMainCard(result.getBoolean("mainCard"));
    		card.setCardName(result.getString("cardName"));
    		return card;
    	}
    	else return null;
    }
    
    //Получить реальную карту по номеру
    static public RealCard getRealCard(String number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from cardtype;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idCardType"),result.getString("name"));
    	}
    	
    	String query = "select * from RealCard where number=\"" + number + "\";";
    	RealCard card = new RealCard();
    	result = statement.executeQuery(query);
    	if( result.next()){
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setLastName(result.getString("lastName"));
    		card.setFirstName(result.getString("firstName"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setCash(result.getInt("cash"));
    		return card;
    	}
    	else return null;
    }
    
    //Получить список операций по номеру карты
    static public ArrayList<Transaction> getTransactions(String cardNumber) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from TransactionType;";
    	ResultSet result = statement.executeQuery(query1);
    	Map<Integer, String> types = new HashMap<Integer, String>();
    	while(result.next()){
    		types.put(result.getInt("idTransactionType"),result.getString("typeName"));
    	}
    	
    	String query = "select * from transaction where number=" + cardNumber + ";";
    	Transaction transaction;
    	ArrayList<Transaction> list = new ArrayList<Transaction>();
    	result = statement.executeQuery(query);
    	while( result.next()){
    		transaction = new Transaction();
    		transaction.setId(result.getInt("idTransaction"));
    		transaction.setDate(result.getDate("date"));
    		transaction.setCardNumber(cardNumber);
    		transaction.setSumm(result.getInt("summ"));
    		transaction.setTime(result.getTime("time"));
    		transaction.setType(types.get(result.getInt("idTransactionType")));
    		list.add(transaction);
    	}
    	return list;
    }
    
    //Добавить операцию
    static public void addTransaction(Transaction transaction) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from TransactionType where typeName=" + "\"" + transaction.getType() + "\";";
    	ResultSet result = statement.executeQuery(query1);
    	result.next();
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String query2 = "insert into transaction (number, idTransaction, idTransactionType, date, time, summ)" + 
    				"values (\"" + transaction.getCardNumber() + "\", " + (new Integer(transaction.getId())).toString() + ", " + 
    				(new Integer(result.getInt("idTransactionType"))).toString() + ", STR_TO_DATE(\"" + df.format(transaction.getDate()) + "\", \"%d/%m/%Y\"), " + 
    				"\"" + transaction.getTime() + "\", " + (new Integer(transaction.getSumm())).toString() + ");";
    	statement.execute(query2);
    }
    
    //Добавить операцию
    static public void addTransaction(Transaction transaction, String category) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from TransactionType where typeName=" + "\"" + transaction.getType() + "\";";
    	ResultSet result = statement.executeQuery(query1);
    	result.next();
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	
    	String query2 = "insert into transaction (number, idTransaction, idTransactionType, date, time, summ, category)" + 
    				"values (\"" + transaction.getCardNumber() + "\", " + (new Integer(transaction.getId())).toString() + ", " + 
    				(new Integer(result.getInt("idTransactionType"))).toString() + ", STR_TO_DATE(\"" + df.format(transaction.getDate()) + "\", \"%d/%m/%Y\"), " + 
    				"\"" + transaction.getTime() + "\", " + (new Integer(transaction.getSumm())).toString() + ",\"" + category +"\");";
    	statement.execute(query2);
    }
    
    //Удалить карту
    static public void deleteCard(String login, String number) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "delete from card where login=\"" + login + "\" and number=\"" + number +  "\";";
    	statement.execute(query);
    }
    
    //Удалить карту
    static public void deleteCardByName(String login, String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "delete from card where login=\"" + login + "\" and cardName=\"" + name +  "\";";
    	statement.execute(query);
    }
    
    //Проверить совпадение данных реальной карты и карты приложения
    static public boolean checkMatchCards(Card card) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	RealCard realCard = getRealCard(card.getNumber());
    	if(realCard != null){
    		if(realCard.getSecurePassword().compareTo(card.getSecurePassword())==0 &&
    				realCard.getValidDate().equals(card.getValidDate()) &&
    				realCard.getType().compareTo(card.getType())==0)
    			return true;
    	}
    	return false;
    }
    
    //Провести операцию
    static public void makeTransaction(Card cardOut, Card cardIn, int summ) throws Exception{
    	if(!isConnected())
    		connect();
    	Random rand = new Random();
		
		RealCard realCard1 = BDQuerys.getRealCard(cardOut.getNumber()),
				realCard2 = BDQuerys.getRealCard(cardIn.getNumber());
        if(realCard2==null){
        	throw new Exception("Карты получателя с таким номером не существует!");
        }
        if(realCard1.getCash() < summ){
        	throw new Exception("На счету вашей карты недостаточно средств!");
        }
        Map<Integer, String> transactionTypes = BDQuerys.getTransactionTypes();
        Transaction outTransaction = new Transaction(),
        		inTransaction = new Transaction();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        
        outTransaction.setCardNumber(cardOut.getNumber());
        outTransaction.setType(transactionTypes.get(OUTTRANSACTION));
        outTransaction.setSumm(summ);
        outTransaction.setId(rand.nextInt(10000000));
        outTransaction.setDate(date);
        outTransaction.setTime(Time.valueOf(dateFormat.format(date)));
        outTransaction.setCategory("");
        BDQuerys.addTransaction(outTransaction, "Перевод");
        
        inTransaction.setType(transactionTypes.get(INTRANSACTION));
        inTransaction.setSumm(summ);
        inTransaction.setDate(date);
        inTransaction.setTime(Time.valueOf(dateFormat.format(date)));
        inTransaction.setId(rand.nextInt(10000000));
        inTransaction.setCardNumber(cardIn.getNumber());
        inTransaction.setCategory("");
        BDQuerys.addTransaction(inTransaction, "Перевод");
    }
    
    //Сделать карту основной
    static public void setMainCard(Card card) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query1 = "select * from card where login=\"" + card.getLogin() + "\" and mainCard=true;";
    	ResultSet result = statement.executeQuery(query1);
    	result.next();
    	String query2 = "update card set mainCard=false where idCard=" + (new Integer(result.getInt("idCard"))).toString() + ";";
    	statement.execute(query2);
    	String query3 = "update card set mainCard=true where idCard=" + card.getId().toString() + ";";
    	statement.execute(query3);
    }
    
    //Получить основную карту
    static public Card getMainCard(String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	Map<Integer, String> types = getCardTypes();
    	
    	String query = "select * from card where login=\"" + login + "\" and mainCard=true;";
    	ResultSet result = statement.executeQuery(query);
    	result.next();
    	Card card = new Card();
    	result = statement.executeQuery(query);
    	if( result.next()){
    		card.setId(result.getInt("idCard"));
    		card.setValidDate(result.getDate("validDate"));
    		card.setNumber(result.getString("number"));
    		card.setSecurePassword(result.getString("securePassword"));
    		card.setLogin(result.getString("login"));
    		card.setType(types.get(result.getInt("idCardType")));
    		card.setMainCard(result.getBoolean("mainCard"));
    		card.setCardName(result.getString("cardName"));
    		return card;
    	}
    	else return null;
    }
    
    //Изменить сумму на карте
    static public void updateRealCardCash(String number, int cash) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "update realcard set cash=" + (new Integer(cash)).toString() + " where number=\"" + number + "\";";
    	statement.execute(query);
    }
    
    //Добавить данные автопополнения
    static public void addAutorecharge(Integer idAutorecharge,Integer idCard, String login) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "insert into autocharge (idAutorecharge, idCard, login, byTime, byAmount) values" + 
    					"(" + idAutorecharge.toString() + "," + idCard.toString() + ",\"" + login + "\", false, false);";
    	statement.execute(query);
    }
    
    //Получить данные автопополнения
    static public Autorecharge getAutorecharge(Integer idCard) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "select * from autocharge where idCard=" + idCard.toString() + ";";
    	ResultSet result = statement.executeQuery(query);
    	if(!result.next())
    		return null;
    	Autorecharge arc = new Autorecharge();
    	arc.setId(result.getInt("idAutorecharge"));
    	arc.setAmountSinceDate(result.getDate("AmountSinceDate"));
    	arc.setAmountSumm(result.getInt("AmountSumm"));
    	arc.setAmountToDate(result.getDate("AmountToDate"));
    	arc.setAmountRechargeSumm(result.getInt("AmountRechargeSumm"));
    	arc.setByAmount(result.getBoolean("ByAmount"));
    	arc.setByTime(result.getBoolean("ByTime"));
    	arc.setIdCard(result.getInt("IdCard"));
    	arc.setId(result.getInt("idAutorecharge"));
    	arc.setLogin(result.getString("login"));
    	arc.setTime(result.getInt("time"));
    	arc.setTimeSinceDate(result.getDate("TimeSinceDate"));
    	arc.setTimeSumm(result.getInt("TimeSumm"));
    	arc.setTimeToDate(result.getDate("TimeToDate"));
    	return arc;
    }
    
    //Обновить данные автопополнения
    static public void updateAutorecharge(Autorecharge arc) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    	String query = "update autocharge set byTime=" + arc.getByTime().toString() + ", timeSinceDate=STR_TO_DATE(\"" +
    			df.format(arc.getTimeSinceDate()) + "\", \"%d/%m/%Y\"), timeToDate=STR_TO_DATE(\"" + 
    			df.format(arc.getTimeToDate()) + "\", \"%d/%m/%Y\"), time=" + arc.getTime().toString() + 
    			",timeSumm=" + arc.getTimeSumm().toString() + ", byAmount=" + arc.getByAmount().toString() + 
    			", amountSinceDate=STR_TO_DATE(\"" + df.format(arc.getAmountSinceDate()) + 
    			"\", \"%d/%m/%Y\"), amountToDate=STR_TO_DATE(\"" + df.format(arc.getAmountToDate()) + 
    			"\", \"%d/%m/%Y\"), amountRechargeSumm=" + arc.getAmountRechargeSumm().toString() + 
    			", amountSumm=" + arc.getAmountSumm().toString() + " where idAutorecharge=" + arc.getId().toString() + ";";
    	statement.execute(query);
    }
    
    //Обновить имя карты
    static public void updateCardName(Card card, String name) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    	if(!isConnected())
    		connect();
    	String query = "update card set cardName=\"" + name + "\" where idCard=" + card.getId().toString() + ";";
    	statement.execute(query);
    }
}
