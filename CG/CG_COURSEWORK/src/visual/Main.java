package visual;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("form.fxml"));
        Parent root = loader.load();
        ((Controller)loader.getController()).setStage(primaryStage);
        primaryStage.setTitle("Генератор фракталов");
        primaryStage.setScene(new Scene(root, 850, 640));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}