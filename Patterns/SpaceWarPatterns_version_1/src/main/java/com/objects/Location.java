package com.objects;

import java.util.*;

/**
 * 
 */
public class Location {

	/**
	 * Default constructor
	 */
	public Location(int x, int y) {
		X = x;
		Y = y;
	}

	/**
	 * Координата объекта по оси Ox
	 */
	public int X;

	/**
	 * Координата объекта по оси Oy
	 */
	public int Y;


	/**
	 * @param x 
	 * @param y 
	 * @return
	 */
	public void setLocation(int x, int y) {
		X = x;
		Y = y;
	}

}