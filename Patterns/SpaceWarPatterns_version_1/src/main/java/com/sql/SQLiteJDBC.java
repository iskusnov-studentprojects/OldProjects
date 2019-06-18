package com.sql;

import com.objects.ships.ShipAttributes;
import com.objects.ships.ShipPrices;
import com.objects.ships.ShipsLimit;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Sergey on 27.01.2016.
 */
public class SQLiteJDBC extends SQLQuerysImpl {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public SQLiteJDBC() {
        connection = null;
        statement = null;
        resultSet = null;
    }

    @Override
    public void connect() {
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\resources\\data.s3db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disconnect(){
        if(connection != null){
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected(){
        if(connection == null)
            return false;
        else
            return true;
    }

    @Override
    public ShipAttributes getWarShipAttributes() {
        if(!isConnected())
            connect();
        ShipAttributes attributes = null;
        try {
            resultSet = statement.executeQuery("select name, armor, speed, visibility from shipattributes WHERE name = \"WarShip\";");
            attributes = new ShipAttributes(resultSet.getInt("armor"), resultSet.getInt("speed"), resultSet.getInt("visibility"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public ShipAttributes getStealthShipAttributes() {
        if(!isConnected())
            connect();
        ShipAttributes attributes = null;
        try {
            resultSet = statement.executeQuery("select name, armor, speed, visibility from shipattributes WHERE name = \"StealthShip\";");
            attributes = new ShipAttributes(resultSet.getInt("armor"), resultSet.getInt("speed"), resultSet.getInt("visibility"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public ShipAttributes getRegenShipAttributes() {
        if(!isConnected())
            connect();
        ShipAttributes attributes = null;
        try {
            resultSet = statement.executeQuery("select name, armor, speed, visibility from shipattributes WHERE name = \"RegenShip\";");
            attributes = new ShipAttributes(resultSet.getInt("armor"), resultSet.getInt("speed"), resultSet.getInt("visibility"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public ShipAttributes getRadarShipAttributes() {
        if(!isConnected())
            connect();
        ShipAttributes attributes = null;
        try {
            resultSet = statement.executeQuery("select name, armor, speed, visibility from shipattributes WHERE name = \"RadarShip\";");
            attributes = new ShipAttributes(resultSet.getInt("armor"), resultSet.getInt("speed"), resultSet.getInt("visibility"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public ShipAttributes getTransportShipAttributes() {
        if(!isConnected())
            connect();
        ShipAttributes attributes = null;
        try {
            resultSet = statement.executeQuery("select name, armor, speed, visibility from shipattributes WHERE name = \"TransportShip\";");
            attributes = new ShipAttributes(resultSet.getInt("armor"), resultSet.getInt("speed"), resultSet.getInt("visibility"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public int getWarShipDamage() {
        if(!isConnected())
            connect();
        int damage = 0;
        try {
            resultSet = statement.executeQuery("select name, damage from shipattributes WHERE name = \"WarShip\";");
            damage = resultSet.getInt("damage");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damage;
    }

    @Override
    public int getRegenShipDamage() {
        if(!isConnected())
            connect();
        int damage = 0;
        try {
            resultSet = statement.executeQuery("select name, damage from shipattributes WHERE name = \"RegenShip\";");
            damage = resultSet.getInt("damage");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damage;
    }

    @Override
    public int getStealthShipDamage() {
        if(!isConnected())
            connect();
        int damage = 0;
        try {
            resultSet = statement.executeQuery("select name, damage from shipattributes WHERE name = \"StealthShip\";");
            damage = resultSet.getInt("damage");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damage;
    }

    @Override
    public int getNumberPlanets() {
        if(!isConnected())
            connect();
        int result = 0;
        try {
            resultSet = statement.executeQuery("SELECT key, PLANETNUMBER FROM variousdata WHERE key = 1;");
            result = resultSet.getInt("PLANETNUMBER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int getMakeVisibleRange() {
        if(!isConnected())
            connect();
        int result = 0;
        try {
            resultSet = statement.executeQuery("SELECT key, MAKEVISIBLERANGE FROM variousdata WHERE key = 1;");
            result = resultSet.getInt("MAKEVISIBLERANGE");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ShipPrices getShipPrices() {
        if(!isConnected())
            connect();
        int warShipPrice = 0,
                regenShipPrice = 0,
                stealthShipPrice = 0,
                radarShipPrice = 0,
                transportShipPrice = 0;
        try {
            resultSet = statement.executeQuery("select name, price from shipattributes;");
            do{
                String s = resultSet.getString("name");
                if (s.equals("WarShip")) {
                    warShipPrice = resultSet.getInt("price");

                } else if (s.equals("RegenShip")) {
                    regenShipPrice = resultSet.getInt("price");

                } else if (s.equals("StealthShip")) {
                    stealthShipPrice = resultSet.getInt("price");

                } else if (s.equals("RadarShip")) {
                    radarShipPrice = resultSet.getInt("price");

                } else if (s.equals("TransportShip")) {
                    transportShipPrice = resultSet.getInt("price");
                }
            }while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ShipPrices(warShipPrice, regenShipPrice, stealthShipPrice, radarShipPrice, transportShipPrice);
    }

    @Override
    public int getPlanetResources() {
        if(!isConnected())
            connect();
        int result = 0;
        try {
            resultSet = statement.executeQuery("SELECT key, PLANETRESOURCES FROM variousdata WHERE key = 1;");
            result = resultSet.getInt("PLANETRESOURCES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ShipsLimit getShipsLimit() {
        if(!isConnected())
            connect();
        int warShipLimit = 0,
                regenShipLimit = 0,
                stealthShipLimit = 0,
                radarShipLimit = 0,
                transportShipLimit = 0;
        try {
            resultSet = statement.executeQuery("select name, maxnumber from shipattributes;");
            do{
                String s = resultSet.getString("name");
                if (s.equals("WarShip")) {
                    warShipLimit = resultSet.getInt("maxnumber");

                } else if (s.equals("RegenShip")) {
                    regenShipLimit = resultSet.getInt("maxnumber");

                } else if (s.equals("StealthShip")) {
                    stealthShipLimit = resultSet.getInt("maxnumber");

                } else if (s.equals("RadarShip")) {
                    radarShipLimit = resultSet.getInt("maxnumber");

                } else if (s.equals("TransportShip")) {
                    transportShipLimit = resultSet.getInt("maxnumber");
                }
            }while (resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ShipsLimit(warShipLimit, stealthShipLimit, regenShipLimit, radarShipLimit, transportShipLimit);
    }
}
