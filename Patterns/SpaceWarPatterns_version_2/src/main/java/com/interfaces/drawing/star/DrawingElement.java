package com.interfaces.drawing.star;

import com.interfaces.drawing.IDrawing;
import com.objects.Location;

import java.awt.*;

/**
 * Created by Sergey on 23.01.2016.
 */
public abstract class DrawingElement{
    public abstract void draw(Graphics canvas, int canvasWidth, int canvasHeight, Location location);
}
