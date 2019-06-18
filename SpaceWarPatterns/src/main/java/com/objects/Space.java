package com.objects;

import com.Constants;
import com.enums.PlayerColor;
import com.objects.LocalizableObject;

import java.util.*;

/**
 * 
 */
public class Space {

	/**
	 * 
	 */
	public LocalizableObject[][] field;

	/**
	 * 
	 */
	private static Space instance;

	/**
	 * @return
	 */
	public void clear() {
        if (field == null) {
            field = new LocalizableObject[Constants.SPACEWIDTH + 2][Constants.SPACEHEIGHT + 2];
            for (int i = 0; i < Constants.SPACEWIDTH + 2; i++) {
                field[i][0] = new Planet(i, 0, PlayerColor.None);
                field[i][Constants.SPACEWIDTH + 1] = new Planet(i, Constants.SPACEWIDTH + 1, PlayerColor.None);
            }
            for (int j = 0; j < Constants.SPACEHEIGHT + 2; j++) {
                field[0][j] = new Planet(0, j, PlayerColor.None);
                field[Constants.SPACEHEIGHT + 1][j] = new Planet(Constants.SPACEHEIGHT + 1, j, PlayerColor.None);
            }
        }
        for (int i = 0; i < field.length; i++)
            for (int j = 0; j < field[i].length; j++)
                field[i][j] = null;
    }

	/**
	 * 
	 */
	protected void Space() {
	field = new LocalizableObject[Constants.SPACEWIDTH+2][Constants.SPACEHEIGHT+2];
        for(int i = 0; i < Constants.SPACEWIDTH + 2; i++){
            field[i][0] = new Planet(i,0, PlayerColor.None);
            field[i][Constants.SPACEWIDTH + 1] = new Planet(i, Constants.SPACEWIDTH + 1, PlayerColor.None);
        }
        for(int j = 0; j < Constants.SPACEHEIGHT + 2; j++){
            field[0][j] = new Planet(0,j, PlayerColor.None);
            field[Constants.SPACEHEIGHT + 1][j] = new Planet(Constants.SPACEHEIGHT + 1, j, PlayerColor.None);
        }
        }

	/**
	 * @return
	 */
	public static Space getInstance() {
		if(instance == null)
			instance = new Space();
		return instance;
	}

	public static double distance(int sx, int sy, int tx, int ty) {
		return Math.sqrt((sx - tx) * (sx - tx) + (sy - ty) * (sy - ty));
	}

}