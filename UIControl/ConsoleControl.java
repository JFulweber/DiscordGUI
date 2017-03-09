package main.UIControl;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.Controller;
import main.Main;
import net.dv8tion.jda.core.entities.Guild;

import java.awt.*;

/**
 * Created by fulwejam000 on 3/8/2017.
 */
public class ConsoleControl {
    static Controller controller = Main.getController();

    public static void addLog(Guild guild, String s){
        controller.LOG(guild, new Text(s));
    }

}
