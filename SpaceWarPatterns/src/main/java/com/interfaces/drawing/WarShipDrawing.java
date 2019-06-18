package com.interfaces.drawing;

import com.Constants;
import com.objects.LocalizableObject;

import java.awt.*;
import java.util.*;

/**
 * 
 */
public class WarShipDrawing implements IDrawing {

	LocalizableObject value;
	/**
	 * Default constructor
	 */
	public WarShipDrawing(LocalizableObject value) {
		this.value = value;
	}

	/**
	 * @param canvas 
	 * @param canvasWidth 
	 * @param canvasHeight 
	 * @return
	 */
	public void draw(Graphics canvas, int canvasWidth, int canvasHeight) {
		switch(value.getPlayerColor()){
			case Red: canvas.setColor(Color.RED);
				break;
			case Green: canvas.setColor(Color.GREEN);
				break;
			case Blue: canvas.setColor(Color.BLUE);
				break;
			case None: canvas.setColor(Color.GRAY);
		}
		double w = canvasWidth/ Constants.SPACEWIDTH,
				h = canvasHeight/Constants.SPACEHEIGHT;
		canvas.fillArc((int)(value.location.X*w), (int)(value.location.Y*h - 3*h), (int)(5*w), (int)(5*h), 240, 60);
	}

}