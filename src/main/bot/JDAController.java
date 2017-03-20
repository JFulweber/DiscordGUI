package main.bot;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import main.Main;
import main.Random;
import main.bot.eventHandlers.GameChangeEvent;
import main.bot.eventHandlers.GuildVoiceLeave;
import main.bot.eventHandlers.MyEventListener;
import main.config.Configuration;
import main.controllers.MainController;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;

/**
 * Created by Adair on 03/16/17.
 */
public class JDAController {
    public static JDA jda;
    static MainController controller = Main.mainController;

    public static void startJDA(){
        if(Configuration.setup()){
            try {
                jda = new JDABuilder(AccountType.BOT).setToken(Configuration.getProp("Bot Token")).buildAsync();
                jda.addEventListener(new MyEventListener());
                jda.addEventListener(new GameChangeEvent());
                jda.addEventListener(new GuildVoiceLeave());
            } catch (LoginException e) {
                e.printStackTrace();
            } catch (RateLimitedException e) {
                e.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning: Set Token");
            alert.setHeaderText("Discord API Token Not Found");
            alert.setContentText("Please set your token either in the config.property file in the bot jar directory.");
            alert.showAndWait();
            System.out.println(controller.upperTabs.getTabs().get(1).getText());
            Platform.runLater(()->{
                controller.upperTabs.getSelectionModel().select(1);
            });
        }
    }

    public static void resetJDA(){
        System.out.println("resetting jda");
        //if(jda!=null) jda.shutdown(false);
        for(Guild guild:JDAController.jda.getGuilds()){
            guild.getController().setNickname(Random.botMember(guild),Configuration.getProp("Bot Name")).queue();
        }
        //startJDA();
    }
}
