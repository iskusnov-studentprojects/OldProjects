package graphicalobjects;

import operations.AffineTranformation;
import visual.Drawing;

/**
 * Created by Sergey on 10.10.2016.
 */
public class Jumping extends Thread {
    private double speed;
    private int height;
    private double dilation;
    private double vector;
    private final double g = 1;

    private boolean running;

    private GraphicalObject3D[] graphicalObject3Ds;
    private Drawing drawing;

    public Jumping(int height, GraphicalObject3D[] graphicalObject3Ds, Drawing drawing){
        setSpeed(0);
        setDilation(100);
        setHeight(height);
        vector = 1;
        this.graphicalObject3Ds = graphicalObject3Ds;
        this.drawing = drawing;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed >= 0 ? speed : 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getDilation() {
        return dilation;
    }

    public void setDilation(double dilation) {
        this.dilation = dilation;
    }

    @Override
    public void run() {
        while (running) {
            int sleepTime = (int) (getDilation() / (getSpeed() > 0 ? getSpeed() : 1));
            try {
                this.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (vector > 0) {
                objectDown();
                setSpeed(getSpeed() + g * sleepTime / 1000);
                height--;
                if (height == 0) {
                    vector = -1;
                    setSpeed(getSpeed() * 0.9);
                }
            } else {
                objectUp();
                setSpeed(getSpeed() - g * sleepTime / 1000);
                height++;
                if (getSpeed() == 0)
                    vector = 1;
            }
        }
    }

    public void objectUp(){
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                AffineTranformation.movement(k, 0, -1, 0);
        drawing.draw(graphicalObject3Ds);
    }

    public void objectDown(){
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                AffineTranformation.movement(k, 0, 1, 0);
        drawing.draw(graphicalObject3Ds);
    }

    @Override
    public void start(){
        running = true;
        if(this.getState() == State.NEW)
            super.start();
    }

    public void done() throws InterruptedException {
        running = false;
    }
    //*/
}
