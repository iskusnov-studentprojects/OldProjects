package com.interfaces.drawing;

import com.Constants;
import com.interfaces.drawing.IDrawing;
import com.objects.LocalizableObject;

import java.awt.*;
import java.util.*;

/**
 * 
 */
public class RegenShipDrawing {

	LocalizableObject value;

	public RegenShipDrawing() {
		value = null;
	}

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
		double w = canvasWidth/ Constants.SPACEWIDTH,
				h = canvasHeight/Constants.SPACEHEIGHT;
		canvas.fillArc((int)(value.location.X*w), (int)(value.location.Y*h - 3*h), (int)(5*w), (int)(5*h), 240, 60);
	}

	/**
	 * @param canvas 
	 * @param canvasWidth 
	 * @param canvasHeight 
	 * @return
	 */
	public void drawRectangle(Graphics canvas, int canvasWidth, int canvasHeight) {
		double w = canvasWidth/ Constants.SPACEWIDTH,
				h = canvasHeight/Constants.SPACEHEIGHT;
		canvas.fillRect((int)(value.location.X*w - 1.5*w), (int)(value.location.Y*h - 0.75*h), (int)(3*w), (int)(1.5*h));
	}
}