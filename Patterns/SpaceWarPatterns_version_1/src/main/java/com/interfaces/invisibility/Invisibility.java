package com.interfaces.invisibility;

import com.objects.LocalizableObject;
import com.objects.Space;
import com.sql.SingletonSQLQuerys;

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
        int begi, endi, begj, endj,
                range = SingletonSQLQuerys.getInstance().getMakeVisibleRange();
        if(ship.location.X - range > 0)
            begi = ship.location.X - range;
        else
            begi = 0;
        if(ship.location.X + range < Space.SPACEWIDTH + 2)
            endi = ship.location.X + range;
        else
            endi = Space.SPACEHEIGHT + 2;
        if(ship.location.Y - range > 0)
            begj = ship.location.Y - range;
        else
            begj = 0;
        if(ship.location.Y + range < Space.SPACEHEIGHT + 2)
            endj = ship.location.Y + range;
        else
            endj = Space.SPACEHEIGHT + 2;

        for(int i = begi; i < endi; i++)
            for(int j = begj; j < endj; j++){
                if(Space.getInstance().get(i,j) != null && Space.getInstance().get(i,j).getPlayerColor() != null && Space.getInstance().get(i,j).getPlayerColor() != ship.getPlayerColor()){
                    if(Space.distance(ship.location.X, ship.location.Y, i, j) < range){
                        if(Space.getInstance().get(i,j).getiVisible().makeVisible())
                            return true;
                    }
                }
            }
        return false;
    }
}
