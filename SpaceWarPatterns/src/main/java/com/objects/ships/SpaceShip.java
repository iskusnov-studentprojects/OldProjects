package com.objects.ships;

import com.Constants;
import com.enums.PlayerColor;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.objects.LocalizableObject;
import com.objects.Location;
import com.objects.Space;

import java.util.*;

/**
 * 
 */
public class SpaceShip extends LocalizableObject implements IPrototype, Comparable {
	/**
	 * 
	 */
	protected int armor;
	protected Location target;

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Location getTarget() {
            if(target == null)
                setRandomTarget();
            return target;
	}

	public void setTarget(Location target) {
		this.target = target;
	}

	/**
	 * 
	 */
	protected int speed;

	public void setRandomTarget(){
		Random rand = new Random();
		target = new Location(rand.nextInt(Constants.SPACEWIDTH - 1) + 1, rand.nextInt(Constants.SPACEHEIGHT - 1) + 1);
	}

	@Override
	public void getDamage(int damage){
		armor-=damage;
		if(armor <= 0){
			Space.getInstance().field[location.X][location.Y] = null;
			playerColor = PlayerColor.None;
		}
	}

	public ShipTypes getType(){return null;}

    @Override
    public LocalizableObject clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public int compareTo(Object o) {
		return armor - ((SpaceShip)o).armor;
	}
}