package com.interfaces.drawing.star;

import com.objects.Location;

import java.util.*;

/**
 * Created by Sergey on 23.01.2016.
 */
public class StarFactory {
    public StarFactory() {
        map = new HashMap<Integer, DrawingElement>();
    }

    private static StarFactory instance;
    protected Map<Integer, DrawingElement> map;

    public static StarFactory getInstance(){
        if(instance == null)
            instance = new StarFactory();
        return instance;
    }

    public DrawingElement getStar(int key){
        if(map.get(key) == null) {
            DrawingComposite drawingComposite = new DrawingComposite();
            Random rand = new Random();
            int radius = rand.nextInt(6) + 5,
                    lines = rand.nextInt(6) + 3;
            Circle circle = new Circle();
            circle.setRadius(radius);
            drawingComposite.addElement(circle);
            Line line;
            for (int i = 0; i < lines; i++) {
                line = new Line();
                line.setArc(rand.nextInt(360));
                line.setLen(rand.nextInt(10) + 10);
                drawingComposite.addElement(line);
            }
            map.put(radius, drawingComposite);
            return drawingComposite;
        }
        else return map.get(key);
    }

    public void clear(){
        map.clear();
    }
}
