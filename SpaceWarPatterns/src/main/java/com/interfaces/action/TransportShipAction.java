package com.interfaces.action;

import com.Constants;
import com.enums.PlayerColor;
import com.objects.Planet;
import com.objects.Space;
import com.objects.ships.SpaceShip;

import java.util.*;

/**
 * 
 */
public class TransportShipAction implements IAction {

	boolean busy;
	/**
	 * Default constructor
	 */
	public TransportShipAction(SpaceShip ship) {
		this.ship = ship;
		busy = false;
	}

	/**
	 * 
	 */
	protected SpaceShip ship;

	/**
	 * @return
	 */
	public void action() {
		if(!busy || Space.getInstance().field[ship.getTarget().X][ship.getTarget().Y].getPlayerColor() != PlayerColor.None){
			int allPlanets = 0, poorPlanets = 0;
			for(Planet i: Constants.PLANETS)
				if(i.getPlayerColor() == ship.getPlayerColor()){
					allPlanets++;
					if(i.getResources() < 1000)
						poorPlanets++;
				}
			if(allPlanets == poorPlanets)
				for(Planet i: Constants.PLANETS){
					if(i.getPlayerColor() == PlayerColor.None){
						busy = true;
						ship.getTarget().setLocation(i.location.X, i.location.Y);
						break;
					}
				}
			if(!busy) {
				if(Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4 || ship.getTarget().X <= 0)
					do{
						ship.setRandomTarget();
					}while(Space.getInstance().field[ship.getTarget().X][ship.getTarget().Y] != null || Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4);
				ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
			}
		}else{
			if(Space.getInstance().field[ship.getTarget().X][ship.getTarget().Y].getPlayerColor() != PlayerColor.None)
				busy = false;
			if(Space.distance(ship.getLocation().X, ship.getLocation().Y, ship.getTarget().X, ship.getTarget().Y) < ship.getVisibility() * 0.2){
				((Planet)Space.getInstance().field[ship.getTarget().X][ship.getTarget().Y]).captured(ship.getPlayerColor());
				ship.getDamage(100000);
			}
			else
				ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
		}
	}

}