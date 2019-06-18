package draw;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javafx.scene.image.PixelReader;
import java.awt.Color;

/**
 * Created by Sergey on 20.01.2017.
 */
public class ImageBufferI {
    Color[][] pixels;
    int averageRed;
    int averageGreen;
    int averageBlue;

    public ImageBufferI(Color[][] pixels){
        this.pixels = new Color[pixels.length][pixels[0].length];
        int averageRed=0,
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

    public ImageBufferI(BufferedImage image){
        this.pixels = new Color[image.getWidth()][image.getHeight()];
        int averageRed=0,
                averageGreen=0,
                averageBlue=0;
        for(int i = 0; i < pixels.length; i++)
            for(int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new Color(image.getRGB(i,j)>>16 & 0xff,
                        image.getRGB(i,j)>>8 & 0xff,
                        image.getRGB(i,j) & 0xff);
                averageRed += pixels[i][j].getRed();
                averageGreen += pixels[i][j].getGreen();
                averageBlue += pixels[i][j].getBlue();
            }
        this.averageRed = (int)((double)averageRed/(getWidth()*getHeight()));
        this.averageGreen = (int)((double)averageGreen/(getWidth()*getHeight()));
        this.averageBlue = (int)((double)averageBlue/(getWidth()*getHeight()));
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

    public int getAverageRed() {
        return averageRed;
    }

    public int getAverageGreen() {
        return averageGreen;
    }

    public int getAverageBlue() {
        return averageBlue;
    }

    public void setPixel(int x, int y, Color color){
        pixels[x][y] = color;
    }

    @Override
    public ImageBufferI clone() {
        return new ImageBufferI(pixels);
    }
}
