package com.interfaces.cannon;

import com.objects.LocalizableObject;
import com.sql.SingletonSQLQuerys;

/**
 * 
 */
public class WarShipCannon implements ICannon {

	/**
	 * Default constructor
	 */
	public WarShipCannon() {
		damage = SingletonSQLQuerys.getInstance().getWarShipDamage();
	}

	/**
	 * 
	 */
	protected int damage;

	/**
	 * @param ship 
	 * @return
	 */
	public void fire(LocalizableObject ship) {
		ship.getDamage(damage);
	}

}