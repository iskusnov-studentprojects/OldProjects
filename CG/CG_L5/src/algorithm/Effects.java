package algorithm;

import draw.ImageBuffer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Created by Sergey on 17.01.2017.
 */
public class Effects {
    public static void noise(ImageBuffer buffer, int value){
        for(int i = 0; i < buffer.getWidth(); i++)
            for(int j = 0; j < buffer.getHeight(); j++)
                buffer.setPixel(i,j, noise(buffer.getPixel(i,j), value));
    }

    public static Color noise(Color color, int value){
        Random random = new Random();
        if(random.nextInt(value)==value/2)
            return new Color(0.7,0.7,0.7,1);
        else
            return color;
    }

    public static ImageBuffer medianFilter(ImageBuffer buffer, int matrixSize) throws Exception {
        if(matrixSize%2!=1)
            throw new Exception("Размер ядра должен быть нечетным");
        Color[][] result = new Color[buffer.getWidth()][buffer.getHeight()];
        List<Double> red = new ArrayList<>();
        List<Double> green = new ArrayList<>();
        List<Double> blue = new ArrayList<>();
        for(int i = 0; i < buffer.getWidth(); i++)
            for (int j = 0; j < buffer.getHeight(); j++){
                for(int k = -matrixSize/2; k <= matrixSize/2; k++)
                    for(int t = -matrixSize/2; t <= matrixSize/2; t++) {
                        Color color = buffer.getPixel(i + k < 0 ? 0 : (i + k >= buffer.getWidth() ? buffer.getWidth() - 1 : i + k),
                                j + t < 0 ? 0 : (j + t >= buffer.getHeight() ? buffer.getHeight() - 1 : j + t));
                        red.add(color.getRed());
                        green.add(color.getGreen());
                        blue.add(color.getBlue());
                    }
                Comparator<Double> comparator = (o1, o2) -> Double.compare(o1,o2);
                red.sort(comparator);
                green.sort(comparator);
                blue.sort(comparator);
                result[i][j] = new Color(red.get(matrixSize/2+1), green.get(matrixSize/2+1),blue.get(matrixSize/2+1),1);
                red.clear();
                green.clear();
                blue.clear();
            }
        return new ImageBuffer(result);
    }

    public static ImageBuffer uniformFilter(ImageBuffer buffer, int matrixSize) throws Exception {
        if(matrixSize%2!=1)
            throw new Exception("Размер ядра должен быть нечетным");
        Color[][] result = new Color[buffer.getWidth()][buffer.getHeight()];
        double red,
                green,
                blue;
        for(int i = 0; i < buffer.getWidth(); i++)
            for (int j = 0; j < buffer.getHeight(); j++){
                red = green = blue = 0;
                for(int k = -matrixSize/2; k <= matrixSize/2; k++)
                    for(int t = -matrixSize/2; t <= matrixSize/2; t++) {
                        Color color = buffer.getPixel(i + k < 0 ? 0 : (i + k >= buffer.getWidth() ? buffer.getWidth() - 1 : i + k),
                                j + t < 0 ? 0 : (j + t >= buffer.getHeight() ? buffer.getHeight() - 1 : j + t));
                        red += color.getRed();
                        green += color.getGreen();
                        blue += color.getBlue();
                    }
                result[i][j] = new Color(red/(matrixSize*matrixSize), green/(matrixSize*matrixSize), blue/(matrixSize*matrixSize),1);
            }
        return new ImageBuffer(result);
    }

    public static ImageBuffer stamping(ImageBuffer buffer, double[][] matrix) throws Exception {
        Color[][] result = new Color[buffer.getWidth()][buffer.getHeight()];
        double red,
                green,
                blue;
        int n = matrix.length,
                m = matrix[0].length;
        for(int i = 0; i < buffer.getWidth(); i++)
            for (int j = 0; j < buffer.getHeight(); j++){
                red = green = blue = 0;
                for(int k = -n/2; k <= n/2; k++)
                    for(int t = -m/2; t <= m/2; t++) {
                        Color color = buffer.getPixel(i + k < 0 ? 0 : (i + k >= buffer.getWidth() ? buffer.getWidth() - 1 : i + k),
                                j + t < 0 ? 0 : (j + t >= buffer.getHeight() ? buffer.getHeight() - 1 : j + t));
                        red += color.getRed()*matrix[k+(n/2)][t+(m/2)];
                        green += color.getGreen()*matrix[k+(n/2)][t+(m/2)];
                        blue += color.getBlue()*matrix[k+(n/2)][t+(m/2)];
                    }
                result[i][j] = new Color((red+0.5)>1?1:((red+0.5)<0?0:(red+0.5)),
                        (green+0.5)>1?1:((green+0.5)<0?0:(green+0.5)),
                        (blue+0.5)>1?1:((blue+0.5)<0?0:(blue+0.5)),1);
            }
        return new ImageBuffer(result);
    }

    public static ImageBuffer harshness(ImageBuffer buffer, double c, int matrixSize) throws Exception {
        if(matrixSize%2!=1)
            throw new Exception("Размер ядра должен быть нечетным");
        Color[][] result = new Color[buffer.getWidth()][buffer.getHeight()];
        double red,
                green,
                blue;
        for(int i = 0; i < buffer.getWidth(); i++)
            for (int j = 0; j < buffer.getHeight(); j++){
                red = green = blue = 0;
                for(int k = -matrixSize/2; k <= matrixSize/2; k++)
                    for(int t = -matrixSize/2; t <= matrixSize/2; t++) {
                        Color color = buffer.getPixel(i + k < 0 ? 0 : (i + k >= buffer.getWidth() ? buffer.getWidth() - 1 : i + k),
                                j + t < 0 ? 0 : (j + t >= buffer.getHeight() ? buffer.getHeight() - 1 : j + t));
                        if(k == 0 && t == 0){
                            red += color.getRed()*(c+1);
                            green += color.getGreen()*(c+1);
                            blue += color.getBlue()*(c+1);
                        } else {
                            red += color.getRed()*(-c/(matrixSize*matrixSize-1));
                            green += color.getGreen()*(-c/(matrixSize*matrixSize-1));
                            blue += color.getBlue()*(-c/(matrixSize*matrixSize-1));
                        }
                    }
                result[i][j] = new Color(red>1?1:(red<0?0:red),
                        green>1?1:(green<0?0:green),
                        blue>1?1:(blue<0?0:blue),1);
            }
        return new ImageBuffer(result);
    }
}
