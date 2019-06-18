package graphicalobjects;

/**
 * Created by Sergey on 27.09.2016.
 */
public class Point2D {
    private double X;
    private double Y;
    private double h;

    public Point2D(double x, double y) {
        X = x;
        Y = y;
        h = 1;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
        setX(getX()*h);
        setY(getY()*h);
    }
}
