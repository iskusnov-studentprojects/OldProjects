package com.objects.ships.prototypes;

import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.objects.pool.PrototypesPool;
import com.objects.ships.SpaceShip;

import java.util.*;

/**
 * 
 */
public class ShipPrototypes {
	/**
	 * @param type 
	 * @return
	 */
	public static SpaceShip getShipPrototype(ShipTypes type) {
		IPrototype[] mem = new IPrototype[5];
		for(int i = 0; i < 5; i++)
			mem[i] = PrototypesPool.getInstance().acquireObject();
		for(int i = 4; i >= 0; i--)
			PrototypesPool.getInstance().releaseObject(mem[i]);
		switch (type){
			case RadarShip:
				return (SpaceShip)mem[4];
			case RegenShip:
				return (SpaceShip)mem[3];
			case StealthShip:
				return (SpaceShip)mem[2];
			case TransportShip:
				return (SpaceShip)mem[1];
			case WarShip:
				return (SpaceShip)mem[0];
		}
		return null;
	}
}