package gui_discord.bot;

import gui_discord.Main;
import gui_discord.UIControl.ConsoleControl;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

/**
 * Created by fulwejam000 on 3/8/2017.
 */
public class MyEventListener implements EventListener {
    @Override
    public void onEvent(Event event) {
        if(event instanceof GuildMessageReceivedEvent){
            ConsoleControl.addLog(((GuildMessageReceivedEvent) event).getGuild(),((GuildMessageReceivedEvent) event).getMessage().getContent());
        }
        if(event instanceof ReadyEvent){
            System.out.println("Ready!");
            System.out.println(Main.jda.getGuilds());
            Main.controller.addAllTabs();
        }
    }
}
