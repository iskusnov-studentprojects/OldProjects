package com.interfaces.drawing;

import com.objects.Space;

import java.awt.*;

/**
 * Created by Sergey on 31.01.2016.
 */
public class PauseDrawing implements IDrawing{
    private Graphics canvas;
    private RunningGameDrawing drawing;

    public PauseDrawing() {
        drawing = new RunningGameDrawing();
    }

    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight) {
        this.canvas = canvas;
        drawing.draw(canvas, canvasWidth, canvasHeight);
        double w = canvasWidth/ Space.SPACEWIDTH,
                h = canvasHeight/Space.SPACEHEIGHT;
        drawButton((int) (w * 20), (int) (h * 10), (int) (w * 60), (int) (h * 20));
        drawText("Continue", (int) (w * 25), (int) (h * 23), (int) ((h > w ? w : h) * 11));
        drawButton((int) (w * 20), (int) (h * 40), (int) (w * 60), (int) (h * 20));
        drawText("New game", (int) (w * 25), (int) (h * 53), (int) ((h > w ? w : h) * 11));
        drawButton((int) (w * 20), (int) (h * 70), (int) (w * 60), (int) (h * 20));
        drawText("Exit", (int) (w * 25), (int) (h * 85), (int) ((h > w ? w : h) * 20));
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
