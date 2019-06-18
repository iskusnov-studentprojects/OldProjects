package algorithm;

import draw.ImageBuffer;
import draw.ImageBufferI;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

/**
 * Created by Sergey on 16.01.2017.
 */
public class ColorCorrection {
    /*
     * @param value (-1.0;1.0)
     */
    public static void changeBrightness(float value, ImageBuffer image){
        for(int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setPixel(i,j, changeBrightness(value, image.getPixel(i,j)));
    }

    public static Color changeBrightness(float value, Color color){
        double red = color.getRed() + value,
                green = color.getGreen() + value,
                blue = color.getBlue() + value;
        return new Color(
                red < 0?0:(red>1?1:red),
                green < 0?0:(green>1?1:green),
                blue < 0?0:(blue>1?1:blue),
                1);
    }

    public static void contrastCorrection(float value, ImageBuffer image) {
        for(int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setPixel(i,j, contrastCorrection(value, image.getPixel(i,j), image));
    }

    public static Color contrastCorrection(float value, Color color, ImageBuffer image){
        double red = value*(color.getRed()-image.getAverageRed())+image.getAverageRed(),
                green = value*(color.getGreen()-image.getAverageGreen())+image.getAverageGreen(),
                blue = value*(color.getBlue()-image.getAverageBlue())+image.getAverageBlue();
        return new Color(
                red < 0?0:(red>1?1:red),
                green < 0?0:(green>1?1:green),
                blue < 0?0:(blue>1?1:blue),
                1);
    }

    public static void contrastCorrection(int value, ImageBufferI image) {
        for(int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setPixel(i,j, contrastCorrection(value, image.getPixel(i,j), image));
    }

    public static java.awt.Color contrastCorrection(int value, java.awt.Color color, ImageBufferI image){
        int red = value*(color.getRed()-image.getAverageRed())+image.getAverageRed(),
                green = value*(color.getGreen()-image.getAverageGreen())+image.getAverageGreen(),
                blue = value*(color.getBlue()-image.getAverageBlue())+image.getAverageBlue();
        return new java.awt.Color(
                red < 0?0:(red>=255?255:red),
                green < 0?0:(green>=255?255:green),
                blue < 0?0:(blue>=255?255:blue));
    }

    public static void convertToGray(ImageBuffer image){
        for(int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setPixel(i,j, convertToGray(image.getPixel(i,j)));
    }

    public static Color convertToGray(Color color){
        return color.grayscale();
    }

    public static void binary(ImageBuffer image){
        for(int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setPixel(i,j, binary(image.getPixel(i,j), image));
    }

    public static Color binary(Color color, ImageBuffer image){
        if((color.getRed()+color.getGreen()+color.getBlue())/3<
                (image.getAverageRed()+image.getAverageGreen()+image.getAverageBlue())/3)
            return Color.WHITE;
        else
            return Color.BLACK;
    }

    public static void negative(ImageBuffer image){
        for(int i = 0; i < image.getWidth(); i++)
            for (int j = 0; j < image.getHeight(); j++)
                image.setPixel(i,j, negative(image.getPixel(i,j)));
    }

    public static Color negative(Color color){
        return new Color(1-color.getRed(), 1-color.getGreen(), 1-color.getBlue(), 1);
    }
}
