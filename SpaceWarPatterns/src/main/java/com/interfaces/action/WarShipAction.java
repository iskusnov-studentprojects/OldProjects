package com.interfaces.action;

import com.Constants;
import com.objects.Space;
import com.objects.ships.SpaceShip;
import com.objects.ships.prototypes.WarShip;

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
		if(ship.location.X + ship.getVisibility() < Constants.SPACEWIDTH + 2)
			endi = ship.location.X + ship.getVisibility();
		else
			endi = Constants.SPACEHEIGHT + 2;
		if(ship.location.Y - ship.getVisibility() > 0)
			begj = ship.location.Y - ship.getVisibility();
		else
			begj = 0;
		if(ship.location.Y + ship.getVisibility() < Constants.SPACEHEIGHT + 2)
			endj = ship.location.Y + ship.getVisibility();
		else
			endj = Constants.SPACEHEIGHT + 2;

		for(int i = begi; i < endi; i++)
			for(int j = begj; j < endj; j++){
				if(Space.getInstance().field[i][j] != null && Space.getInstance().field[i][j].getPlayerColor() != ship.getPlayerColor() && Space.getInstance().field[i][j].isVisible() && !Space.getInstance().field[i][j].isImmortal()){
					if(Space.distance(ship.location.X, ship.location.Y, Space.getInstance().field[i][j].location.X, Space.getInstance().field[i][j].location.Y) < ship.getVisibility()){
						ship.getTarget().setLocation(Space.getInstance().field[i][j].location.X, Space.getInstance().field[i][j].location.Y);
						if(Space.distance(ship.location.X, ship.location.Y, Space.getInstance().field[i][j].location.X, Space.getInstance().field[i][j].location.Y) < ship.getVisibility() * 0.7) {
							ship.attack(Space.getInstance().field[i][j]);
						}
						else
							ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
						return;
					}
				}
			}

		if(Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4 || ship.getTarget().X <= 0)
			do{
				ship.setRandomTarget();
			}while(Space.getInstance().field[ship.getTarget().X][ship.getTarget().Y] != null || Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4);
		ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
	}

}