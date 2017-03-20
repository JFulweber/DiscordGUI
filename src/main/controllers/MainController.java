package main.controllers;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import main.bot.JDAController;
import main.config.Configuration;
import net.dv8tion.jda.core.entities.Guild;

import java.io.IOException;
import java.util.HashMap;


public class MainController {

    //CONSOLE
    @FXML
    public TabPane upperTabs;
    @FXML
    Menu configMenu;

    public HashMap<Guild, TextFlow> guildToTextFlow = new HashMap<>();

    /*
     *
     * CONFIG METHODS
     *
     */

    public void openConfigStage() {
        System.out.println("open config stage");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/configScreen.fxml"));
        try {
            Parent configRoot = loader.load();
            ConfigController configController = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Global Bot Configuration");
            stage.setScene(new Scene(configRoot));
            stage.show();
            configController.setupConifgParts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     *
     * CONSOLE METHODS
     *
     */

    public void LOG(Guild guild, Text text){
        Platform.runLater(()->{
            if(guildToTextFlow.get(guild).getChildren().size()!=0)text.setText(text.getText()+"\n");
            guildToTextFlow.get(guild).getChildren().add(0,text);
        });
    }

    public void clear(){
        Platform.runLater(()-> {
            Tab tab = upperTabs.getSelectionModel().getSelectedItem();
            ScrollPane pane = ((ScrollPane) ((AnchorPane) tab.getContent()).getChildren().get(0));
            TextFlow flow = (TextFlow) ((AnchorPane) pane.getContent()).getChildren().get(0);
            flow.getChildren().clear();
        });
    }

    public void addNewTab(Guild guild){
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/consoleTab.fxml"));
       try {
           Tab tab = loader.load();
           tab.setText(guild.getName());
           HBox hbox = new HBox();
           Button button = new Button();
           Tooltip tooltip = new Tooltip();
           tooltip.setText("Clear the guild console");
           button.setTooltip(tooltip);
           ImageView view = new ImageView("/resources/img/clear-icon-9213.png");
           view.setFitHeight(17);
           view.setFitWidth(19);
           button.setStyle(
           "-fx-shadow-highlight-color : transparent;" +  // if you don't want a 3d effect highlight.
                   "-fx-outer-border : transparent;" +  // if you don't want a button border.
                   "-fx-inner-border : transparent;" +  // if you don't want a button border.
                   "-fx-focus-color: transparent;" +  // if you don't want any focus ring.
                   "-fx-faint-focus-color : transparent;" +  // if you don't want any focus ring.
                   "-fx-base : #e8ebef;" // if you want a gradient shaded button that lightens on hover and darkens on arming.
                   // "-fx-body-color: palegreen;" + // instead of -fx-base, if you want a flat shaded button that does not lighten on hover and darken on arming.
           );
           button.setGraphic(view);
           button.setOnAction((event -> {
               clear();
           }));
           tab.setGraphic(button);
           ScrollPane pane = ((ScrollPane) ((AnchorPane) tab.getContent()).getChildren().get(0));
           TextFlow fxidFlow = (TextFlow) loader.getNamespace().get("consoleFlow");
           guildToTextFlow.put(guild,fxidFlow);
           Platform.runLater(()->upperTabs.getTabs().add(tab));
       }
       catch(Exception e){
           e.printStackTrace();
       }
    }

    public void addAllTabs(){
        Platform.runLater(()->upperTabs.getTabs().clear());
        for(Guild guild:JDAController.jda.getGuilds()){
            addNewTab(guild);
        }
    }

    public void readyInitialize(){
        addAllTabs();
        Label label = new Label("Open Configuration");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                openConfigStage();
            }
        });
        Platform.runLater(()->{
            configMenu.setGraphic(label);
        });
    }
}
