package algorithm;

import draw.ImageBuffer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Created by Sergey on 18.01.2017.
 */
public class Zoom {
    public static ImageBuffer nearestNeighbor(ImageBuffer buffer, double k){
        if(Math.abs(k-100)<0.5)
            return buffer;
        if(k > 100)
            return nearestNeighborPlus(buffer, (int)k);
        else
            return zoomMinus(buffer, (int)k);
    }

    private static ImageBuffer nearestNeighborPlus(ImageBuffer buffer, int k){
        int n = (int)(buffer.getWidth()*(k/100.)),
                m = (int)(buffer.getHeight()*(k/100.)),
                c = k-100;
        Color[][] result = new Color[n][m];
        int i,j,t,p,xb,yb;
        for(i = 0, t = 0, xb = 90; i < buffer.getWidth();) {
            if (xb >= 100) {
                for (j = 0, p = 0, yb = 90; j < buffer.getHeight(); ) {
                    if (yb >= 100) {
                        result[t][p] = new Color(buffer.getPixel(i, j).getRed(),
                                buffer.getPixel(i, j).getGreen(),
                                buffer.getPixel(i, j).getBlue(),
                                1);
                        p++;
                        yb -= 100;
                        continue;
                    }
                    result[t][p] = new Color(buffer.getPixel(i, j).getRed(),
                            buffer.getPixel(i, j).getGreen(),
                            buffer.getPixel(i, j).getBlue(),
                            1);
                    j++;p++;
                    yb += c;
                }
                for (; p < m; p++)
                    result[t][p] = Color.BLACK;
                t++;
                xb -= 100;
                continue;
            }
            for (j = 0, p = 0, yb = 90; j < buffer.getHeight(); ) {
                if (yb >= 100) {
                    result[t][p] = new Color(buffer.getPixel(i, j).getRed(),
                            buffer.getPixel(i, j).getGreen(),
                            buffer.getPixel(i, j).getBlue(),
                            1);
                    p++;
                    yb -= 100;
                    continue;
                }
                result[t][p] = new Color(buffer.getPixel(i, j).getRed(), buffer.getPixel(i, j).getGreen(), buffer.getPixel(i, j).getBlue(), 1);
                j++;
                p++;
                yb += c;
            }
            for (; p < m; p++)
                result[t][p] = Color.BLACK;
            i++;
            t++;
            xb += c;
        }
        for(;t<n;t++)
            for(p=0;p<m;p++)
                result[t][p] = Color.BLACK;
        return new ImageBuffer(result);
    }

    private static ImageBuffer zoomMinus(ImageBuffer buffer, int k){
        int n = (int)(buffer.getWidth()*(k/100.)),
                m = (int)(buffer.getHeight()*(k/100.)),
                c = 100-k;
        Color[][] result = new Color[n][m];
        for(int i = 0, t = 0, xb = 90; i < n;) {
            if(xb>=100) {
                t++;
                xb-=100;
                xb+=c;
                continue;
            }
            for (int j = 0, p = 0, yb = 90; j < m;) {
                if(yb>=100) {
                    p++;
                    yb-=100;
                    yb+=c;
                    continue;
                }
                result[i][j] = new Color(buffer.getPixel(t,p).getRed(), buffer.getPixel(t,p).getGreen(), buffer.getPixel(t,p).getBlue(), 1);
                j++;p++;
                yb+=c;
            }
            i++;t++;
            xb+=c;
        }
        return new ImageBuffer(result);
    }

    public static ImageBuffer bicubicInterpolation(ImageBuffer buffer, double k){
        if(Math.abs(k-100)<0.5)
            return buffer;
        if(k>100)
            return bicubicInterpolationPlus(buffer,(int)k);
        else
            return zoomMinus(buffer,(int)k);
    }

    private static ImageBuffer bicubicInterpolationPlus(ImageBuffer buffer, int k){
        int n = (int)(buffer.getWidth()*(k/100.)),
                m = (int)(buffer.getHeight()*(k/100.)),
                c = k-100;
        Color[][] result = new Color[n][m];
        int i,j,t,p,xb,yb;
        for(i = 0, t = 0, xb = 90; i < buffer.getWidth();) {
            if (xb >= 100) {
                for (j = 0, p = 0, yb = 90; j < buffer.getHeight(); ) {
                    int x2l = i-1>=0?i-1:0,
                            x2r = i+2<buffer.getWidth()?i+2:buffer.getWidth()-1,
                            x1l = i,
                            x1r = i+1<buffer.getWidth()?i+1:buffer.getWidth()-1,
                            y2t = j-1>=0?j-1:0,
                            y2b = j+2<buffer.getHeight()?j+2:buffer.getHeight()-1,
                            y1t = j,
                            y1b = j+1<buffer.getHeight()?j+1:buffer.getHeight()-1;
                    if (yb >= 100) {
                        result[t][p] = new Color(buffer.getPixel(x2l, y2t).getRed() / 64 + buffer.getPixel(x2l, y2b).getRed() / 64 + buffer.getPixel(x2r, y2t).getRed() / 64 + buffer.getPixel(x2l, y2b).getRed() / 64 +
                                buffer.getPixel(x2l, y1b).getRed() / 64 * 3 + buffer.getPixel(x2l, y1t).getRed() / 64 * 3 +
                                buffer.getPixel(x1l, y2t).getRed() / 64 * 3 + buffer.getPixel(x1r, y2t).getRed() / 64 * 3 +
                                buffer.getPixel(x2r, y1t).getRed() / 64 * 3 + buffer.getPixel(x2r, y1b).getRed() / 64 * 3 +
                                buffer.getPixel(x1l, y2b).getRed() / 64 * 3 + buffer.getPixel(x1r, y2b).getRed() / 64 * 3 +
                                buffer.getPixel(x1l, y1t).getRed() / 64 * 9 + buffer.getPixel(x1r, y1t).getRed() / 64 * 9 + buffer.getPixel(x1r, y1b).getRed() / 64 * 9 + buffer.getPixel(x1l, y1b).getRed() / 64 * 9,
                                buffer.getPixel(x2l, y2t).getGreen() / 64 + buffer.getPixel(x2l, y2b).getGreen() / 64 + buffer.getPixel(x2r, y2t).getGreen() / 64 + buffer.getPixel(x2l, y2b).getGreen() / 64 +
                                        buffer.getPixel(x2l, y1b).getGreen() / 64 * 3 + buffer.getPixel(x2l, y1t).getGreen() / 64 * 3 +
                                        buffer.getPixel(x1l, y2t).getGreen() / 64 * 3 + buffer.getPixel(x1r, y2t).getGreen() / 64 * 3 +
                                        buffer.getPixel(x2r, y1t).getGreen() / 64 * 3 + buffer.getPixel(x2r, y1b).getGreen() / 64 * 3 +
                                        buffer.getPixel(x1l, y2b).getGreen() / 64 * 3 + buffer.getPixel(x1r, y2b).getGreen() / 64 * 3 +
                                        buffer.getPixel(x1l, y1t).getGreen() / 64 * 9 + buffer.getPixel(x1r, y1t).getGreen() / 64 * 9 + buffer.getPixel(x1r, y1b).getGreen() / 64 * 9 + buffer.getPixel(x1l, y1b).getGreen() / 64 * 9,
                                buffer.getPixel(x2l, y2t).getBlue() / 64 + buffer.getPixel(x2l, y2b).getBlue() / 64 + buffer.getPixel(x2r, y2t).getBlue() / 64 + buffer.getPixel(x2l, y2b).getBlue() / 64 +
                                        buffer.getPixel(x2l, y1b).getBlue() / 64 * 3 + buffer.getPixel(x2l, y1t).getBlue() / 64 * 3 +
                                        buffer.getPixel(x1l, y2t).getBlue() / 64 * 3 + buffer.getPixel(x1r, y2t).getBlue() / 64 * 3 +
                                        buffer.getPixel(x2r, y1t).getBlue() / 64 * 3 + buffer.getPixel(x2r, y1b).getBlue() / 64 * 3 +
                                        buffer.getPixel(x1l, y2b).getBlue() / 64 * 3 + buffer.getPixel(x1r, y2b).getBlue() / 64 * 3 +
                                        buffer.getPixel(x1l, y1t).getBlue() / 64 * 9 + buffer.getPixel(x1r, y1t).getBlue() / 64 * 9 + buffer.getPixel(x1r, y1b).getBlue() / 64 * 9 + buffer.getPixel(x1l, y1b).getBlue() / 64 * 9,
                                1);
                        p++;
                        yb -= 100;
                        continue;
                    }
                    result[t][p] = new Color(buffer.getPixel(i, j).getRed(),
                            buffer.getPixel(i, j).getGreen(),
                            buffer.getPixel(i, j).getBlue(),
                            1);
                    j++;p++;
                    yb += c;
                }
                for (; p < m; p++)
                    result[t][p] = Color.WHITE;
                t++;
                xb -= 100;
                continue;
            }
            for (j = 0, p = 0, yb = 90; j < buffer.getHeight(); ) {
                int x2l = i-1>=0?i-1:0,
                        x2r = i+2<buffer.getWidth()?i+2:buffer.getWidth()-1,
                        x1l = i,
                        x1r = i+1<buffer.getWidth()?i+1:buffer.getWidth()-1,
                        y2t = j-1>=0?j-1:0,
                        y2b = j+2<buffer.getHeight()?j+2:buffer.getHeight()-1,
                        y1t = j,
                        y1b = j+1<buffer.getHeight()?j+1:buffer.getHeight()-1;
                if (yb >= 100) {
                    result[t][p] = new Color(buffer.getPixel(x2l, y2t).getRed() / 64 + buffer.getPixel(x2l, y2b).getRed() / 64 + buffer.getPixel(x2r, y2t).getRed() / 64 + buffer.getPixel(x2l, y2b).getRed() / 64 +
                            buffer.getPixel(x2l, y1b).getRed() / 64 * 3 + buffer.getPixel(x2l, y1t).getRed() / 64 * 3 +
                            buffer.getPixel(x1l, y2t).getRed() / 64 * 3 + buffer.getPixel(x1r, y2t).getRed() / 64 * 3 +
                            buffer.getPixel(x2r, y1t).getRed() / 64 * 3 + buffer.getPixel(x2r, y1b).getRed() / 64 * 3 +
                            buffer.getPixel(x1l, y2b).getRed() / 64 * 3 + buffer.getPixel(x1r, y2b).getRed() / 64 * 3 +
                            buffer.getPixel(x1l, y1t).getRed() / 64 * 9 + buffer.getPixel(x1r, y1t).getRed() / 64 * 9 + buffer.getPixel(x1r, y1b).getRed() / 64 * 9 + buffer.getPixel(x1l, y1b).getRed() / 64 * 9,
                            buffer.getPixel(x2l, y2t).getGreen() / 64 + buffer.getPixel(x2l, y2b).getGreen() / 64 + buffer.getPixel(x2r, y2t).getGreen() / 64 + buffer.getPixel(x2l, y2b).getGreen() / 64 +
                                    buffer.getPixel(x2l, y1b).getGreen() / 64 * 3 + buffer.getPixel(x2l, y1t).getGreen() / 64 * 3 +
                                    buffer.getPixel(x1l, y2t).getGreen() / 64 * 3 + buffer.getPixel(x1r, y2t).getGreen() / 64 * 3 +
                                    buffer.getPixel(x2r, y1t).getGreen() / 64 * 3 + buffer.getPixel(x2r, y1b).getGreen() / 64 * 3 +
                                    buffer.getPixel(x1l, y2b).getGreen() / 64 * 3 + buffer.getPixel(x1r, y2b).getGreen() / 64 * 3 +
                                    buffer.getPixel(x1l, y1t).getGreen() / 64 * 9 + buffer.getPixel(x1r, y1t).getGreen() / 64 * 9 + buffer.getPixel(x1r, y1b).getGreen() / 64 * 9 + buffer.getPixel(x1l, y1b).getGreen() / 64 * 9,
                            buffer.getPixel(x2l, y2t).getBlue() / 64 + buffer.getPixel(x2l, y2b).getBlue() / 64 + buffer.getPixel(x2r, y2t).getBlue() / 64 + buffer.getPixel(x2l, y2b).getBlue() / 64 +
                                    buffer.getPixel(x2l, y1b).getBlue() / 64 * 3 + buffer.getPixel(x2l, y1t).getBlue() / 64 * 3 +
                                    buffer.getPixel(x1l, y2t).getBlue() / 64 * 3 + buffer.getPixel(x1r, y2t).getBlue() / 64 * 3 +
                                    buffer.getPixel(x2r, y1t).getBlue() / 64 * 3 + buffer.getPixel(x2r, y1b).getBlue() / 64 * 3 +
                                    buffer.getPixel(x1l, y2b).getBlue() / 64 * 3 + buffer.getPixel(x1r, y2b).getBlue() / 64 * 3 +
                                    buffer.getPixel(x1l, y1t).getBlue() / 64 * 9 + buffer.getPixel(x1r, y1t).getBlue() / 64 * 9 + buffer.getPixel(x1r, y1b).getBlue() / 64 * 9 + buffer.getPixel(x1l, y1b).getBlue() / 64 * 9,
                            1);
                    p++;
                    yb -= 100;
                    continue;
                }
                result[t][p] = new Color(buffer.getPixel(i, j).getRed(), buffer.getPixel(i, j).getGreen(), buffer.getPixel(i, j).getBlue(), 1);
                j++;
                p++;
                yb += c;
            }
            for (; p < m; p++)
                result[t][p] = Color.WHITE;
            i++;
            t++;
            xb += c;
        }
        for(;t<n;t++)
            for(p=0;p<m;p++)
                result[t][p] = Color.WHITE;
        return new ImageBuffer(result);
    }
}
