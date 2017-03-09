package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        if(controller.setupConfig()){
            jda = new JDABuilder(AccountType.BOT).setToken(Configuration.getProp("token")).buildAsync();
            jda.addEventListener(new MyEventListener());
        }
        else{
            System.out.println("Creating properties file");
        }
    }

    public static Controller getController(){
        return controller;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
