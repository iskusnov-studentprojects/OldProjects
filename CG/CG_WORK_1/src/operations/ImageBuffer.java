package operations;

import graphicalobjects.BufferElement;
import graphicalobjects.GraphicalObject3D;
import graphicalobjects.Point3D;
import graphicalobjects.Polygon;
import javafx.scene.paint.Color;
import visual.Drawing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static visual.Drawing.*;
/**
 * Created by Sergey on 20.12.2016.
 */
public class ImageBuffer {
    private static BufferElement[][] imageBuffer;

    static public BufferElement[][] start(int windowWidth, int windowHeight, GraphicalObject3D[] objects){
        imageBuffer = new BufferElement[windowWidth][windowHeight];
        List<Polygon> polygons = new ArrayList<>();
        for(GraphicalObject3D t : objects)
            for (int k = 0; k < t.getNumberOfPolygons(); k++)
                polygons.add(t.getPolygonByNumber(k));
        //step(0, 0, windowWidth - 1, windowHeight - 1, objects);
        for(int i = 0; i < windowWidth; i++) {
            for (int j = 0; j < windowHeight; j++) {
                if (i == 320 && j == 306)
                    i = i;
                double maxZ = -1000000;
                Polygon polygon = null;
                for (Polygon t : polygons)
                    if (t.getYMin() <= j &&
                            t.getXMin() <= i &&
                            t.getYMax() >= j &&
                            t.getXMax() >= i) {
                        if (t.pointInPolygon(i, j)) {
                            double z = t.zCoordinateInPoint(i, j);
                            if (z > maxZ) {
                                maxZ = z;
                                polygon = t;
                            }
                        }
                    }
                imageBuffer[i][j] = polygon == null ? new BufferElement(Color.WHITE, -1000000000) :
                        new BufferElement(calculateLight(i, j, maxZ, polygon.getColor(), polygon), maxZ);
            }
        }
        return imageBuffer;
    }

//region old
    /*
    static private void step(int xLeft, int yTop, int xRight, int yBottom, GraphicalObject3D[] objects){
        if(xLeft == xRight && yTop == yBottom && imageBuffer[xLeft][yTop] == 0) {
            double maxZ = -1000000;
            int idPolygon = 0;
            for (GraphicalObject3D i : objects)
                for (int j = 0; j < i.getNumberOfPolygons(); j++)
                    if (!polygonExternal(i.getPolygonByNumber(j), xLeft, yTop, xRight, yBottom)) {
                        double z = i.getPolygonByNumber(j).zCoordinateInPoint(xLeft, yTop);
                        if (z > maxZ) {
                            maxZ = z;
                            idPolygon = i.getPolygonByNumber(j).getID();
                        }
                    }
            if(idPolygon != 0) {
                Point2D point = Drawing.projection(new Point3D(xLeft, yTop, maxZ));
                imageBuffer[(int) point.getX()][(int) point.getY()] = idPolygon;
                //imageBuffer[xLeft][yBottom] = idPolygon;
            }
        } else {
            boolean allExt = true;
            for (GraphicalObject3D i : objects)
                for (int j = 0; j < i.getNumberOfPolygons() && allExt; j++)
                    if (!polygonExternal(i.getPolygonByNumber(j), xLeft, yTop, xRight, yBottom))
                        allExt = false;
            if (!allExt) {
                int newLeft = (xLeft + xRight) / 2,
                        newTop = (yTop + yBottom) / 2;
                if(xLeft <= newLeft && yTop <= newTop)
                    step(xLeft, yTop, newLeft, newTop, objects);
                if(newLeft+1 <= xRight && yTop <= newTop)
                    step(newLeft + 1, yTop, xRight, newTop, objects);
                if(newLeft + 1 <= xRight && newTop + 1 <= yBottom)
                    step(newLeft + 1, newTop + 1, xRight, yBottom, objects);
                if(xLeft <= newLeft && newTop + 1 <= yBottom)
                    step(xLeft, newTop + 1, newLeft, yBottom, objects);
            } else {
                for (int i = xLeft; i <= xRight; i++)
                    for (int j = yTop; j <= yBottom; j++)
                        imageBuffer[i][j] = 0;
            }
        }
    }

    static private boolean polygonExternal(Polygon polygon,int xLeft, int yTop, int xRight, int yBottom){
        if(polygon.getYMin() >= yTop &&
                polygon.getXMin() >= xLeft &&
                polygon.getYMax() <= yBottom &&
                polygon.getXMax() <= xRight)
            return false;
        if(polygon.getYMax() < yTop ||
                polygon.getXMax() < xLeft ||
                polygon.getYMin() > yBottom ||
                polygon.getXMin() > xRight)
            return true;
        return !polygon.pointInPolygon(xLeft,yTop,xRight,yBottom);
    }
    //*/
//endregion

    static public BufferElement[][] getImageBuffer(){
        return imageBuffer;
    }

    static public Color calculateLight(int x, int y, double z, Color color, Polygon polygon){
        //d - расстояние до источника света
        double len, d;
        len = Math.sqrt((Drawing.light.getX()-x)*(Drawing.light.getX()-x)+
                (Drawing.light.getY()-y)*(Drawing.light.getY()-y)+
                (Drawing.light.getZ()-z)*(Drawing.light.getZ()-z));
        d = len;
        Point3D vectorLight = new Point3D((Drawing.light.getX() - x)/len,
                (Drawing.light.getY() - y)/len,
                (Drawing.light.getZ() - z)/len),
                camera = new Point3D(0,0,1),
                normal = polygon.calculateNormal();
        double i =IA*KA +
                (II*KD/(d+K))*
                        (KD*scalarMul(vectorLight,normal)+
                        KS*Math.pow(scalarMul(foundReflectVector(normal,vectorLight), camera), Drawing.P));
        return Color.gray(i);
    }

    static private double scalarMul(Point3D vector1, Point3D vector2){
        return vector1.getX()*vector2.getX() + vector1.getY()*vector2.getY() + vector1.getZ()*vector2.getZ();
    }

    static private Point3D foundReflectVector(Point3D normal, Point3D light){
        double scalar = scalarMul(normal,light),
                scalar2 = scalarMul(normal,normal);
        double x = light.getX()-2*normal.getX()*scalar/scalar2,
                y = light.getY()-2*normal.getY()*scalar/scalar2,
                z = light.getZ()-2*normal.getZ()*scalar/scalar2,
                len = Math.sqrt(x*x+y*y+z*z);
        return new Point3D(x/len,y/len,z/len);
    }
}
