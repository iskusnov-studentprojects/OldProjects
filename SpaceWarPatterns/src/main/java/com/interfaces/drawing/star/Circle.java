package com.interfaces.drawing.star;

import com.Constants;
import com.objects.Location;

import java.awt.*;

/**
 * Created by Sergey on 23.01.2016.
 */
public class Circle extends DrawingElement {
    protected int radius;
    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight, Location location) {
        double w = canvasWidth/ Constants.SPACEWIDTH,
                h = canvasHeight/Constants.SPACEHEIGHT;
        canvas.setColor(Color.yellow);
        canvas.fillOval((int)(location.X*w - (radius/2)*w), (int)(location.Y*h - (radius/2)*h), (int)(radius*w), (int)(radius*h));
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
