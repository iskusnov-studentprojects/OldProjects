package visual;

import graphicalobjects.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import operations.AffineTranformation;
import operations.ImageBuffer;

/**
 * Created by Sergey on 10.10.2016.
 */
public class Drawing {
    private GraphicsContext graphicsContext;

    //Параметры камеры
    public static final double cam = -700;
    public static final double pl = -100;
    public static final double angleA = 0;
    public static final double angleB = 0;

    //Произвольная система координат
    public static final double X0 = 300;
    public static final double Y0 = 250;

    //Источник света
    public static Point3D light = new Point3D(120, 100, 100);
    //Интенсивности рассеяного света
    public static final double IA = 2;
    //Коэффициэнт дифузионного отражения рассеянного света
    public static final double KA = 0.3;
    //Интенсивности источника света
    public static final double II = 50;
    //Коэффициэнт дифузионного отражения
    public static final double KD = 1;
    //Эксперементальная постоянная
    public static final double KS = 0.4;
    //Произвольная постоянная
    public static final double K = 5;
    //Коэффициент фонга
    public static final double P = 10;

    public Drawing(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    private static double[] matrixMultiply(double[] m1, double[][] m2){
        int n = m1.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++)
                result[i] += m1[j] * m2[j][i];
        }
        return result;
    }

    public static Point2D projection(Point3D point) {
        //*
        //Перспективная проекция
        AffineTranformation.rotationByX(point, -angleA);
        AffineTranformation.rotationByY(point, -angleB);
        double[] pointMatrix = {point.getX(), point.getY(), point.getZ(), point.getH()};
        double[][] transformMatrix = { // Формируем матрицу преобразования
                {(cam - pl) / (cam - point.getZ()), 0, 0, 0},
                {0, (cam - pl) / (cam - point.getZ()), 0, 0},
                {0, 0, 1, 0},
                {0, 0, -pl, 1}
        };

        AffineTranformation.rotationByY(point, angleB);
        AffineTranformation.rotationByX(point, angleA);
        double[] res = matrixMultiply(pointMatrix, transformMatrix);
        return new Point2D(res[0]+X0, res[1]+Y0);
    }

    public void draw(GraphicalObject3D[] objects) {
        graphicsContext.fillRect(0,0,graphicsContext.getCanvas().getWidth(),graphicsContext.getCanvas().getHeight());
        //*
        ImageBuffer.start((int)graphicsContext.getCanvas().getWidth(),(int)graphicsContext.getCanvas().getHeight(), objects);
        BufferElement[][] imageBuffer = ImageBuffer.getImageBuffer();
        PixelWriter pen = graphicsContext.getPixelWriter();
        for(int i = 0; i < imageBuffer.length; i++)
            for (int j = 0; j < imageBuffer[i].length; j++) {
                if(imageBuffer[i][j].getColor() != Color.WHITE) {
                    pen.setColor(i, j, imageBuffer[i][j].getColor());
                    //pen.setColor(i, j, imageBuffer[i][j].getColor());
                }
            }
        //*/
        /*
        for (GraphicalObject3D i: objects)
            for (Polygon j: i.getPolygons()){
                for(int k = 0; k < j.getPoints().length; k++){
                    int n = (k < j.getPoints().length - 1)? k+1: 0;
                    Point2D point1 = Drawing.projection(j.getPoints()[k]),
                            point2 = Drawing.projection(j.getPoints()[n]);
                    graphicsContext.strokeLine(point1.getX(),point1.getY(),point2.getX(),point2.getY());
                }
            }
        //*/
        Point2D point = projection(light);
    }
//*/
}
