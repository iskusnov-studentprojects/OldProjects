package com.interfaces.move;

import com.Constants;
import com.objects.LocalizableObject;
import com.objects.Space;

import java.util.*;

/**
 * 
 */
public class Movable implements IMovable {

	/**
	 * Default constructor
	 */
	public Movable(LocalizableObject obj) {
		this.obj = obj;
	}

	/**
	 * 
	 */
	public LocalizableObject obj;

	/**
	 * @param x 
	 * @param y 
	 * @return
	 */
	public void move(int x, int y) {
		double mindist = Space.distance(obj.location.X, obj.location.Y, x, y);
		int newx = obj.location.X,
				newy = obj.location.Y;
		if(obj.location.X < 1)
			obj.location.X = 1;
		if(obj.location.X >= Constants.SPACEWIDTH - 2)
			obj.location.X = Constants.SPACEWIDTH - 2;
		if(obj.location.Y < 1)
			obj.location.Y = 1;
		if(obj.location.Y >= Constants.SPACEHEIGHT - 2)
			obj.location.Y = Constants.SPACEHEIGHT - 2;


		//Up-Left
		if (Space.getInstance().field[obj.location.X - 1][obj.location.Y - 1] == null)
			if (mindist > Space.distance(obj.location.X - 1, obj.location.Y - 1, x, y)) {
				mindist = Space.distance(obj.location.X - 1, obj.location.Y - 1, x, y);
				newx = obj.location.X - 1;
				newy = obj.location.Y - 1;
			}

		//Up
		if (Space.getInstance().field[obj.location.X][obj.location.Y - 1] == null)
			if (mindist > Space.distance(obj.location.X, obj.location.Y - 1, x, y)) {
				mindist = Space.distance(obj.location.X, obj.location.Y - 1, x, y);
				newx = obj.location.X;
				newy = obj.location.Y - 1;
			}

		//Up-Right
		if (Space.getInstance().field[obj.location.X + 1][obj.location.Y - 1] == null)
			if (mindist > Space.distance(obj.location.X + 1, obj.location.Y - 1, x, y)) {
				mindist = Space.distance(obj.location.X + 1, obj.location.Y - 1, x, y);
				newx = obj.location.X + 1;
				newy = obj.location.Y - 1;
			}

		//Left
		if (Space.getInstance().field[obj.location.X - 1][obj.location.Y] == null)
			if (mindist > Space.distance(obj.location.X - 1, obj.location.Y, x, y)) {
				mindist = Space.distance(obj.location.X - 1, obj.location.Y, x, y);
				newx = obj.location.X - 1;
				newy = obj.location.Y;
			}

		//Right
		if (Space.getInstance().field[obj.location.X + 1][obj.location.Y] == null)
			if (mindist > Space.distance(obj.location.X + 1, obj.location.Y, x, y)) {
				mindist = Space.distance(obj.location.X + 1, obj.location.Y, x, y);
				newx = obj.location.X + 1;
				newy = obj.location.Y;
			}

		//Down-Left
		if (Space.getInstance().field[obj.location.X - 1][obj.location.Y + 1] == null)
			if (mindist > Space.distance(obj.location.X - 1, obj.location.Y + 1, x, y)) {
				mindist = Space.distance(obj.location.X - 1, obj.location.Y + 1, x, y);
				newx = obj.location.X - 1;
				newy = obj.location.Y + 1;
			}

		//Down
		if (Space.getInstance().field[obj.location.X][obj.location.Y + 1] == null)
			if (mindist > Space.distance(obj.location.X, obj.location.Y + 1, x, y)) {
				mindist = Space.distance(obj.location.X, obj.location.Y + 1, x, y);
				newx = obj.location.X;
				newy = obj.location.Y + 1;
			}

		//Donw-Right
		if (Space.getInstance().field[obj.location.X + 1][obj.location.Y + 1] == null)
			if (mindist > Space.distance(obj.location.X + 1, obj.location.Y + 1, x, y)) {
				newx = obj.location.X + 1;
				newy = obj.location.Y + 1;
			}

		//Change location
		if (Space.getInstance().field[obj.location.X][obj.location.Y] == obj)
			Space.getInstance().field[obj.location.X][obj.location.Y] = null;
		obj.location.setLocation(newx, newy);
		Space.getInstance().field[newx][newy] = obj;
	}

}