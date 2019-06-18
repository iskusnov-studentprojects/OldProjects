package com.interfaces.drawing.star;

import com.objects.Location;
import com.objects.Space;

import java.awt.*;

/**
 * Created by Sergey on 23.01.2016.
 */

public class Line extends DrawingElement {
    protected int arc;
    protected int len;
    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight, Location location) {
        double w = canvasWidth/ Space.SPACEWIDTH,
                h = canvasHeight/ Space.SPACEHEIGHT;
        canvas.setColor(Color.yellow);
        double X = Math.sin(arc)*len + location.X,
                Y = Math.cos(arc)*len + location.Y;
        canvas.drawLine((int)(location.X*w), (int)(location.Y*h), (int)(X*w),(int)(Y*h));
    }

    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
