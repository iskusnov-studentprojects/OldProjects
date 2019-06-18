package com.objects.ships;

/**
 * Created by Sergey on 27.01.2016.
 */
public class ShipAttributes {
    private int armor;
    private int speed;
    private int visibility;

    public ShipAttributes(int armor, int speed, int visibility) {
        this.armor = armor;
        this.speed = speed;
        this.visibility = visibility;
    }

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

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
