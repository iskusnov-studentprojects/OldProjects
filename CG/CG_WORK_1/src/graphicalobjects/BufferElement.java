package graphicalobjects;

import javafx.scene.paint.Color;

/**
 * Created by Sergey on 13.01.2017.
 */
public class BufferElement {
    private Color color;
    private double deep;

    public BufferElement(Color color, double deep) {
        this.color = color;
        this.deep = deep;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getDeep() {
        return deep;
    }

    public void setDeep(double deep) {
        this.deep = deep;
    }
}
