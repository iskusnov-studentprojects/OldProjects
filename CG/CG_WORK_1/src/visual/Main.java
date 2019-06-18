package visual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("form.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    /*
    //test
    public static void main(String[] args) {
        Point3D[] points = {new Point3D(0,0,0),
                new Point3D(0,10,0),
                new Point3D(10,10,0),
                new Point3D(10,0,0)};
        Point2D point = new Point2D(5,11);
        Polygon polygon = new Polygon(points, Color.GREEN);
        boolean result = polygon.pointInPolygon(point);
    }
    //*/
}
