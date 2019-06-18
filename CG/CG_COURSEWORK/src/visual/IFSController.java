package visual;

import algorithm.IF_2D;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IFSController {
    //region Элементы формы
    @FXML
    private TextField chanceF1;
    @FXML
    private TextField bYF4;
    @FXML
    private TextField bXF4;
    @FXML
    private TextField bYF3;
    @FXML
    private TextField bXF1;
    @FXML
    private TextField dXF4;
    @FXML
    private TextField dYF3;
    @FXML
    private TextField dYF4;
    @FXML
    private TextField bXF3;
    @FXML
    private TextField dYF1;
    @FXML
    private TextField bYF2;
    @FXML
    private TextField bXF2;
    @FXML
    private TextField dXF3;
    @FXML
    private TextField bYF1;
    @FXML
    private TextField dYF2;
    @FXML
    private TextField aXF4;
    @FXML
    private TextField aYF3;
    @FXML
    private TextField aXF3;
    @FXML
    private TextField aYF2;
    @FXML
    private TextField aYF4;
    @FXML
    private TextField aXF2;
    @FXML
    private TextField aYF1;
    @FXML
    private TextField aXF1;
    @FXML
    private TextField dXF1;
    @FXML
    private TextField chanceF4;
    @FXML
    private TextField chanceF3;
    @FXML
    private TextField chanceF2;
    @FXML
    private TextField dXF2;
    //endregion

    //Первое аффинное преобразование
    private IF_2D fun1;
    //Второе аффинное преобразование
    private IF_2D fun2;
    //Третье аффинное преобразование
    private IF_2D fun3;
    //Четвертое аффинное преобразование
    private IF_2D fun4;
    Stage stage;

    //Установить ссылки на афинные преобразования
    public void setFunctions(IF_2D fun1, IF_2D fun2, IF_2D fun3, IF_2D fun4){
        this.fun1 = fun1;
        this.fun2 = fun2;
        this.fun3 = fun3;
        this.fun4 = fun4;
        refreshFields();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    //Событие кнопки "Принять"
    @FXML
    private void accept(ActionEvent event) {
        try {
            fun1.setaX(Double.parseDouble(aXF1.getText()));
            fun1.setbX(Double.parseDouble(bXF1.getText()));
            fun1.setdX(Double.parseDouble(dXF1.getText()));
            fun1.setaY(Double.parseDouble(aYF1.getText()));
            fun1.setbY(Double.parseDouble(bYF1.getText()));
            fun1.setdY(Double.parseDouble(dYF1.getText()));
            fun1.setChance(Double.parseDouble(chanceF1.getText()));
            fun2.setaX(Double.parseDouble(aXF2.getText()));
            fun2.setbX(Double.parseDouble(bXF2.getText()));
            fun2.setdX(Double.parseDouble(dXF2.getText()));
            fun2.setaY(Double.parseDouble(aYF2.getText()));
            fun2.setbY(Double.parseDouble(bYF2.getText()));
            fun2.setdY(Double.parseDouble(dYF2.getText()));
            fun2.setChance(Double.parseDouble(chanceF2.getText()));
            fun3.setaX(Double.parseDouble(aXF3.getText()));
            fun3.setbX(Double.parseDouble(bXF3.getText()));
            fun3.setdX(Double.parseDouble(dXF3.getText()));
            fun3.setaY(Double.parseDouble(aYF3.getText()));
            fun3.setbY(Double.parseDouble(bYF3.getText()));
            fun3.setdY(Double.parseDouble(dYF3.getText()));
            fun3.setChance(Double.parseDouble(chanceF3.getText()));
            fun4.setaX(Double.parseDouble(aXF4.getText()));
            fun4.setbX(Double.parseDouble(bXF4.getText()));
            fun4.setdX(Double.parseDouble(dXF4.getText()));
            fun4.setaY(Double.parseDouble(aYF4.getText()));
            fun4.setbY(Double.parseDouble(bYF4.getText()));
            fun4.setdY(Double.parseDouble(dYF4.getText()));
            fun4.setChance(Double.parseDouble(chanceF4.getText()));
            if (fun1.getChance() + fun2.getChance() + fun3.getChance() + fun4.getChance() > 1)
                throw new Exception("Неверно указаны вероятности. В сумме больше 1");
            stage.close();
        }
        catch (Exception e){
            Dialogs.showErrorDialog(stage, e.getMessage());
        }
    }

    //Событие кнопки "Отменить"
    @FXML
    private void cancel(ActionEvent event) {
        stage.close();
    }

    //Установка значений полей окна в соответсвии со значениями аффинных преобразований
    private void refreshFields(){
        if(fun1 != null){
            aXF1.setText(new Double(fun1.getaX()).toString());
            bXF1.setText(new Double(fun1.getbX()).toString());
            dXF1.setText(new Double(fun1.getdX()).toString());
            aYF1.setText(new Double(fun1.getaY()).toString());
            bYF1.setText(new Double(fun1.getbY()).toString());
            dYF1.setText(new Double(fun1.getdY()).toString());
            chanceF1.setText(new Double(fun1.getChance()).toString());
        }
        if(fun2 != null){
            aXF2.setText(new Double(fun2.getaX()).toString());
            bXF2.setText(new Double(fun2.getbX()).toString());
            dXF2.setText(new Double(fun2.getdX()).toString());
            aYF2.setText(new Double(fun2.getaY()).toString());
            bYF2.setText(new Double(fun2.getbY()).toString());
            dYF2.setText(new Double(fun2.getdY()).toString());
            chanceF2.setText(new Double(fun2.getChance()).toString());
        }
        if(fun3 != null){
            aXF3.setText(new Double(fun3.getaX()).toString());
            bXF3.setText(new Double(fun3.getbX()).toString());
            dXF3.setText(new Double(fun3.getdX()).toString());
            aYF3.setText(new Double(fun3.getaY()).toString());
            bYF3.setText(new Double(fun3.getbY()).toString());
            dYF3.setText(new Double(fun3.getdY()).toString());
            chanceF3.setText(new Double(fun3.getChance()).toString());
        }
        if(fun4 != null){
            aXF4.setText(new Double(fun4.getaX()).toString());
            bXF4.setText(new Double(fun4.getbX()).toString());
            dXF4.setText(new Double(fun4.getdX()).toString());
            aYF4.setText(new Double(fun4.getaY()).toString());
            bYF4.setText(new Double(fun4.getbY()).toString());
            dYF4.setText(new Double(fun4.getdY()).toString());
            chanceF4.setText(new Double(fun4.getChance()).toString());
        }
    }
}
