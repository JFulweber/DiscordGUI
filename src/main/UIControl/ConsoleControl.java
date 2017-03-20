package main.UIControl;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.controllers.MainController;
import main.Main;
import net.dv8tion.jda.core.entities.Guild;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fulwejam000 on 3/8/2017.
 */
public class ConsoleControl {
    static MainController mainController = Main.mainController;

    public static void addLog(Guild guild, String s, Color c){
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        String log = "["+df.format(dateobj)+"]";
        log+= s;
        Text text = new Text(log);
        if(c!=null) {
            text.setFill(c);
        }
        else {
            text.setFill(Color.WHITE);
        }
        mainController.LOG(guild, text);
    }

}
