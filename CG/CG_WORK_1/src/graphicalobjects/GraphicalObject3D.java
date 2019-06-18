package graphicalobjects;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by Sergey on 27.09.2016.
 */
public class GraphicalObject3D {
    private List<Polygon> obj;

    public GraphicalObject3D(){
        obj = new ArrayList<>();
    }

    public void addPolygon(Polygon polygon){
        obj.add(polygon);
    }

    public void addPolygon(Point3D[] points, Color color){
        obj.add(new Polygon(points, color));
    }

    public void removePolygon(){
        if(!obj.isEmpty()){
            obj.remove(obj.size()-1);
        }
    }

    public void clear(){
        obj.clear();
    }

    public Polygon getPolygonByNumber(int index){
        return obj.get(index);
    }

    public int getNumberOfPolygons(){
        return obj.size();
    }

    public void forEach(Consumer<? super Polygon> action){
        obj.forEach(action);
    }

    public Polygon getPolygonByID(int id){
        for (Polygon i: obj)
            if(i.getID() == id)
                return i;
        return null;
    }

    public List<Polygon> getPolygons(){
        return obj;
    }
}
