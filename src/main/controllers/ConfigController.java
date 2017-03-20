package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.bot.JDAController;
import main.config.Configuration;

import javax.xml.soap.Text;

import static main.config.Configuration.propertyHash;

/**
 * Created by Adair on 03/16/17.
 */
public class ConfigController {

    @FXML
    VBox configVbox;
    @FXML
    Button closeButton;

    GridPane gridPane = new GridPane();

    public void setupConifgParts(){
        gridPane.setPadding(new Insets(45,15,45,15));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setHgap(20);
        int index = 0;
        for(String prop: propertyHash.keySet()){
            Label label = new Label(prop);
            TextField field = new TextField();
            field.setPromptText("Set "+prop);
            field.setText(Configuration.getProp(prop));
            gridPane.add(label, 0, index);
            gridPane.add(field, 1, index);
            index++;
        }
        Platform.runLater(()->{
            configVbox.getChildren().clear();
            configVbox.getChildren().add(gridPane);
            configVbox.setAlignment(Pos.CENTER);
        });
    }

    public void saveConfig(){
        for(int i = 0;i<Configuration.propertyHash.keySet().size();i++){
            String prop = ((Label) getNodeFromGridPane(i, 0)).getText();
            String value = ((TextField) getNodeFromGridPane(i, 1)).getText();
            Configuration.setProp(prop, value);
        }
        Configuration.save();
    }

    private Node getNodeFromGridPane(int row, int col){
        for(Node child: gridPane.getChildren()){
            if(GridPane.getColumnIndex(child) == col && GridPane.getRowIndex(child) == row) return child;
        }
        return null;
    }

    public void closeWindow(){
        Stage stage = ((Stage) closeButton.getScene().getWindow());
        JDAController.resetJDA();
        stage.hide();
    }

}
