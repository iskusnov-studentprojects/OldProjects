package com.objects;

import com.Constants;
import com.enums.PlayerColor;
import com.enums.RaceType;
import com.enums.ShipTypes;
import com.interfaces.ShipCreator;
import com.interfaces.drawing.PlanetDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.objects.LocalizableObject;
import com.objects.factory.RegenerationRaceFactory;
import com.objects.factory.StealthRaceFactory;
import com.objects.ships.SpaceShip;

import java.util.*;

/**
 * 
 */
public class Planet extends LocalizableObject {

	/**
	 * 
	 */
	protected int resources;

	/**
	 * 
	 */
	protected ShipCreator shipCreator;


	/**
	 * 
	 */
	public Planet(int x, int y, PlayerColor _playerColor, RaceType raceType){
		setLocation(x, y);
		playerColor = _playerColor;
		Random rand = new Random();
		resources = Constants.PLANETRESOURCES - rand.nextInt(2000) + 1000;
		if(RaceType.RegenerationRace == raceType)
			shipCreator = new RegenerationRaceFactory(this);
		else
			shipCreator = new StealthRaceFactory(this);
                drawing = new PlanetDrawing(this);
                immortal = true;
                this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
	}
        
        public Planet(int x, int y, PlayerColor _playerColor){
		setLocation(x, y);
		playerColor = _playerColor;
		Random rand = new Random();
		resources = Constants.PLANETRESOURCES - rand.nextInt(2000) + 1000;
		shipCreator = new RegenerationRaceFactory(this);
                drawing = new PlanetDrawing(this);
                immortal = true;
                this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
	}
        
        public void setFactory(PlayerColor playerColor){
            if(RaceType.RegenerationRace.equals(playerColor))
                shipCreator = new RegenerationRaceFactory(this);
            else
                shipCreator = new StealthRaceFactory(this);
        }

	/**
	 * @return
	 */
	public int getResources() {
		return resources;
	}

	/**
	 * @param value 
	 * @return
	 */
	public void pay(int value) {
		resources-=value;
	}

	public void captured(PlayerColor pc){
		playerColor = pc;
                setFactory(pc);
	}

	public ShipCreator getShipCreator() {
		return shipCreator;
	}
}