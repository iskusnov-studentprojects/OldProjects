package com.interfaces.invisibility;

import com.Constants;
import com.objects.LocalizableObject;
import com.objects.Space;

/**
 * Created by Sergey on 29.12.2015.
 */
public class Invisibility implements IInvisibility {

    public Invisibility(LocalizableObject ship, boolean visible) {
        this.ship = ship;
        this.visible = visible;
    }

    boolean visible;

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    LocalizableObject ship;

    @Override
    public boolean isVisible() {
        if(!visible)
            return true;
        int begi, endi, begj, endj;
        if(ship.location.X - Constants.MAKEVISIBLERANGE > 0)
            begi = ship.location.X - Constants.MAKEVISIBLERANGE;
        else
            begi = 0;
        if(ship.location.X + Constants.MAKEVISIBLERANGE < Constants.SPACEWIDTH + 2)
            endi = ship.location.X + Constants.MAKEVISIBLERANGE;
        else
            endi = Constants.SPACEHEIGHT + 2;
        if(ship.location.Y - Constants.MAKEVISIBLERANGE > 0)
            begj = ship.location.Y - Constants.MAKEVISIBLERANGE;
        else
            begj = 0;
        if(ship.location.Y + Constants.MAKEVISIBLERANGE < Constants.SPACEHEIGHT + 2)
            endj = ship.location.Y + Constants.MAKEVISIBLERANGE;
        else
            endj = Constants.SPACEHEIGHT + 2;

        for(int i = begi; i < endi; i++)
            for(int j = begj; j < endj; j++){
                if(Space.getInstance().field[i][j] != null && Space.getInstance().field[i][j].getPlayerColor() != ship.getPlayerColor()){
                    if(Space.distance(ship.location.X, ship.location.Y, Space.getInstance().field[i][j].location.X, Space.getInstance().field[i][j].location.Y) < Constants.MAKEVISIBLERANGE){
                        if(Space.getInstance().field[i][j].getiVisible().makeVisible())
                            return true;
                    }
                }
            }
        return false;
    }
}
