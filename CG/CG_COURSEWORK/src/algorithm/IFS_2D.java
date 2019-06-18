package algorithm;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.Random;

/**
 * Created by Sergey on 24.01.2017.
 */
//Система итерируемых функций
public class IFS_2D {
    //Первое аффинное преобразование
    private static IF_2D function1;
    //Второе аффинное преобразование
    private static IF_2D function2;
    //Третье аффинное преобразование
    private static IF_2D function3;
    //Четветое аффинное преобразование
    private static IF_2D function4;
    //Массив точек фрактала
    private static List<Point2D> points;
    //Генератор случайных чисел
    private static Random random;
    private static MyObserver observer;

    public static void setObserver(MyObserver observer) {
        IFS_2D.observer = observer;
    }

    //Поиск точек фрактала (fun1-4 - афинные преобразования, base - началаная точка, iteration - количество итераций (точек))
    public static Point2D[] start(IF_2D fun1, IF_2D fun2, IF_2D fun3, IF_2D fun4, Point2D base, int iterations){
        function1 = fun1;
        function2 = fun2;
        function3 = fun3;
        function4 = fun4;
        double chance1 = fun1.getChance(),
                chance2 = fun2.getChance(),
                chance3 = fun3.getChance(),
                chance4 = fun4.getChance();
        double c1 = fun2.getChance()+fun1.getChance(),
                c2 = fun3.getChance()+fun2.getChance()+fun1.getChance(),
                c3 = fun4.getChance()+fun3.getChance()+fun2.getChance()+fun1.getChance();
        function2.setChance(c1);
        function3.setChance(c2);
        function4.setChance(c3);
        points = new ArrayList<>();
        random = new Random();
        points.add(findBeginPoint(base));
        Point2D point = points.get(0),
                nPoint;
        for(int i = 0; i < iterations; i++){
            if(observer != null)
                observer.update(i);
            int value = random.nextInt(100);
            if (value <= function1.getChance()*100){
                nPoint = function1.calculate(point);
                points.add(nPoint);
                point = nPoint;
                continue;
            }
            if (value <= function2.getChance()*100){
                nPoint = function2.calculate(point);
                points.add(nPoint);
                point = nPoint;
                continue;
            }
            if (value <= function3.getChance()*100){
                nPoint = function3.calculate(point);
                points.add(nPoint);
                point = nPoint;
                continue;
            }
            if (value <= function4.getChance()*100) {
                nPoint = function4.calculate(point);
                points.add(nPoint);
                point = nPoint;
                continue;
            }
        }
        fun1.setChance(chance1);
        fun2.setChance(chance2);
        fun3.setChance(chance3);
        fun4.setChance(chance4);
        return points.toArray(new Point2D[points.size()]);
    }

    //Поиск точки-аттрактора
    private static Point2D findBeginPoint(Point2D point){
        Point2D nPoint = point;
        for(int i = 0; i < 100; i++, point = nPoint){
            double value = random.nextDouble();
            if (value <= function1.getChance())
                nPoint = function1.calculate(point);
            if (value <= function2.getChance())
                nPoint = function2.calculate(point);
            if (value <= function3.getChance())
                nPoint = function3.calculate(point);
            if (value <= function4.getChance())
                nPoint = function4.calculate(point);
        }
        return nPoint;
    }
}
