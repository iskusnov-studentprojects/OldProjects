package com.interfaces;

import com.objects.ships.SpaceShip;

import java.util.*;

/**
 * 
 */
public interface ShipCreator {


	/**
	 * @return
	 */
	public SpaceShip createWarShip();

	/**
	 * @return
	 */
	public SpaceShip createTransportShip();

	/**
	 * @return
	 */
	public SpaceShip createRadarShip();

	/**
	 * @return
	 */
	public SpaceShip createSpecialShip();

}