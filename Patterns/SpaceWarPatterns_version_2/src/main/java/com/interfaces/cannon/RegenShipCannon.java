package com.interfaces.cannon;

import com.objects.LocalizableObject;
import com.sql.SingletonSQLQuerys;

/**
 * 
 */
public class RegenShipCannon implements ICannon {


	/**
	 * Default constructor
	 */
	public RegenShipCannon() {
		this.damage = SingletonSQLQuerys.getInstance().getRegenShipDamage();
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