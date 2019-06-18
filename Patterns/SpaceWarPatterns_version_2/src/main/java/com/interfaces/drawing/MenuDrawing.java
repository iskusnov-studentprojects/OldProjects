package com.interfaces.drawing;

import com.objects.Space;

import java.awt.*;

/**
 * Created by Sergey on 22.01.2016.
 */
public class MenuDrawing implements IDrawing {
    private Graphics canvas;

    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight) {
        this.canvas = canvas;
        double w = canvasWidth/ Space.SPACEWIDTH,
                h = canvasHeight/Space.SPACEHEIGHT;
        drawButton((int) (w * 20), (int) (h * 20), (int) (w * 60), (int) (h * 20));
        drawText("New game", (int) (w * 25), (int) (h * 33), (int) ((h > w ? w : h) * 11));
        drawButton((int) (w * 20), (int) (h * 60), (int) (w * 60), (int) (h * 20));
        drawText("Exit", (int) (w * 25), (int) (h * 75), (int) ((h > w ? w : h) * 20));
    }

    private void drawButton(int x, int y, int width, int height){
        canvas.setColor(Color.orange);
        canvas.fillRect(x, y, width, height);
        canvas.setColor(Color.black);
        canvas.drawRect(x, y, width, height);
    }

    private void drawText(String text, int x, int y, int fontSize){
        canvas.setFont(new Font("Calibri", Font.BOLD, fontSize));
        canvas.drawString(text, x + 5, y + 5);
    }
}
