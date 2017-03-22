package main.bot.eventHandlers;

import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import main.Main;
import main.Random;
import main.UIControl.ConsoleControl;
import main.bot.JDAController;
import main.bot.commands.CommandInterpret.CommandHandler;
import main.bot.commands.CommandInterpret.CommandParser;
import main.bot.user_info.UserInfo;
import main.config.Configuration;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fulwejam000 on 3/8/2017.
 */
public class MyEventListener implements EventListener {
    @Override
    public void onEvent(Event event) {
        if(event instanceof GuildMessageReceivedEvent){

            if(((GuildMessageReceivedEvent) event).getMessage().getContent().contains("uhhh")){
                UserInfo.loadSpecific(((GuildMessageReceivedEvent) event).getMember());
            }

            String log = null;
            if(((GuildMessageReceivedEvent) event).getMember().getNickname()==null){
                log = ((GuildMessageReceivedEvent) event).getMember().getEffectiveName()+": "+((GuildMessageReceivedEvent) event).getMessage().getContent();
            }
            else{
                log = ((GuildMessageReceivedEvent) event).getMember().getNickname() +": "+((GuildMessageReceivedEvent) event).getMessage().getContent();
            }
            Color color = null;
            if(isCommand(((GuildMessageReceivedEvent) event).getMessage().getContent())){
                color = Color.LIGHTSKYBLUE;
                CommandHandler.handleCommand(CommandParser.parse(((GuildMessageReceivedEvent) event)));
            }
            if(((GuildMessageReceivedEvent) event).getAuthor().getId().equals(JDAController.jda.getSelfUser().getId())) {
                color = Color.YELLOW;
            }
            ConsoleControl.addLog(((GuildMessageReceivedEvent) event).getGuild(),log,color);
        }
        if(event instanceof ReadyEvent){
            Main.mainController.readyInitialize();
        }
        if(event instanceof GuildJoinEvent){
            Main.mainController.addNewTab(((GuildJoinEvent) event).getGuild());
        }
    }

    public boolean isCommand(String msg){
        return msg.indexOf(Configuration.getProp("Prefix"))==0;
    }
}
