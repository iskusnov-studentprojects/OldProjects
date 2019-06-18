package com.objects;

import com.enums.PlayerColor;
import com.enums.RaceType;
import com.interfaces.IIterator;
import com.objects.creator.*;
import com.objects.planet.Planet;
import com.objects.queue.MyQueue;
import com.objects.ships.ShipsLimit;
import com.objects.ships.SpaceShip;
import com.objects.visitor.CollectorOfInformation;
import com.objects.visitor.ConcreteCollector;
import com.sql.SingletonSQLQuerys;

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
		ships = new MyQueue<SpaceShip>();
		planets = new ArrayList<Planet>();
		limits = SingletonSQLQuerys.getInstance().getShipsLimit();
	}

	protected RaceType raceType;
	protected MyQueue<SpaceShip> ships;
	protected ArrayList<Planet> planets;
	protected int warShipNumber;
	protected int specialShipNumber;
	protected int radarShipNumber;
	protected int transportShipNumber;
	protected PlayerColor playerColor;
	protected ShipsLimit limits;

	/**
	 * @return
	 */
	public void turn() {
		IIterator<SpaceShip> iterator = ships.getIterator();
		for (; iterator.hasNext(); iterator.next()) {
			if (iterator.get() != null)
				iterator.get().doAction();
		}

		for (Planet i : planets)
			if (i.playerColor == this.playerColor && i.getResources() > 150)
				planetAction(i);
	}

	public RaceType getRaceType() {
		return raceType;
	}

	public void setRaceType(RaceType raceType) {
		this.raceType = raceType;
	}

	public MyQueue<SpaceShip> getShips() {
		return ships;
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
		if (warShipNumber < limits.getWarShipLimit()) {
			p.getShipCreator().setBuilder(WarShipBuilder.class);
			p.getShipCreator().constructShip();
			ships.add(p.getShipCreator().getShip());
			warShipNumber++;
			return;
		}
		if (transportShipNumber < limits.getTransportShipLimit()) {
			p.getShipCreator().setBuilder(TransportShipBuilder.class);
			p.getShipCreator().constructShip();
			ships.add(p.getShipCreator().getShip());
			transportShipNumber++;
			return;
		}
		if (radarShipNumber < limits.getRadarShipLimit()) {
			p.getShipCreator().setBuilder(RadarShipBuilder.class);
			p.getShipCreator().constructShip();
			ships.add(p.getShipCreator().getShip());
			radarShipNumber++;
			return;
		}
		if(raceType == RaceType.StealthRace) {
			if (specialShipNumber < limits.getStealthShipLimit()) {
				p.getShipCreator().setBuilder(StealthShipBuilder.class);
				p.getShipCreator().constructShip();
				ships.add(p.getShipCreator().getShip());
				specialShipNumber++;
				return;
			}
		}
		else {
			if(specialShipNumber < limits.getRegenShipLimit()){
				p.getShipCreator().setBuilder(RegenShipBuilder.class);
				p.getShipCreator().constructShip();
				ships.add(p.getShipCreator().getShip());
				specialShipNumber++;
				return;
			}
		}
	}

	public String[] getInformation(){
		CollectorOfInformation visitor = new ConcreteCollector();
		IIterator<SpaceShip> iterator = ships.getIterator();
		for(Planet i: planets)
			i.accept(visitor);
		for(;iterator.hasNext();iterator.next())
			iterator.get().accept(visitor);
		return visitor.getInformation();
	}

	public boolean isAlive(){
		int allPlanets = 0, poorPlanets = 0;
		for(Planet i: planets) {
			allPlanets++;
			if (i.getResources() <= 200)
				poorPlanets++;
		}
		if(allPlanets == poorPlanets && ships.isEmpty())
			return false;
		else
			return true;
	}

	public boolean resourcesAreDepleted(){
		int allPlanets = 0, poorPlanets = 0;
		for(Planet i: planets) {
			allPlanets++;
			if (i.getResources() < 1000)
				poorPlanets++;
		}
		return allPlanets == poorPlanets ? true : false;
	}

	public void removeShip(SpaceShip ship){
		switch (ship.getType()) {
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
		ships.remove(ship);
	}
}