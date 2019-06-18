package graphicalobjects;

/**
 * Created by Sergey on 21.09.2016.
 */
import visual.Drawing;

public class Point3D {
    private double X;
    private double Y;
    private double Z;
    private double h;
    private Point2D projection;

    public Point3D(double x, double y, double z) {
        X = x;
        Y = y;
        Z = z;
        h = 1;
        projection = Drawing.projection(this);
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        if(X!=x) {
            X=x;
            projection = Drawing.projection(this);
        }
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        if(Y!=y) {
            Y=y;
            projection = Drawing.projection(this);
        }
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double z) {
        if(Z!=z) {
            Z=z;
            projection = Drawing.projection(this);
        }
    }

    public void setCoordinate(double x, double y, double z){
        if(X!=x || Y!=y || Z!=z){
            X=x;
            Y=y;
            Z=z;
            projection = Drawing.projection(this);
        }
    }

    public double getH() {
        return h;
    }

    public Point2D getProjection(){
        return projection;
    }

    public void setH(double h) {
        this.h = h;
        setX(getX()*h);
        setY(getY()*h);
        setZ(getZ()*h);
    }
}
