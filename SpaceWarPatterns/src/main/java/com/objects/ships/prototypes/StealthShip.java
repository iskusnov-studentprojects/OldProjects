package com.objects.ships.prototypes;

import com.Constants;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.interfaces.action.RadarShipAction;
import com.interfaces.action.StealthShipAction;
import com.interfaces.cannon.StealthShipCannon;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.drawing.StealthShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.objects.LocalizableObject;
import com.objects.Location;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class StealthShip extends SpaceShip{

	/**
	 * Default constructor
	 */
	public StealthShip() {
		this.setAction(new StealthShipAction(this));
		this.setDrawing(new StealthShipDrawing(this));
		this.setMovable(new Movable(this));
		this.setInvisibility(new Invisibility(this, true));
		this.setiVisible(new MakeVisible(true));
		this.setCannon(new StealthShipCannon());
		this.setImmortal(false);
	}

	/**
	 * @return
	 */
	public LocalizableObject clone() {
		StealthShip ship = new StealthShip();
		ship.setArmor(armor);
		ship.setSpeed(speed);
		ship.setVisibility(visibility);
		return ship;
	}

	@Override
	public ShipTypes getType(){return ShipTypes.StealthShip;}

	public static IPrototype createPrototype(){
		StealthShip ship = new StealthShip();
		ship.setArmor(Constants.STEALTHSHIPARMOR);
		ship.setSpeed(Constants.STEALTHSHIPSPEED);
		ship.setVisibility(Constants.STEALTHSHIPVISIBILITY);
		return ship;
	}
}