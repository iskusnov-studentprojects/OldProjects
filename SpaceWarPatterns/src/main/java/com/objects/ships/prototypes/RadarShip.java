package com.objects.ships.prototypes;

import com.Constants;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.interfaces.action.RadarShipAction;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.objects.LocalizableObject;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class RadarShip extends SpaceShip implements IPrototype {

	/**
	 * Default constructor
	 */
	public RadarShip() {
		this.setAction(new RadarShipAction(this));
		this.setDrawing(new RadarShipDrawing(this));
		this.setMovable(new Movable(this));
		this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
		this.setImmortal(false);
	}

	/**
	 * @return
	 */
	public LocalizableObject clone() {
		RadarShip ship = new RadarShip();
		ship.setArmor(armor);
		ship.setSpeed(speed);
		ship.setVisibility(visibility);
		return ship;
	}

	@Override
	public ShipTypes getType(){return ShipTypes.RadarShip;}

	public static IPrototype createPrototype(){
		RadarShip ship = new RadarShip();
		ship.setArmor(Constants.RADARSHIPARMOR);
		ship.setSpeed(Constants.RADARSHIPSPEED);
		ship.setVisibility(Constants.RADARSHIPVISIBILITY);
		return ship;
	}
}