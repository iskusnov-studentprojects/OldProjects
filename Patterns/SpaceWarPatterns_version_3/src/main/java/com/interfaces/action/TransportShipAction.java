package com.interfaces.action;

import com.enums.PlayerColor;
import com.objects.LocalizableObject;
import com.objects.planet.Planet;
import com.objects.Space;
import com.objects.ships.SpaceShip;

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
		if(!busy || Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y).getPlayerColor() != PlayerColor.None){
			busy = false;
			if(ship.getRace().resourcesAreDepleted())
				for(int i = 0; i < Space.getInstance().oXLen(); i++) {
					for (int j = 0; j < Space.getInstance().oYLen(); j++) {
						if (Space.getInstance().get(i,j) instanceof Planet && Space.getInstance().get(i,j).getPlayerColor() == PlayerColor.None) {
							busy = true;
							ship.setTarget(Space.getInstance().get(i,j).getLocation().X, Space.getInstance().get(i,j).getLocation().Y);
							break;
						}
					}
					if(busy)
						break;
				}
			if(!busy) {
				if(Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4 || ship.getTarget().X <= 0)
					do{
						ship.setRandomTarget();
					}while(Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y) != null || Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4);
				ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
			}
		}else{
			if(Space.distance(ship.getLocation().X, ship.getLocation().Y, ship.getTarget().X, ship.getTarget().Y) < ship.getVisibility() * 0.2){
				((Planet)Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y)).captured(ship.getPlayerColor());
				((Planet)Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y)).setFactory(ship.getRace().getRaceType());
				Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y).setRace(ship.getRace());
				ship.getRace().getPlanets().add((Planet) Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y));
				ship.getDamage(100000);
			}
			else
				ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
		}
	}

}