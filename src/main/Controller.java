package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.config.Configuration;
import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;


public class Controller{
    //CONSOLE
    @FXML
    Button clearButton;
    @FXML
    TabPane consoleTabs;
    //CONFIG
    @FXML
    VBox configVbox;
    @FXML
    TextField tokenTextBox,nameTextBox;
    @FXML
    Button saveButton, reloadButton;

    public HashMap<Guild, TextFlow> guildToTextFlow = new HashMap<>();

    /*
     *
     * CONFIG METHODS
     *
     */

    public void saveConfig(){
        for(Node vBox:configVbox.getChildren()){
            vBox = (VBox) vBox;
            TextField property = (TextField) ((VBox) vBox).getChildren().get(1);
            Label propName= (Label) ((VBox) vBox).getChildren().get(0);
            Configuration.setProp(propName.getText(),property.getText());
            Configuration.save();
        }
    }

    public void setupConifgParts(){
        Platform.runLater(()->configVbox.getChildren().clear());
        for(String prop: Configuration.propertyHash.keySet()){
            Label label = new Label();
            TextField field = new TextField();
            VBox vbox = new VBox();
            vbox.setAlignment(configVbox.getAlignment());
            vbox.getChildren().addAll(label,field);
            field.setText(Configuration.getProp(prop));
            field.setPrefWidth(configVbox.getWidth()/1.25);
            label.setText(prop);
            vbox.setSpacing(10);
            Platform.runLater(()->{
                configVbox.getChildren().add(vbox);
            });
        }
    }

    public void resetJDA(){
        System.out.println("resetting jda");
        saveConfig();
        Main.jda.shutdown(false);
        Main.startJDA();
    }
    /*
     *
     * CONSOLE METHODS
     *
     */

    public void LOG(Guild guild, Text text){
        Platform.runLater(()->{
            if(guildToTextFlow.get(guild).getChildren().size()!=0)text.setText(text.getText()+"\n");
            if(text.getText().contains(Configuration.getProp("prefix")));
            guildToTextFlow.get(guild).getChildren().add(0,text);
        });
    }

    public void clear(){
        Platform.runLater(()-> {
            Tab tab = consoleTabs.getSelectionModel().getSelectedItem();
            ScrollPane pane = (ScrollPane) tab.getContent();
            TextFlow flow = (TextFlow) pane.getContent();
            flow.getChildren().clear();
        });
    }
    public void addNewTab(Guild guild){
        Tab tab = new Tab();
        TextFlow guildFlow = new TextFlow();
        guildToTextFlow.put(guild,guildFlow);
        tab.setContent(new ScrollPane(guildFlow));
        tab.setText(guild.getName());
        tab.setClosable(false);
        Platform.runLater(()->{
            consoleTabs.getTabs().add(tab);
        });
    }

    public void addAllTabs(){
        Platform.runLater(()->consoleTabs.getTabs().clear());
        for(Guild guild:Main.jda.getGuilds()){
            addNewTab(guild);
        }
    }
}
