package com.objects.planet;

import com.enums.PlayerColor;
import com.enums.RaceType;
import com.interfaces.drawing.PlanetDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.objects.LocalizableObject;
import com.objects.creator.ShipCreator;
import com.objects.visitor.CollectorOfInformation;
import com.sql.SingletonSQLQuerys;

import java.util.*;

/**
 * 
 */
public class Planet extends LocalizableObject {

	protected int resources;
	protected ShipCreator shipCreator;


	public Planet(int x, int y, PlayerColor _playerColor, RaceType raceType) {
		setLocation(x, y);
		playerColor = _playerColor;
		Random rand = new Random();
		resources = SingletonSQLQuerys.getInstance().getPlanetResources() - rand.nextInt(2000) + 1000;
		shipCreator = new ShipCreator(this);
		drawing = new PlanetDrawing(this);
		immortal = true;
		this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
	}

	public Planet(int x, int y, PlayerColor _playerColor) {
		setLocation(x, y);
		playerColor = _playerColor;
		Random rand = new Random();
		resources = SingletonSQLQuerys.getInstance().getPlanetResources() - rand.nextInt(2000) + 1000;
		shipCreator = new ShipCreator(this);
		drawing = new PlanetDrawing(this);
		immortal = true;
		this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
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
		resources -= value;
	}

	public void captured(PlayerColor pc) {
		playerColor = pc;
	}

	public ShipCreator getShipCreator() {
		return shipCreator;
	}

	public PlanetMemento createMemento(){
		return new PlanetMemento(resources, playerColor);
	}

	public void resetState(PlanetMemento memento){
		resources = memento.getResources();
		setPlayerColor(memento.getColor());
	}

	@Override
	public void accept(CollectorOfInformation visitor) {
		visitor.visitPlanet(this);
	}
}