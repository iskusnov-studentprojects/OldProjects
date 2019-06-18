package draw;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

/**
 * Created by Sergey on 16.01.2017.
 */
public class ImageBuffer {
    Color[][] pixels;
    float averageRed;
    float averageGreen;
    float averageBlue;

    public ImageBuffer(Color[][] pixels){
        this.pixels = new Color[pixels.length][pixels[0].length];
        float averageRed=0,
                averageGreen=0,
                averageBlue=0;
        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[i].length; j++) {
                this.pixels[i][j] = pixels[i][j];
                averageRed += pixels[i][j].getRed();
                averageGreen += pixels[i][j].getGreen();
                averageBlue += pixels[i][j].getBlue();
            }
        this.averageRed = averageRed/(getWidth()*getHeight());
        this.averageGreen = averageGreen/(getWidth()*getHeight());
        this.averageBlue = averageBlue/(getWidth()*getHeight());
    }

    public ImageBuffer(Image image){
        pixels = new Color[(int)image.getWidth()][(int)image.getHeight()];
        PixelReader reader = image.getPixelReader();
        float averageRed=0,
                averageGreen=0,
                averageBlue=0;
        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = reader.getColor(i, j);
                averageRed += pixels[i][j].getRed();
                averageGreen += pixels[i][j].getGreen();
                averageBlue += pixels[i][j].getBlue();
            }
        this.averageRed = averageRed/(getWidth()*getHeight());
        this.averageGreen = averageGreen/(getWidth()*getHeight());
        this.averageBlue = averageBlue/(getWidth()*getHeight());
    }

    public Color getPixel(int x, int y){
        return pixels[x][y];
    }

    public int getWidth(){
        return pixels.length;
    }

    public int getHeight(){
        return pixels[0].length;
    }

    public float getAverageRed() {
        return averageRed;
    }

    public float getAverageGreen() {
        return averageGreen;
    }

    public float getAverageBlue() {
        return averageBlue;
    }

    public void setPixel(int x, int y, Color color){
        pixels[x][y] = color;
    }

    @Override
    public ImageBuffer clone() {
        return new ImageBuffer(pixels);
    }
}
