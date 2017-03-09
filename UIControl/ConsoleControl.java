package gui_discord.UIControl;

import javafx.scene.text.Text;
import gui_discord.Controller;
import gui_discord.Main;
import net.dv8tion.jda.core.entities.Guild;

/**
 * Created by fulwejam000 on 3/8/2017.
 */
public class ConsoleControl {
    static Controller controller = Main.getController();

    public static void addLog(Guild guild, String s){
        controller.LOG(guild, new Text(s));
    }

}
