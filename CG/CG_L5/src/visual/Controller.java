package visual;

import algorithm.ColorCorrection;
import algorithm.Effects;
import algorithm.Zoom;
import draw.Drawing;
import draw.Handler;
import draw.ImageBuffer;
import draw.ImageBufferI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.Toolkit;

public class Controller {

    //region Визуальные элементы
    @FXML
    private Canvas canvas;

    @FXML
    private CheckMenuItem shadesOfGrayCheckMenuItem;

    @FXML
    private MenuItem exitMenuItem;

    @FXML
    private MenuItem saveChangesMenuItem;

    @FXML
    private MenuItem cancelChangesMenuItem;

    @FXML
    private CheckBox shadesOfGrayCheckBox;

    @FXML
    private MenuItem openFileMenuItem;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Slider contrastSlider;

    @FXML
    private CheckMenuItem noiseCheckMenuItem;

    @FXML
    private CheckBox noiseCheckBox;

    @FXML
    private Slider noiseSlider;

    @FXML
    private CheckBox medianFilterCheckBox;

    @FXML
    private CheckMenuItem medianFilterCheckMenuItem;

    @FXML
    private ComboBox<Integer> medianFilterComboBox;

    @FXML
    private CheckBox uniformFilterCheckBox;

    @FXML
    private CheckMenuItem uniformFilterCheckMenuItem;

    @FXML
    private ComboBox<Integer> uniformFilterComboBox;

    @FXML
    private CheckBox stampingCheckBox;

    @FXML
    private CheckMenuItem stampingCheckMenuItem;

    @FXML
    private CheckMenuItem harshnessCheckMenuItem;

    @FXML
    private CheckBox harshnessCheckBox;

    @FXML
    private Slider harshnessSlider;

    @FXML
    private ComboBox<Integer> harshnessComboBox;

    @FXML
    private Slider nearestNeighborSlider;

    @FXML
    private Slider bicubicInterpolationSlider;

    @FXML
    private CheckBox negativeCheckBox;

    @FXML
    private CheckBox binaryCheckBox;

    @FXML
    private CheckMenuItem negativeCheckMenuItem;

    @FXML
    private CheckMenuItem binaryCheckMenuItem;
    //endregion

    private Stage stage;
    private Drawing drawing;
    private Handler<Color, Color> handlerColor;
    private Handler<ImageBuffer, ImageBuffer> handlerImage;
    private ImageBuffer image;
    private ImageBufferI imagei;
    private ImageBuffer buffer;
    private ImageBufferI bufferi;
    private double[][] stampingCore = {{0,1,0},
            {1,0,-1},
            {0,-1,0}};

    @FXML
    void initialize() {
        assert negativeCheckBox != null : "fx:id=\"negativeCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert noiseCheckMenuItem != null : "fx:id=\"noiseCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert medianFilterComboBox != null : "fx:id=\"medianFilterComboBox\" was not injected: check your FXML file 'form.fxml'.";
        assert saveChangesMenuItem != null : "fx:id=\"saveChangesMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert uniformFilterCheckMenuItem != null : "fx:id=\"uniformFilterCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert uniformFilterComboBox != null : "fx:id=\"uniformFilterComboBox\" was not injected: check your FXML file 'form.fxml'.";
        assert shadesOfGrayCheckMenuItem != null : "fx:id=\"shadesOfGrayCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert medianFilterCheckMenuItem != null : "fx:id=\"medianFilterCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert noiseCheckBox != null : "fx:id=\"noiseCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert harshnessCheckBox != null : "fx:id=\"harshnessCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert stampingCheckMenuItem != null : "fx:id=\"stampingCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert exitMenuItem != null : "fx:id=\"exitMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert uniformFilterCheckBox != null : "fx:id=\"uniformFilterCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert stampingCheckBox != null : "fx:id=\"stampingCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert medianFilterCheckBox != null : "fx:id=\"medianFilterCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert harshnessCheckMenuItem != null : "fx:id=\"harshnessCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert binaryCheckMenuItem != null : "fx:id=\"binaryCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert nearestNeighborSlider != null : "fx:id=\"nearestNeighborSlider\" was not injected: check your FXML file 'form.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'form.fxml'.";
        assert harshnessSlider != null : "fx:id=\"harshnessSlider\" was not injected: check your FXML file 'form.fxml'.";
        assert negativeCheckMenuItem != null : "fx:id=\"negativeCheckMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert cancelChangesMenuItem != null : "fx:id=\"cancelChangesMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert binaryCheckBox != null : "fx:id=\"binaryCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert openFileMenuItem != null : "fx:id=\"openFileMenuItem\" was not injected: check your FXML file 'form.fxml'.";
        assert noiseSlider != null : "fx:id=\"noiseSlider\" was not injected: check your FXML file 'form.fxml'.";
        assert bicubicInterpolationSlider != null : "fx:id=\"bicubicInterpolationSlider\" was not injected: check your FXML file 'form.fxml'.";
        assert shadesOfGrayCheckBox != null : "fx:id=\"shadesOfGrayCheckBox\" was not injected: check your FXML file 'form.fxml'.";
        assert contrastSlider != null : "fx:id=\"contrastSlider\" was not injected: check your FXML file 'form.fxml'.";
        assert brightnessSlider != null : "fx:id=\"brightnessSlider\" was not injected: check your FXML file 'form.fxml'.";
        assert harshnessComboBox != null : "fx:id=\"harshnessComboBox\" was not injected: check your FXML file 'form.fxml'.";

        drawing = new Drawing(canvas.getGraphicsContext2D());

        //region Обработчики
        Handler<Color, Color> handlerColor, handlerNoise, handlerNegative, handlerBinary;
        handlerColor = value -> ColorCorrection.contrastCorrection((float) contrastSlider.getValue(),
                ColorCorrection.changeBrightness((float) brightnessSlider.getValue(),
                        shadesOfGrayCheckBox.isSelected()?ColorCorrection.convertToGray(value):value),
                buffer);
        handlerNoise = value -> noiseCheckBox.selectedProperty().getValue()?Effects.noise(value, (int)(100-noiseSlider.getValue())):value;
        handlerBinary = value -> binaryCheckBox.isSelected()?ColorCorrection.binary(value, buffer):value;
        handlerNegative = value -> negativeCheckBox.isSelected()?ColorCorrection.negative(value):value;
        this.handlerColor = value -> {
            Color tmp = handlerColor.process(value);
            tmp = handlerColor.process(handlerNegative.process(handlerBinary.process(handlerNoise.process(value))));
            return tmp;
        };
        Handler<ImageBuffer, ImageBuffer> handlerStamping = value -> stampingCheckBox.selectedProperty().getValue()?Effects.stamping(value, stampingCore):value;
        Handler<ImageBuffer, ImageBuffer> handlerFilter = value -> {
            if(medianFilterCheckBox.selectedProperty().getValue())
                return Effects.medianFilter(value, medianFilterComboBox.getValue());
            if(uniformFilterCheckBox.selectedProperty().getValue())
                return Effects.uniformFilter(value, uniformFilterComboBox.getValue());
            return value;
        };
        Handler<ImageBuffer, ImageBuffer> handlerHarshness = value -> harshnessCheckBox.selectedProperty().getValue()?
                Effects.harshness(value, harshnessSlider.getValue(), harshnessComboBox.getValue()):value;
        Handler<ImageBuffer, ImageBuffer> handlerZoom = value -> {
            if(Math.abs(nearestNeighborSlider.getValue()-100)>=0.5)
                return Zoom.nearestNeighbor(value, nearestNeighborSlider.getValue());
            if(Math.abs(bicubicInterpolationSlider.getValue()-100)>=0.5)
                return Zoom.bicubicInterpolation(value, bicubicInterpolationSlider.getValue());
            return value;
        };
        this.handlerImage = value -> handlerStamping.process(handlerFilter.process(handlerHarshness.process(handlerZoom.process(value))));
        //endregion

        //region Цветокоррекция
        //Слайдер яркости
        brightnessSlider.setMax(1);
        brightnessSlider.setMin(-1);

        //Слайдер контрастности
        contrastSlider.setMax(10);
        contrastSlider.setMin(0.001);

        brightnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        contrastSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        shadesOfGrayCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            shadesOfGrayCheckMenuItem.setSelected(newValue);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        shadesOfGrayCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            shadesOfGrayCheckBox.setSelected(newValue);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        binaryCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            binaryCheckMenuItem.setSelected(newValue);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        negativeCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            negativeCheckMenuItem.setSelected(newValue);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        negativeCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            negativeCheckBox.setSelected(newValue);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        shadesOfGrayCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            shadesOfGrayCheckBox.setSelected(newValue);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //endregion

        //region Эффекты
        //region Шум
        noiseCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            noiseCheckMenuItem.selectedProperty().setValue(newValue);
            if(buffer!=null)
                return;
            try {
                drawing.draw(buffer, handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        noiseCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            noiseCheckBox.selectedProperty().setValue(newValue);
            if(buffer!=null)
                return;
            try {
                drawing.draw(buffer, handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        noiseSlider.setMin(0);
        noiseSlider.setMax(90);
        noiseSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //endregion
        //region Медиальное сглаживание
        medianFilterCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            medianFilterCheckMenuItem.selectedProperty().setValue(newValue);
            uniformFilterCheckBox.selectedProperty().setValue(false);
            uniformFilterCheckMenuItem.selectedProperty().setValue(false);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        medianFilterCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            medianFilterCheckBox.selectedProperty().setValue(newValue);
            uniformFilterCheckBox.selectedProperty().setValue(false);
            uniformFilterCheckMenuItem.selectedProperty().setValue(false);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        medianFilterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        medianFilterComboBox.getItems().addAll(3,5,7,9,11);
        medianFilterComboBox.getSelectionModel().select(0);
        //endregion
        //region Равномерное сглаживание
        uniformFilterCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            uniformFilterCheckMenuItem.selectedProperty().setValue(newValue);
            medianFilterCheckBox.selectedProperty().setValue(false);
            medianFilterCheckMenuItem.selectedProperty().setValue(false);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        uniformFilterCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            uniformFilterCheckBox.selectedProperty().setValue(newValue);
            medianFilterCheckBox.selectedProperty().setValue(false);
            medianFilterCheckMenuItem.selectedProperty().setValue(false);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        uniformFilterComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        uniformFilterComboBox.getItems().addAll(3,5,7,9,11);
        uniformFilterComboBox.getSelectionModel().select(0);
        //endregion
        //region Тиснение
        stampingCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            stampingCheckMenuItem.selectedProperty().setValue(newValue);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        stampingCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            stampingCheckBox.selectedProperty().setValue(newValue);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //endregion
        //region Резкость
        harshnessCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            harshnessCheckMenuItem.selectedProperty().setValue(newValue);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        harshnessCheckMenuItem.selectedProperty().addListener((observable, oldValue, newValue) -> {
            harshnessCheckBox.selectedProperty().setValue(newValue);
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        harshnessComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(image == null)
                return;
            try {
                drawing.draw(buffer,handlerColor,handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        harshnessComboBox.getItems().addAll(3,5,7,9,11);
        harshnessComboBox.getSelectionModel().select(0);
        harshnessSlider.setMin(1);
        harshnessSlider.setMax(10);
        harshnessSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //endregion
        //endregion

        //region Масштабирование
        nearestNeighborSlider.setMin(0);
        nearestNeighborSlider.setMax(300);
        nearestNeighborSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            bicubicInterpolationSlider.setValue(100);
            if(buffer ==null)
                return;
            try {
                drawing.draw(buffer, this.handlerColor, handlerImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        bicubicInterpolationSlider.setMin(0);
        bicubicInterpolationSlider.setMax(300);
        bicubicInterpolationSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                nearestNeighborSlider.setValue(100);
                if(buffer==null)
                    return;
                try {
                    drawing.draw(buffer,handlerColor, handlerImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        //endregion
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    void openFileMenuItem_onAction(ActionEvent event) throws Exception {
        String file = new FileChooser().showOpenDialog(stage).getAbsolutePath();
        BufferedImage img1 = ImageIO.read(new FileInputStream(file));
        imagei = new ImageBufferI(img1);
        Image img = new Image("file:///" + file);
        image = new ImageBuffer(img);
        buffer = image.clone();
        bufferi = imagei.clone();
        setDefault();
        drawing.draw(buffer, handlerColor, handlerImage);
    }

    @FXML
    void saveChangesMenuItem_onAction(ActionEvent event) throws Exception {
        ColorCorrection.changeBrightness((float) brightnessSlider.getValue(), image);
        ColorCorrection.contrastCorrection((float) contrastSlider.getValue(), image);
        if(shadesOfGrayCheckBox.selectedProperty().getValue())
            ColorCorrection.convertToGray(image);
        if(noiseCheckBox.isSelected())Effects.noise(image,100-(int)noiseSlider.getValue());
        setDefault();
        buffer = image.clone();
        drawing.draw(buffer,handlerColor,handlerImage);
    }

    @FXML
    void cancelChangesMenuItem_onAction(ActionEvent event) throws Exception {
        buffer = image.clone();
        setDefault();
        drawing.draw(buffer, handlerColor, handlerImage);
    }

    Chart makeChart(){
        int[] colors = new int[256];
        ColorCorrection.contrastCorrection(255, bufferi);
        for(int i = 0; i < colors.length; i++)
            colors[i]=0;
        for(int i = 0; i < bufferi.getWidth(); i++)
            for(int j = 0; j < bufferi.getHeight(); j++) {
                int key = (int)(0.3*bufferi.getPixel(i, j).getRed() + 0.57*bufferi.getPixel(i, j).getGreen() + 0.13*bufferi.getPixel(i, j).getBlue());
                colors[key] += 1;
            }
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < colors.length; i++)
            if(colors[i]>0)
                series.getData().add(new XYChart.Data(new Integer(i).toString(), colors[i]));
        chart.getData().add(series);
        return chart;
    }

    @FXML
    void openChart(ActionEvent event){
        if(buffer == null)
            return;
        Popup window = new Popup();
        window.getContent().add(makeChart());
        window.show(stage);
    }

    private void setDefault(){
        brightnessSlider.setValue(0);
        contrastSlider.setValue(1);
        shadesOfGrayCheckBox.selectedProperty().setValue(false);
        noiseCheckBox.selectedProperty().setValue(false);
        noiseSlider.setValue(0);
        medianFilterCheckBox.selectedProperty().setValue(false);
        uniformFilterCheckBox.selectedProperty().setValue(false);
        nearestNeighborSlider.setValue(100);
        harshnessCheckBox.selectedProperty().setValue(false);
        harshnessSlider.setValue(0);
        stampingCheckBox.selectedProperty().setValue(false);
        binaryCheckBox.selectedProperty().setValue(false);
        negativeCheckBox.selectedProperty().setValue(false);
        nearestNeighborSlider.setValue(100);
    }

    int toInteger(double color){
        return (int)(255*((double) ((int)(color*100))/100));
    }
}
