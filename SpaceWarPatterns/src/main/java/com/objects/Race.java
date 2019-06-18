package com.objects;

import com.Constants;
import com.enums.PlayerColor;
import com.enums.RaceType;
import com.objects.ships.SpaceShip;
import java.awt.Graphics;

import java.util.*;

/**
 * 
 */
public class Race {

	public Race(RaceType raceType, PlayerColor playerColor) {
		this.raceType = raceType;
		this.playerColor = playerColor;
		warShipNumber = 0;
		specialShipNumber = 0;
		radarShipNumber = 0;
		transportShipNumber = 0;
                ships = new ArrayList<SpaceShip>();
                planets = new ArrayList<Planet>();
	}

	/**
	 * 
	 */
	protected RaceType raceType;

	/**
	 * 
	 */
	protected ArrayList<SpaceShip> ships;

	/**
	 * 
	 */
	protected ArrayList<Planet> planets;

	/**
	 * 
	 */
	protected int warShipNumber;

	/**
	 * 
	 */
	protected int specialShipNumber;

	/**
	 * 
	 */
	protected int radarShipNumber;

	/**
	 * 
	 */
	protected int transportShipNumber;

	/**
	 * 
	 */
	protected PlayerColor playerColor;

	/**
	 * @return
	 */
	public void turn() {
		for(int i = 0; i < ships.size(); i++)
			if(ships.get(i).playerColor != this.playerColor){
				switch(ships.get(i).getType()){
					case WarShip:
						warShipNumber--;
						break;
					case StealthShip:
					case RegenShip:
						specialShipNumber--;
						break;
					case TransportShip:
						transportShipNumber--;
						break;
					case RadarShip:
						radarShipNumber--;
						break;
				}
				ships.remove(i);
				i--;
			}
		TemplateIterator<SpaceShip> iterator = new TemplateIterator<SpaceShip>(ships);
		for(;!iterator.isEnd();iterator.next()){
                    if(iterator.get() != null)
			iterator.get().doAction();
                }
		for(Planet i: planets)
			if(i.playerColor == this.playerColor && i.resources > 150)
				planetAction(i);
	}

	public RaceType getRaceType() {
		return raceType;
	}

	public void setRaceType(RaceType raceType) {
		this.raceType = raceType;
	}

	public ArrayList<SpaceShip> getShips() {
		return ships;
	}

	public void setShips(ArrayList<SpaceShip> ships) {
		this.ships = ships;
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	public int getWarShipNumber() {
		return warShipNumber;
	}

	public void setWarShipNumber(int warShipNumber) {
		this.warShipNumber = warShipNumber;
	}

	public int getStealthShipNumber() {
		return specialShipNumber;
	}

	public void setStealthShipNumber(int stealthShipNumber) {
		this.specialShipNumber = stealthShipNumber;
	}

	public int getRadarShipNumber() {
		return radarShipNumber;
	}

	public void setRadarShipNumber(int radarShipNumber) {
		this.radarShipNumber = radarShipNumber;
	}

	public int getTransportShipNumber() {
		return transportShipNumber;
	}

	public void setTransportShipNumber(int transportShipNumber) {
		this.transportShipNumber = transportShipNumber;
	}

	public PlayerColor getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(PlayerColor playerColor) {
		this.playerColor = playerColor;
	}

	private void planetAction(Planet p) {
		if (warShipNumber < Constants.MAXWARSHIPNUMBER) {
			ships.add(p.getShipCreator().createWarShip());
			warShipNumber++;
			return;
		}
		if (transportShipNumber < Constants.MAXTRANSPORTSHIPNUMBER) {
			ships.add(p.getShipCreator().createTransportShip());
			transportShipNumber++;
			return;
		}
		if (radarShipNumber < Constants.MAXRADARSHIPNUMBER) {
			ships.add(p.getShipCreator().createRadarShip());
			radarShipNumber++;
			return;
		}
		if (specialShipNumber < Constants.MAXSPECIALSHIPNUMBER) {
			ships.add(p.getShipCreator().createSpecialShip());
			specialShipNumber++;
			return;
		}

	}

    public void draw(Graphics g, int width, int height) {
                for(LocalizableObject i : ships){
            i.getDrawing().draw(g, width, height);
        }
    }
}