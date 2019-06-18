package com.interfaces.action;

import com.objects.Space;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class WarShipAction implements IAction {

	/**
	 * Default constructor
	 */
	public WarShipAction(SpaceShip ship) {
		this.ship = ship;
	}

	/**
	 * 
	 */
	protected SpaceShip ship;

	/**
	 * @return
	 */
	public void action() {
		int begi, endi, begj, endj;
		if(ship.location.X - ship.getVisibility() > 0)
			begi = ship.location.X - ship.getVisibility();
		else
			begi = 0;
		if(ship.location.X + ship.getVisibility() < Space.SPACEWIDTH + 2)
			endi = ship.location.X + ship.getVisibility();
		else
			endi = Space.SPACEHEIGHT + 2;
		if(ship.location.Y - ship.getVisibility() > 0)
			begj = ship.location.Y - ship.getVisibility();
		else
			begj = 0;
		if(ship.location.Y + ship.getVisibility() < Space.SPACEHEIGHT + 2)
			endj = ship.location.Y + ship.getVisibility();
		else
			endj = Space.SPACEHEIGHT + 2;

		for(int i = begi; i < endi; i++)
			for(int j = begj; j < endj; j++){
				if(Space.getInstance().get(i,j) != null && Space.getInstance().get(i,j).getPlayerColor() != ship.getPlayerColor() && Space.getInstance().get(i,j).isVisible() && !Space.getInstance().get(i,j).isImmortal()){
					if(Space.distance(ship.location.X, ship.location.Y, Space.getInstance().get(i,j).location.X, Space.getInstance().get(i,j).location.Y) < ship.getVisibility()){
						ship.getTarget().setLocation(Space.getInstance().get(i,j).location.X, Space.getInstance().get(i,j).location.Y);
						if(Space.distance(ship.location.X, ship.location.Y, Space.getInstance().get(i,j).location.X, Space.getInstance().get(i,j).location.Y) < ship.getVisibility() * 0.7) {
							ship.attack(Space.getInstance().get(i,j));
						}
						else
							ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
						return;
					}
				}
			}

		if(Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4)
			do{
				ship.setRandomTarget();
			}while(Space.getInstance().get(ship.getTarget().X, ship.getTarget().Y) != null || Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4);
		ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
	}

}