package com.objects.ships.prototypes;

import com.Constants;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.interfaces.action.RadarShipAction;
import com.interfaces.action.WarShipAction;
import com.interfaces.cannon.WarShipCannon;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.drawing.WarShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.objects.LocalizableObject;
import com.objects.Location;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class WarShip extends SpaceShip {

	/**
	 * Default constructor
	 */
	public WarShip() {
		this.setAction(new WarShipAction(this));;
		this.setDrawing(new WarShipDrawing(this));
		this.setMovable(new Movable(this));
		this.setInvisibility(new Invisibility(this, false));
		this.setCannon(new WarShipCannon());
		this.setiVisible(new MakeVisible(false));
		this.setImmortal(false);
	}

	/**
	 * @return
	 */
	public LocalizableObject clone() {
		WarShip ship = new WarShip();
		ship.setArmor(armor);
		ship.setSpeed(speed);
		ship.setVisibility(speed);
		return ship;
	}

	@Override
	public ShipTypes getType(){return ShipTypes.WarShip;}

	public static IPrototype createPrototype(){
		WarShip ship = new WarShip();
		ship.setArmor(Constants.WARSHIPARMOR);
		ship.setSpeed(Constants.WARSHIPSPEED);
		ship.setVisibility(Constants.WARSHIPVISBILITY);
		return ship;
	}
}