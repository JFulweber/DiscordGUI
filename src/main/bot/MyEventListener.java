package main.bot;

import main.Main;
import main.Random;
import main.UIControl.ConsoleControl;
import main.config.Configuration;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

/**
 * Created by fulwejam000 on 3/8/2017.
 */
public class MyEventListener implements EventListener {
    @Override
    public void onEvent(Event event) {
        if(event instanceof GuildMessageReceivedEvent){
            String log = "";
            log = ((GuildMessageReceivedEvent) event).getAuthor().getName() +": "+((GuildMessageReceivedEvent) event).getMessage().getContent();
            ConsoleControl.addLog(((GuildMessageReceivedEvent) event).getGuild(),log);
        }
        if(event instanceof ReadyEvent){
            Main.controller.addAllTabs();
            for(Guild guild:Main.jda.getGuilds()){
                guild.getController().setNickname(Random.botMember(guild),Configuration.getProp("botName")).queue();
            }
            // add localized guild bot name change
        }
        if(event instanceof GuildJoinEvent){
            Main.controller.addNewTab(((GuildJoinEvent) event).getGuild());
        }
    }
}
