package com.objects.ships;

import com.enums.PlayerColor;
import com.enums.ShipTypes;
import com.objects.LocalizableObject;
import com.objects.Location;
import com.objects.Race;
import com.objects.Space;
import com.objects.visitor.CollectorOfInformation;

import java.util.*;

/**
 * 
 */
public class SpaceShip extends LocalizableObject {
	/**
	 * 
	 */
	protected ShipAttributes attributes;
	protected Location target;
	protected ShipTypes type;
	protected int maxArmor;

	public int getArmor() {
		return attributes.getArmor();
	}

	public void setArmor(int armor) {
		if(armor >= maxArmor)
			attributes.setArmor(maxArmor);
		else {
			attributes.setArmor(armor);
			if (getArmor() <= 0) {
				race.removeShip(this);
				Space.getInstance().set(null,getLocation().X,getLocation().Y);
			}
		}
	}

	public int getSpeed() {
		return attributes.getSpeed();
	}

	public void setSpeed(int speed) {
		attributes.setSpeed(speed);
	}

	public int getVisibility() {
		return attributes.getVisibility();
	}

	public void setVisibility(int visibility) {
		attributes.setVisibility(visibility);
	}

	public Location getTarget() {
            if(target == null)
                setRandomTarget();
            return target;
	}

	public void setTarget(int x, int y) {
		if(target == null)
			target = new Location(x, y);
		else
			target.setLocation(x, y);
	}

	public void setRandomTarget(){
		Random rand = new Random();
		target = new Location(rand.nextInt(Space.SPACEWIDTH - 1) + 1, rand.nextInt(Space.SPACEHEIGHT - 1) + 1);
	}

	@Override
	public void getDamage(int damage){
		setArmor(getArmor()-damage);
	}

	public ShipTypes getType() {
		return type;
	}

	public void setType(ShipTypes type) {
		this.type = type;
	}

	public void setAttributes(ShipAttributes attributes) {
		this.attributes = attributes;
		maxArmor = getArmor();
	}

	@Override
	public void accept(CollectorOfInformation visitor) {
		visitor.visitShip(this);
	}
}