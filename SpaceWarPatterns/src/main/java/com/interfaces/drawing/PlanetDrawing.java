/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfaces.drawing;

import com.Constants;
import com.objects.LocalizableObject;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Sergey
 */
public class PlanetDrawing implements IDrawing {
    LocalizableObject value;
	/**
	 * Default constructor
	 */
	public PlanetDrawing(LocalizableObject value) {
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
        double w = canvasWidth/Constants.SPACEWIDTH,
               h = canvasHeight/Constants.SPACEHEIGHT;
        canvas.fillOval((int)(value.location.X*w - 2*w), (int)(value.location.Y*h - 2*h), (int)(4*w), (int)(4*h));
	}
}
