package main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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

    public boolean setupConfig(){
        if(Configuration.setup()){
            Platform.runLater(()->{
                tokenTextBox.setText(Configuration.getProp("token"));
                nameTextBox.setText(Configuration.getProp("botName"));
            });
            return true;
        }
        return false;
    }

    public void saveConfig(){

    }

    public void setupConifgParts(){
        for(String prop: Configuration.properties){
            Label label = new Label();
            TextField field = new TextField();
            VBox vbox = new VBox();
            vbox.getChildren().addAll(label,field);
            field.setPromptText("Put "+prop);
            label.setText(prop);
            Platform.runLater(()->{
                configVbox.getChildren().add(vbox);
            });
        }
    }
    /*
     *
     * CONSOLE METHODS
     *
     */

    public void LOG(Guild guild, Text text){
        Platform.runLater(()->{
            System.out.println(guildToTextFlow.get(guild).getChildren());
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

    public void addAllTabs(){
        for(Guild guild:Main.jda.getGuilds()){
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
    }
}
