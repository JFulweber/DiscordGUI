package gui_discord;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import gui_discord.config.Configuration;
import net.dv8tion.jda.core.entities.Guild;

import java.util.HashMap;


public class Controller{
    //CONSOLE
    @FXML
    TextFlow consoleFlow;
    @FXML
    Button clearButton;
    @FXML
    Tab baseConsoleTab;
    @FXML
    TabPane consoleTabs;
    //CONFIG
    @FXML
    TextField tokenTextBox;
    @FXML
    TextField nameTextBox;

    public HashMap<Guild, TextFlow> guildToTextFlow = new HashMap<>();

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

    public void LOG(Guild guild, Text text){
        Platform.runLater(()->{
            System.out.println(guildToTextFlow.get(guild).getChildren());
            if(guildToTextFlow.get(guild).getChildren().size()!=0)text.setText(text.getText()+"\n");
            guildToTextFlow.get(guild).getChildren().add(0,text);
        });
    }

    public void clear(){
        Platform.runLater(()->((TextFlow)((ScrollPane)consoleTabs.getSelectionModel().getSelectedItem().getContent()).getChildrenUnmodifiable().get(0)).getChildren().clear());
    }

    public void addAllTabs(){
        for(Guild guild:Main.jda.getGuilds()){
            Tab tab = new Tab();
            TextFlow guildFlow = new TextFlow();
            guildToTextFlow.put(guild,guildFlow);
            tab.setContent(new ScrollPane(guildFlow));
            tab.setText(guild.getName());
            Platform.runLater(()->{
                consoleTabs.getTabs().add(tab);
            });
        }
    }
}
