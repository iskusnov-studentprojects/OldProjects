package graphicalobjects;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Sergey on 20.12.2016.
 */
public class Polygon {
    Point3D[] points;
    Color color;
    int id;

    public Polygon(Point3D[] points, Color color) {
        this.points = points;
        this.color = color;
        Random rand = new Random();
        id = rand.nextInt();
    }

    public boolean pointInPolygon(double x, double y){
        graphicalobjects.Point2D[] points = projection();
        int i1, i2, n, N;
        boolean flag = false;
        double S, S1, S2, S3;
        N = points.length;
        for (n=0; n<N; n++)
        {
            flag = false;
            i1 = n < N-1 ? n + 1 : 0;
            while (!flag)
            {
                i2 = i1 + 1;
                if (i2 >= N)
                    i2 = 0;
                if (i2 == (n < N-1 ? n + 1 : 0))
                    break;
                S = Math.abs (points[i1].getX() * (points[i2].getY() - points[n ].getY()) +
                        points[i2].getX() * (points[n ].getY() - points[i1].getY()) +
                        points[n].getX()  * (points[i1].getY() - points[i2].getY()));
                S1 = Math.abs (points[i1].getX() * (points[i2].getY() - y) +
                        points[i2].getX() * (y       - points[i1].getY()) +
                        x       * (points[i1].getY() - points[i2].getY()));
                S2 = Math.abs (points[n ].getX() * (points[i2].getY() - y) +
                        points[i2].getX() * (y       - points[n ].getY()) +
                        x       * (points[n ].getY() - points[i2].getY()));
                S3 = Math.abs (points[i1].getX() * (points[n ].getY() - y) +
                        points[n ].getX() * (y       - points[i1].getY()) +
                        x       * (points[i1].getY() - points[n ].getY()));
                if (Math.abs(S - (S1 + S2 + S3)) < 0.001)
                {
                    flag = true;
                    break;
                }
                i1 = i1 + 1;
                if (i1 >= N)
                    i1 = 0;
            }
            if (!flag)
                break;
        }
        return flag;
        /*int s = 0;
        for(int i = 0; i < points.length; i++){
            int j = i < points.length-1? i+1: 0;
            double k = (points[j].getY() - points[i].getY())/(points[j].getX() - points[i].getX()),
                    m = points[i].getY() - points[i].getX()*k;
            if(!(((top - k*left - m) >= 0 &&
                (bottom - k*left - m) >= 0 &&
                (top - k*right - m) >= 0 &&
                (bottom - k*right - m) >= 0 ) ||
                ((top - k*left - m) < 0 &&
                (bottom - k*left - m) < 0 &&
                (top - k*right - m) < 0 &&
                (bottom - k*right - m) < 0 )))
                return true;

        }
        return s!=0;
        */
    }

    public double zCoordinateInPoint(double x, double y){
        double A,B,C,D,
                x1 = points[0].getProjection().getX(), y1 = points[0].getProjection().getY(), z1 = points[0].getZ(),
                x2 = points[1].getProjection().getX(), y2 = points[1].getProjection().getY(), z2 = points[1].getZ(),
                x3 = points[2].getProjection().getX(), y3 = points[2].getProjection().getY(), z3 = points[2].getZ();
        A = y1*(z2 - z3) + y2*(z3 - z1) + y3*(z1 - z2);
        B = z1*(x2 - x3) + z2*(x3 - x1) + z3*(x1 - x2);
        C = x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2);
        D =-(x1*(y2*z3 - y3*z2) + x2*(y3*z1 - y1*z3) + x3*(y1*z2 - y2*z1));
        if((int)C == 0 && points.length > 3){
            x3 = points[3].getProjection().getX(); y3 = points[3].getProjection().getY(); z3 = points[3].getZ();
            A = y1*(z2 - z3) + y2*(z3 - z1) + y3*(z1 - z2);
            B = z1*(x2 - x3) + z2*(x3 - x1) + z3*(x1 - x2);
            C = x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2);
            D =-(x1*(y2*z3 - y3*z2) + x2*(y3*z1 - y1*z3) + x3*(y1*z2 - y2*z1));
        }
        return (int)C != 0?-(A*x+B*y+D)/C: -(A*x+B*y+D);
    }

    public Point3D calculateNormal(){
        double A,B,C,
                x1 = points[0].getX(), y1 = points[0].getY(), z1 = points[0].getZ(),
                x2 = points[1].getX(), y2 = points[1].getY(), z2 = points[1].getZ(),
                x3 = points[2].getX(), y3 = points[2].getY(), z3 = points[2].getZ();
        A = y1*(z2 - z3) + y2*(z3 - z1) + y3*(z1 - z2);
        B = z1*(x2 - x3) + z2*(x3 - x1) + z3*(x1 - x2);
        C = x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2);
        double len = Math.sqrt(A*A+B*B+C*C);
        return new Point3D(A/len,B/len,C/len);
    }

    public int getID(){
        return id;
    }

    public Color getColor(){
        return color;
    }

    public Point3D[] getPoints(){
        return points;
    }

    public double getXMin(){
        double value = points[0].getProjection().getX();
        for (Point3D i: points) {
            if(i.getProjection().getX() < value)
                value = i.getProjection().getX();
        }
        return value;
    }

    public double getYMin(){
        double value = points[0].getProjection().getY();
        for (Point3D i: points) {
            if(i.getProjection().getY() < value)
                value = i.getProjection().getY();
        }
        return value;
    }

    public double getXMax(){
        double value = points[0].getProjection().getX();
        for (Point3D i: points) {
            if(i.getProjection().getX() > value)
                value = i.getProjection().getX();
        }
        return value;
    }

    public double getYMax(){
        double value = points[0].getProjection().getY();
        for (Point3D i: points) {
            if(i.getProjection().getY() > value)
                value = i.getProjection().getY();
        }
        return value;
    }

    public double getZMax(){
        double value = points[0].getZ();
        for (Point3D i: points) {
            if(i.getZ() > value)
                value = i.getZ();
        }
        return value;
    }

    private graphicalobjects.Point2D[] projection(){
        graphicalobjects.Point2D[] res = new graphicalobjects.Point2D[points.length];
        for(int i = 0; i < points.length; i++)
            res[i] = points[i].getProjection();
        return res;
    }

}
