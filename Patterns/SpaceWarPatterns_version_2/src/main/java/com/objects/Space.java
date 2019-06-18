package com.objects;

import com.enums.PlayerColor;
import com.interfaces.Observer;
import com.interfaces.Publisher;
import com.objects.planet.Planet;
import com.objects.planet.PlanetPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 */
public class Space implements Publisher{

    public static final int SPACEWIDTH = 100;
    public static final int SPACEHEIGHT = 100;
	/**
	 * 
	 */
	private LocalizableObject[][] field;
    private Set<Observer> observers;

	/**
	 * 
	 */
	private static Space instance;

	/**
	 * @return
	 */
	public void clear() {
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] instanceof Planet)
                    PlanetPool.getInstance().releaseObject((Planet) field[i][j]);
                field[i][j] = null;
            }
        setBorder();
    }

	/**
	 * 
	 */
	private Space() {
        field = new LocalizableObject[SPACEWIDTH + 2][SPACEHEIGHT + 2];
        observers = new HashSet<Observer>();
        setBorder();
    }

	/**
	 * @return
	 */
	public static Space getInstance() {
		if(instance == null)
			instance = new Space();
		return instance;
	}

	public static double distance(int sx, int sy, int tx, int ty) {
		return Math.sqrt((sx - tx) * (sx - tx) + (sy - ty) * (sy - ty));
	}

    private void setBorder(){
        for (int i = 0; i < SPACEWIDTH + 2; i++) {
            field[i][0] = new LocalizableObject();
            field[i][0].setImmortal(true);
            field[i][SPACEWIDTH + 1] = new LocalizableObject();
            field[i][SPACEWIDTH + 1].setImmortal(true);
        }
        for (int j = 0; j < SPACEHEIGHT + 2; j++) {
            field[0][j] = new LocalizableObject();
            field[0][j].setImmortal(true);
            field[SPACEHEIGHT + 1][j] = new LocalizableObject();
            field[SPACEHEIGHT + 1][j].setImmortal(true);
        }
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserber(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObserbers() {
        for(Observer i: observers)
            i.update();
    }

    public void set(LocalizableObject obj, int x, int y){
        field[x][y]=obj;
        notifyObserbers();
    }

    public LocalizableObject get(int x, int y){
        return field[x][y];
    }

    public int oXLen(){
        return field.length;
    }

    public int oYLen(){
        return field[0].length;
    }
}