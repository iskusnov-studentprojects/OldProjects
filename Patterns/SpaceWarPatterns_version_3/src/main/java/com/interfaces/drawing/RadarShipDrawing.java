package com.interfaces.drawing;

import com.objects.LocalizableObject;
import com.objects.Space;

import java.awt.*;

/**
 * 
 */
public class RadarShipDrawing implements IDrawing {

	LocalizableObject value;
	/**
	 * Default constructor
	 */
	public RadarShipDrawing(LocalizableObject value) {
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
		double w = canvasWidth/ Space.SPACEWIDTH,
				h = canvasHeight/ Space.SPACEHEIGHT;
		canvas.fillRect((int)(value.location.X*w - 1.5*w), (int)(value.location.Y*h - 0.75*h), (int)(3*w), (int)(1.5*h));
		canvas.fillOval((int)(value.location.X*w - 0.75*w), (int)(value.location.Y*h - 1.5*h), (int)(1.5*w), (int)(3*h));
	}

}