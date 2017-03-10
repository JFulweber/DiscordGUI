package main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.bot.MyEventListener;
import main.config.Configuration;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Main extends Application {
    public static JDA jda;
    public static Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        controller = (Controller) loader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        if(Configuration.setup()){
            startJDA();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning: Set Token");
            alert.setHeaderText("Discord API Token Not Found");
            alert.setContentText("Please set your token either in the config.property file in the bot jar directory.");
            alert.showAndWait();
            controller.setupConifgParts();
            Platform.runLater(()->controller.consoleTabs.getSelectionModel().select(1));
        }
    }

    public static Controller getController(){
        return controller;
    }

    public static void startJDA(){
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(Configuration.getProp("token")).buildAsync();
            jda.addEventListener(new MyEventListener());
            controller.setupConifgParts();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
