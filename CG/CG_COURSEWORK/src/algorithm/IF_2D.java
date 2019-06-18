package algorithm;

import javafx.geometry.Point2D;

/**
 * Created by Sergey on 24.01.2017.
 */
//Аффинное преобразование
public class IF_2D {
    //Значения для функции X'=A*X+B*Y+C
    double aX;
    double bX;
    double dX;
    //Значения для функции Y'=D*X+E*Y+F
    double aY;
    double bY;
    double dY;
    //Вероятность выбора преобразования
    double chance;

    //Конструктор
    public IF_2D(double aX, double bX, double dX, double aY, double bY, double dY, double chance) {
        setaX(aX);
        setbX(bX);
        setdX(dX);
        setaY(aY);
        setbY(bY);
        setdY(dY);
        setChance(chance);
    }

    //region Сеттеры и геттеры
    public double getaX() {
        return aX;
    }

    public void setaX(double aX) {
        this.aX = aX;
    }

    public double getbX() {
        return bX;
    }

    public void setbX(double bX) {
        this.bX = bX;
    }

    public double getdX() {
        return dX;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public double getaY() {
        return aY;
    }

    public void setaY(double aY) {
        this.aY = aY;
    }

    public double getbY() {
        return bY;
    }

    public void setbY(double bY) {
        this.bY = bY;
    }

    public double getdY() {
        return dY;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }
    //endregion

    //Провести преобразование
    public Point2D calculate(Point2D point){
        return new Point2D(getaX()*point.getX()+getbX()*point.getY()+getdX(), getaY()*point.getX()+getbY()*point.getY()+getdY());
    }
}
