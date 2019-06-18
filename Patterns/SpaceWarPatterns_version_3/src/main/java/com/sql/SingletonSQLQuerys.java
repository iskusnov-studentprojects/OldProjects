package com.sql;

/**
 * Created by Sergey on 27.01.2016.
 */
public class SingletonSQLQuerys extends SQLQuerys{
    private static SingletonSQLQuerys instance;

    private SingletonSQLQuerys(SQLQuerysImpl impl) {
        super(impl);
    }

    public static SingletonSQLQuerys getInstance(){
        if(instance == null)
            instance = new SingletonSQLQuerys(new SQLiteJDBC());
        return instance;
    }
}
