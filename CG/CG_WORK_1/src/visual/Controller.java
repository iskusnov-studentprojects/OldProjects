package visual;

import graphicalobjects.GraphicalObject3D;
import graphicalobjects.Jumping;
import graphicalobjects.Point3D;
import graphicalobjects.Polygon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import operations.AffineTranformation;

public class Controller {
    private Drawing drawing;
    private GraphicalObject3D[] graphicalObject3Ds;
    private Jumping jumping;

    @FXML
    private Button rotateOZButtonPlus;

    @FXML
    private Button moveOZButtonMinus;

    @FXML
    private Canvas canvas;

    @FXML
    private Button zoomButtonPlus;

    @FXML
    private Button rotateOXButtonMinus;

    @FXML
    private Button moveOYButtonPlus;

    @FXML
    private Button rotateOZButtonMinus;

    @FXML
    private Button moveOXButtonPlus;

    @FXML
    private Button moveOYButtonMinus;


    @FXML
    private Button rotateOXButtonPlus;

    @FXML
    private Button moveOXButtonMinus;

    @FXML
    private Button moveOZButtonPlus;

    @FXML
    private Button rotateOYButtonMinus;

    @FXML
    private Button rotateOYButtonPlus;

    @FXML
    private Button zoomButtonMinus;

    @FXML
    private Button reflectionOXButton;

    @FXML
    private Button reflectionOYButton;

    @FXML
    private Button reflectionOZButton;

    @FXML
    private Button startJumping;

    @FXML
    private Button stopJumping;

    @FXML
    private Button refreshJumping;

    @FXML
    private TextField heightTextField;

    @FXML
    void initialize() {
        assert rotateOZButtonPlus != null : "fx:id=\"rotateOZButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert moveOZButtonMinus != null : "fx:id=\"moveOZButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'form.fxml'.";
        assert zoomButtonPlus != null : "fx:id=\"zoomButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert rotateOXButtonMinus != null : "fx:id=\"rotateOXButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert moveOYButtonPlus != null : "fx:id=\"moveOYButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert stopJumping != null : "fx:id=\"stopJumping\" was not injected: check your FXML file 'form.fxml'.";
        assert reflectionOXButton != null : "fx:id=\"reflectionOXButton\" was not injected: check your FXML file 'form.fxml'.";
        assert rotateOZButtonMinus != null : "fx:id=\"rotateOZButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert moveOXButtonPlus != null : "fx:id=\"moveOXButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert moveOYButtonMinus != null : "fx:id=\"moveOYButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert reflectionOYButton != null : "fx:id=\"reflectionOYButton\" was not injected: check your FXML file 'form.fxml'.";
        assert refreshJumping != null : "fx:id=\"refreshJumping\" was not injected: check your FXML file 'form.fxml'.";
        assert rotateOXButtonPlus != null : "fx:id=\"rotateOXButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert heightTextField != null : "fx:id=\"heightTextField\" was not injected: check your FXML file 'form.fxml'.";
        assert moveOXButtonMinus != null : "fx:id=\"moveOXButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert moveOZButtonPlus != null : "fx:id=\"moveOZButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert rotateOYButtonMinus != null : "fx:id=\"rotateOYButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert rotateOYButtonPlus != null : "fx:id=\"rotateOYButtonPlus\" was not injected: check your FXML file 'form.fxml'.";
        assert zoomButtonMinus != null : "fx:id=\"zoomButtonMinus\" was not injected: check your FXML file 'form.fxml'.";
        assert startJumping != null : "fx:id=\"startJumping\" was not injected: check your FXML file 'form.fxml'.";
        assert reflectionOZButton != null : "fx:id=\"reflectionOZButton\" was not injected: check your FXML file 'form.fxml'.";

        canvas.getGraphicsContext2D().setFill(Color.WHITE);
        drawing = new Drawing(canvas.getGraphicsContext2D());
        graphicalObject3Ds = new GraphicalObject3D[]{new GraphicalObject3D(), new GraphicalObject3D(), new GraphicalObject3D()};
        Point3D[] points;

        points = new Point3D[]{new Point3D(10, 10, 10), new Point3D(110,10,10), new Point3D(110,110,10), new Point3D(10,110,10)};
        graphicalObject3Ds[0].addPolygon(points, Color.GREEN);
        points = new Point3D[]{new Point3D(10, 10, 110), new Point3D(110,10,110), new Point3D(110,110,110), new Point3D(10,110,110)};
        graphicalObject3Ds[0].addPolygon(points, Color.RED);
        points = new Point3D[]{new Point3D(10, 10, 10), new Point3D(10,10,110), new Point3D(10,110,110), new Point3D(10,110,10)};
        graphicalObject3Ds[0].addPolygon(points, Color.BLUE);
        points = new Point3D[]{new Point3D(110, 10, 10), new Point3D(110,10,110), new Point3D(110,110,110), new Point3D(110,110,10)};
        graphicalObject3Ds[0].addPolygon(points, Color.GRAY);
        points = new Point3D[]{new Point3D(10, 10, 10), new Point3D(110,10,10), new Point3D(110,10,110), new Point3D(10,10,110)};
        graphicalObject3Ds[0].addPolygon(points, Color.YELLOW);
        points = new Point3D[]{new Point3D(10, 110, 10), new Point3D(110,110,10), new Point3D(110,110,110), new Point3D(10,110,110)};
        graphicalObject3Ds[0].addPolygon(points, Color.OLIVE);

        points = new Point3D[]{new Point3D(-10, -10, -10), new Point3D(-55,-10,-10), new Point3D(-55,-55,-10), new Point3D(-10,-55,-10)};
        graphicalObject3Ds[1].addPolygon(points, Color.GREEN);
        points = new Point3D[]{new Point3D(-10, -10, -55), new Point3D(-55,-10,-55), new Point3D(-55,-55,-55), new Point3D(-10,-55,-55)};
        graphicalObject3Ds[1].addPolygon(points, Color.RED);
        points = new Point3D[]{new Point3D(-10, -10, -10), new Point3D(-10,-10,-55), new Point3D(-10,-55,-55), new Point3D(-10,-55,-10)};
        graphicalObject3Ds[1].addPolygon(points, Color.BLUE);
        points = new Point3D[]{new Point3D(-55, -10, -10), new Point3D(-55,-10,-55), new Point3D(-55,-55,-55), new Point3D(-55,-55,-10)};
        graphicalObject3Ds[1].addPolygon(points, Color.GRAY);
        points = new Point3D[]{new Point3D(-10, -10, -10), new Point3D(-55,-10,-10), new Point3D(-55,-10,-55), new Point3D(-10,-10,-55)};
        graphicalObject3Ds[1].addPolygon(points, Color.YELLOW);
        points = new Point3D[]{new Point3D(-10,-55,-10), new Point3D(-55,-55,-10), new Point3D(-55,-55,-55), new Point3D(-10,-55,-55)};
        graphicalObject3Ds[1].addPolygon(points, Color.OLIVE);

        points = new Point3D[]{new Point3D(0, 200, 0), new Point3D(200, 50, 50), new Point3D(-10,50,-10)};
        graphicalObject3Ds[1].addPolygon(points, Color.GREEN);

        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void rotateOXButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.rotationByX(k, -Math.PI/16);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void rotateOXButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.rotationByX(k, Math.PI/16);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void rotateOYButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.rotationByY(k, -Math.PI/16);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void rotateOYButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.rotationByY(k, Math.PI/16);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void rotateOZButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.rotationByZ(k, -Math.PI/16);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void rotateOZButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.rotationByZ(k, Math.PI/16);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void moveOXButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.movement(k, -10, 0, 0);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void moveOXButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.movement(k, 10, 0, 0);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void moveOYButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.movement(k, 0, -10, 0);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void moveOYButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.movement(k, 0, 10, 0);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void moveOZButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.movement(k, 0, 0, -10);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void moveOZButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.movement(k, 0, 0, 10);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void reflectionOXButton_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.reflection(k, true, false, false);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void reflectionOYButton_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.reflection(k, false, true, false);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void reflectionOZButtons_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.reflection(k, false, false, true);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void zoomButtonMinus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.zoom(k, 0.5, 0.5, 0.5);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void zoomButtonPlus_Action(ActionEvent event) {
        for (GraphicalObject3D i: graphicalObject3Ds)
            for (Polygon j: i.getPolygons())
                for (Point3D k: j.getPoints())
                    AffineTranformation.zoom(k, 2, 2, 2);
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void startJumping_Action(ActionEvent event) throws InterruptedException {
        if(jumping != null && jumping.getState() != Thread.State.TERMINATED)
            jumping.done();
        jumping = new Jumping(500,graphicalObject3Ds,drawing);
        jumping.start();
    }

    @FXML
    void stopJumping_Action(ActionEvent event) throws InterruptedException {
        //jumping.done();
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void keyPressed(KeyEvent event){
        switch (event.getCode()){
            case UP: Drawing.light.setY(Drawing.light.getY()-1);
                break;
            case DOWN: Drawing.light.setY(Drawing.light.getY()+1);
                break;
            case LEFT: Drawing.light.setX(Drawing.light.getX()-1);
                break;
            case RIGHT: Drawing.light.setX(Drawing.light.getX()+1);
                break;
            case ADD: Drawing.light.setZ(Drawing.light.getZ()-1);
                break;
            case SUBTRACT: Drawing.light.setZ(Drawing.light.getZ()+1);
                break;
            case F5:;
        }
        drawing.draw(graphicalObject3Ds);
    }

    @FXML
    void mouseClick(MouseEvent event){
        heightTextField.setText(new Double(event.getX()).toString() + ' ' + new Double(event.getY()).toString());
    }
//*/
}
