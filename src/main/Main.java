package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.bot.JDAController;
import main.controllers.MainController;
import net.dv8tion.jda.core.JDA;

public class Main extends Application {
    public static JDA jda;
    public static MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/mainScene.fxml"));
        AnchorPane root = loader.load();
        mainController = (MainController) loader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        JDAController.startJDA();
    }

    public static MainController getMainController(){
        return mainController;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
