package visual;

import algorithm.IFS_2D;
import algorithm.IF_2D;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Controller {
    //Холст для вывода изображения
    @FXML
    private ImageView canvas;

    //Выпадающее меню для определения точности округления вещественных чисел
    @FXML
    private ComboBox<Integer> multiplyComboBox;

    //Переменная, хранящая точность округления вещественных чисел
    int cons = 1;
    //Колличество итераций алгоритма (итоговое колличество точек фрактала)
    final int iterations = 1000000;
    Stage stage;
    //Первое аффинное преобразование
    IF_2D function1;
    //Второе аффинное преобразование
    IF_2D function2;
    //Теретье аффинное преобразование
    IF_2D function3;
    //Четвертое аффинное преобразование
    IF_2D function4;
    //Массив точек фрактала с координатами вещественного типа
    Point2D[] points;
    //Массив точек фрактала с координатами целочисленного типа
    Point2D[] normalPoints;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    //Функция инициализации элементов окна
    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'form.fxml'.";
        assert multiplyComboBox != null : "fx:id=\"multiplyComboBox\" was not injected: check your FXML file 'form.fxml'.";

        multiplyComboBox.getItems().addAll(1,10,100,1000,10000,100000);
        multiplyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            cons = newValue;
        });
    }

    //Событие для кнопки "Сгенерировать фрактал"
    @FXML
    void generateButton_onAction(ActionEvent event) {
        try {
            Random rand = new Random();
            do {
                double[][] k = new double[4][6];
                for (int i = 0; i < 4; i++)
                    do {
                        k[i][0] = rand.nextDouble() * (rand.nextBoolean() ? 1 : -1);
                        k[i][1] = rand.nextDouble() * (rand.nextBoolean() ? 1 : -1);
                        k[i][2] = rand.nextDouble() * (rand.nextBoolean() ? 1 : -1);
                        k[i][3] = rand.nextDouble() * (rand.nextBoolean() ? 1 : -1);
                        k[i][4] = rand.nextInt(200) - 100;
                        k[i][5] = rand.nextInt(200) - 100;
                    } while (Math.abs(k[i][0]) + Math.abs(k[i][1]) < 1 && Math.abs(k[i][2]) + Math.abs(k[i][3]) < 1);
                double[] chn = new double[4];
                do {
                    chn[0] = rand.nextDouble();
                    chn[1] = rand.nextDouble();
                    chn[2] = rand.nextDouble();
                    chn[3] = rand.nextDouble();
                } while ((chn[0] + chn[1] + chn[2] + chn[3]) < 0.999 || (chn[0] + chn[1] + chn[2] + chn[3]) > 1);
                function1 = new IF_2D(k[0][0], k[0][1], k[0][4], k[0][2], k[0][3], k[0][5], chn[0]);
                function2 = new IF_2D(k[1][0], k[1][1], k[1][4], k[1][2], k[1][3], k[1][5], chn[1]);
                function3 = new IF_2D(k[2][0], k[2][1], k[2][4], k[2][2], k[2][3], k[2][5], chn[2]);
                function4 = new IF_2D(k[3][0], k[3][1], k[3][4], k[3][2], k[3][3], k[3][5], chn[3]);
                points = IFS_2D.start(function1,
                        function2,
                        function3,
                        function4,
                        new Point2D(rand.nextInt(1000), rand.nextInt(1000)), iterations);
                normalPoints = new Point2D[points.length];
                for (int i = 0; i < points.length; i++) {
                    int x = (int) (points[i].getX() * cons),
                            y = (int) (points[i].getY() * cons);
                    normalPoints[i] = new Point2D(x, y);
                }
            } while (findMaxX(points) > 1000 || findMaxY(points) > 1000);
            draw_onAction(event);
        }
        catch(Exception e){
            Dialogs.showErrorDialog(stage, e.getMessage());
        }
    }

    //Событие для кнопки "Сохранить изображение"
    @FXML
    void saveButton_onAction(ActionEvent event) throws IOException {
        try {
            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"),
                    new FileChooser.ExtensionFilter("JPEG", "*.jpg", "*.jpeg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"));
            chooser.setTitle("Сохранить файл");
            File file = chooser.showSaveDialog(stage);
            if (file == null)
                return;
            Random rand = new Random();
            WritableImage image;

            points = IFS_2D.start(function1,
                    function2,
                    function3,
                    function4,
                    new Point2D(rand.nextInt(1000), rand.nextInt(1000)), iterations*2);
            normalPoints = new Point2D[points.length];
            for (int i = 0; i < points.length; i++) {
                int x = (int) (points[i].getX() * cons),
                        y = (int) (points[i].getY() * cons);
                normalPoints[i] = new Point2D(x, y);
            }
            int minX = findMinX(normalPoints),
                    minY = findMinY(normalPoints);
            image = new WritableImage(findMaxX(normalPoints) - minX + 20, findMaxY(normalPoints) - minY + 20);
            PixelWriter writer = image.getPixelWriter();
            for (Point2D i : normalPoints) {
                float red = (float) Math.abs(i.getX()),
                        green = (float) Math.abs(i.getY()),
                        blue = (float) Math.abs(i.getX() + i.getY());
                while (red > 1)
                    red /= 10;
                while (green > 1)
                    green /= 10;
                while (blue > 1)
                    blue /= 10;
                writer.setColor((int) (i.getX() - minX + 10), (int) (i.getY() - minY + 10), new Color(red, green, blue, 1));
            }
            file.createNewFile();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), getFileExtension(file.getAbsoluteFile()), file);
        }
        catch (Exception e){
            Dialogs.showErrorDialog(stage, e.getMessage());
        }
    }

    //Событие для кнопки "Установить параметры"
    @FXML
    void setFunctions_onAction(ActionEvent event) throws IOException {
        if(function1==null)
            function1 = new IF_2D(0.3,-0.3,1,0.3,0.3,1,0.25);
        if(function2==null)
            function2 = new IF_2D(0.3,-0.3,1,0.3,0.3,-1,0.25);
        if(function3==null)
            function3 =  new IF_2D(0.3,-0.3,-1,0.3,0.3,1,0.25);
        if(function4==null)
            function4 =  new IF_2D(0.3,-0.3,-1,0.3,0.3,-1,0.25);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ifs.fxml"));
        Stage window = new Stage();
        Parent parent = loader.load();
        window.setScene(new Scene(parent));
        ((IFSController)loader.getController()).setFunctions(function1,function2,function3,function4);
        ((IFSController)loader.getController()).setStage(window);
        window.setTitle("СИФ");
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(stage);
        window.show();
    }

    //Событие для кнопки "Нарисовать фрактал"
    @FXML
    void  draw_onAction(ActionEvent event) {
        try {
            Random rand = new Random();
            WritableImage image;
            Point2D[] normalPoints;
            points = IFS_2D.start(function1,
                    function2,
                    function3,
                    function4,
                    new Point2D(rand.nextInt(1000), rand.nextInt(1000)), iterations);
            normalPoints = new Point2D[points.length];
            for (int i = 0; i < points.length; i++) {
                int x = (int) (points[i].getX() * cons),
                        y = (int) (points[i].getY() * cons);
                normalPoints[i] = new Point2D(x, y);
            }
            int minX = findMinX(normalPoints),
                    minY = findMinY(normalPoints);
            image = new WritableImage(findMaxX(normalPoints) - minX + 20, findMaxY(normalPoints) - minY + 20);
            PixelWriter writer = image.getPixelWriter();
            for (Point2D i : normalPoints) {
                float red = (float) Math.abs(i.getX()),
                        green = (float) Math.abs(i.getY()),
                        blue = (float) Math.abs(i.getX() + i.getY());
                while (red > 1)
                    red /= 10;
                while (green > 1)
                    green /= 10;
                while (blue > 1)
                    blue /= 10;
                writer.setColor((int) (i.getX() - minX + 10), (int) (i.getY() - minY + 10), new Color(red, green, blue, 1));
            }
            canvas.setImage(image);
        }
        catch (Exception e){
            Dialogs.showErrorDialog(stage, e.getMessage());
        }
    }

    //Найти минимальное значение среди точек фрактала по оси Ox
    private int findMinX(Point2D[] points){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++)
            if(min > points[i].getX())
                min = (int) points[i].getX();
        return min;
    }

    //Найти минимальное значение среди точек фрактала по оси Oy
    private int findMinY(Point2D[] points){
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++)
            if(min > points[i].getY())
                min = (int) points[i].getY();
        return min;
    }

    //Найти максимальное значение среди точек фрактала по оси Ox
    private int findMaxX(Point2D[] points){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++)
            if(max < points[i].getX())
                max = (int) points[i].getX();
        return max;
    }

    //Найти максимальное значение среди точек фрактала по оси Oy
    private int findMaxY(Point2D[] points){
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < points.length; i++)
            if(max < points[i].getY())
                max = (int) points[i].getY();
        return max;
    }

    //Получить расширение файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".")+1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}