package com.interfaces.drawing;

import com.objects.LocalizableObject;
import com.objects.Space;

import java.awt.*;

/**
 * 
 */
public class RegenShipDrawing {

	LocalizableObject value;
	/**
	 * Default constructor
	 */
	public RegenShipDrawing(LocalizableObject value) {
		this.value = value;
	}

	/**
	 * @param canvas 
	 * @param canvasWidth 
	 * @param canvasHeight 
	 * @return
	 */
	public void drawArc(Graphics canvas, int canvasWidth, int canvasHeight) {
		double w = canvasWidth/ Space.SPACEWIDTH,
				h = canvasHeight/Space.SPACEHEIGHT;
		canvas.fillArc((int)(value.location.X*w), (int)(value.location.Y*h - 3*h), (int)(5*w), (int)(5*h), 240, 60);
	}

	/**
	 * @param canvas 
	 * @param canvasWidth 
	 * @param canvasHeight 
	 * @return
	 */
	public void drawRectangle(Graphics canvas, int canvasWidth, int canvasHeight) {
		double w = canvasWidth/ Space.SPACEWIDTH,
				h = canvasHeight/Space.SPACEHEIGHT;
		canvas.fillRect((int)(value.location.X*w - 1.5*w), (int)(value.location.Y*h - 0.75*h), (int)(3*w), (int)(1.5*h));
	}
}