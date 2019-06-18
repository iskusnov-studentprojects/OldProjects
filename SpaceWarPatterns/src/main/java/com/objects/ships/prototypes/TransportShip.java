package com.objects.ships.prototypes;

import com.Constants;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.interfaces.action.RadarShipAction;
import com.interfaces.action.TransportShipAction;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.drawing.TransportShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.objects.LocalizableObject;
import com.objects.Location;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class TransportShip extends SpaceShip {

	/**
	 * Default constructor
	 */
	public TransportShip() {
		this.setAction(new TransportShipAction(this));
		this.setDrawing(new TransportShipDrawing(this));
		this.setMovable(new Movable(this));
		this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
		this.setImmortal(false);
	}

	/**
	 * @return
	 */
	public LocalizableObject clone() {
		TransportShip ship = new TransportShip();
		ship.setArmor(armor);
		ship.setSpeed(speed);
		ship.setVisibility(visibility);
		return ship;
	}

	@Override
	public ShipTypes getType(){return ShipTypes.TransportShip;}

	public static IPrototype createPrototype(){
		TransportShip ship = new TransportShip();
		ship.setArmor(Constants.TRANSPORTSHIPARMOR);
		ship.setSpeed(Constants.TRANSPORTSHIPSPEED);
		ship.setVisibility(Constants.TRANSPORTSHIPVISIBILITY);
		return ship;
	}
}