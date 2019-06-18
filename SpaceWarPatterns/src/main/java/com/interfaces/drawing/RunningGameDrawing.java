package com.interfaces.drawing;

import com.Constants;
import com.interfaces.drawing.star.StarFactory;
import com.objects.Location;
import com.objects.Planet;
import com.objects.Space;

import java.awt.*;
import java.util.*;

/**
 * Created by Sergey on 22.01.2016.
 */
public class RunningGameDrawing implements IDrawing {
    public RunningGameDrawing() {
        stars = new HashMap<Location, Integer>();
        Random rand = new Random();
        for(int i = 0; i < 20; i++)
            stars.put(new Location(rand.nextInt(Constants.SPACEWIDTH) + 1, rand.nextInt(Constants.SPACEHEIGHT) + 1), rand.nextInt(6) + 5);
    }

    Map<Location, Integer> stars;

    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight) {
        canvas.clearRect(0, 0, canvasWidth, canvasHeight);
        for(Location i:stars.keySet())
            StarFactory.getInstance().getStar(stars.get(i)).draw(canvas,canvasWidth,canvasHeight,i);
        for(int i = 1; i < Space.getInstance().field.length - 1; i++)
            for(int j = 1; j < Space.getInstance().field[0].length - 1; j++)
                if(Space.getInstance().field[i][j] != null && Space.getInstance().field[i][j].getDrawing() != null)
                    Space.getInstance().field[i][j].getDrawing().draw(canvas, canvasWidth, canvasHeight);
        for(Planet i: Constants.PLANETS)
            i.getDrawing().draw(canvas, canvasWidth, canvasHeight);
    }
}
