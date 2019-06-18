package com;

import com.objects.Planet;

import java.util.ArrayList;

/**
 * Created by Sergey on 25.12.2015.
 */
public class Constants {
    //Размеры поля
    public static final int SPACEWIDTH = 100;
    public static final int SPACEHEIGHT = 100;
    //Цена кораблей
    public static final int WARSHIPPRICE = 200;
    public static final int REGENSHIPPRICE = 300;
    public static final int STEALTHSHIPPRICE = 330;
    public static final int TRANSPORTSHIPPRICE = 150;
    public static final int RADARSHIPPRICE = 250;
    //Параметры WarShip
    public static final int WARSHIPARMOR = 300;
    public static final int WARSHIPSPEED = 3;
    public static final int WARSHIPVISBILITY = 20;
    public static final int WARSHIPDAMAGE = 20;
    //Параметры RadarShip
    public static final int RADARSHIPARMOR = 400;
    public static final int RADARSHIPSPEED = 4;
    public static final int RADARSHIPVISIBILITY = 30;
    //Параметры TransportShip
    public static final int TRANSPORTSHIPARMOR = 450;
    public static final int TRANSPORTSHIPSPEED = 5;
    public static final int TRANSPORTSHIPVISIBILITY = 15;
    //Параметры RegenShip
    public static final int REGENSHIPARMOR = 250;
    public static final int REGENSHIPSPEED = 3;
    public static final int REGENSHIPVISIBILITY = 20;
    public static final int REGENSHIPDAMAGE = 25;
    //Параметры StealthShip
    public static final int STEALTHSHIPARMOR = 180;
    public static final int STEALTHSHIPSPEED = 4;
    public static final int STEALTHSHIPVISIBILITY = 25;
    public static final int STEALTHSHIPDAMAGE = 15;

    public static final ArrayList<Planet> PLANETS = new ArrayList<Planet>();
    public static final int PLANETNUMBER = 9;

    //Максимальное колличество кораблей
    public static final int MAXWARSHIPNUMBER = 6;
    public static final int MAXSPECIALSHIPNUMBER = 4;
    public static final int MAXTRANSPORTSHIPNUMBER = 4;
    public static final int MAXRADARSHIPNUMBER = 4;

    public static final int PLANETRESOURCES = 3000;
    public static final int MAKEVISIBLERANGE = 30;

}
