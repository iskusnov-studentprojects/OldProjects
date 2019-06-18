package com.objects.ships.prototypes;

import com.Constants;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.interfaces.action.RadarShipAction;
import com.interfaces.action.RegenShipAction;
import com.interfaces.cannon.RegenShipCannon;
import com.interfaces.cannon.WarShipCannon;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.drawing.RegenShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.objects.LocalizableObject;
import com.objects.Location;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class RegenShip extends SpaceShip implements IPrototype {

	/**
	 * Default constructor
	 */
	public RegenShip(){
		this.setAction(new RegenShipAction(this));
		this.setDrawing(new RegenShipDrawing(this));
		this.setMovable(new Movable(this));
		this.setInvisibility(new Invisibility(this, false));
		this.setiVisible(new MakeVisible(true));
		this.setCannon(new RegenShipCannon());
		this.setImmortal(false);
	}

	/**
	 * @return
	 */
	public LocalizableObject clone() {
		RegenShip ship = new RegenShip();
		ship.setArmor(armor);
		ship.setSpeed(speed);
		ship.setVisibility(visibility);
		return ship;
	}

	@Override
	public ShipTypes getType(){return ShipTypes.RegenShip;}

	public static IPrototype createPrototype(){
		RegenShip ship = new RegenShip();
		ship.setArmor(Constants.REGENSHIPARMOR);
		ship.setSpeed(Constants.REGENSHIPSPEED);
		ship.setVisibility(Constants.REGENSHIPVISIBILITY);
		return ship;
	}

}