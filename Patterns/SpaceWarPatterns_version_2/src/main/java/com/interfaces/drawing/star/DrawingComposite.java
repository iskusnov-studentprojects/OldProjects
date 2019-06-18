package com.interfaces.drawing.star;

import com.objects.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Sergey on 23.01.2016.
 */
public class DrawingComposite extends DrawingElement {
    ArrayList<DrawingElement> elements;

    public DrawingComposite() {
        elements = new ArrayList<DrawingElement>();
    }

    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight, Location location) {
        for(DrawingElement element: elements)
            element.draw(canvas, canvasWidth, canvasHeight, location);
    }

    public void addElement(DrawingElement element){
        elements.add(element);
    }

    public void removeElement(DrawingElement element){
        elements.remove(element);
    }

    public DrawingElement getChild(int index){
        return elements.get(index);
    }
}
