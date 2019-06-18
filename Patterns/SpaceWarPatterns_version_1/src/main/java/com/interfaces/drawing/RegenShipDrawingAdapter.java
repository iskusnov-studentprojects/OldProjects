package com.interfaces.drawing;

import com.objects.LocalizableObject;

import java.awt.*;
import java.util.*;

/**
 * 
 */
public class RegenShipDrawingAdapter extends RegenShipDrawing implements IDrawing {

	public RegenShipDrawingAdapter(LocalizableObject value) {
		super(value);
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
		drawRectangle(canvas, canvasWidth, canvasHeight);
		drawArc(canvas, canvasWidth, canvasHeight);
	}

}