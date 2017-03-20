package main.bot.eventHandlers;

import javafx.scene.paint.Color;
import main.UIControl.ConsoleControl;
import main.config.Configuration;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.core.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

/**
 * Created by Adair on 03/19/17.
 */
public class GuildVoiceLeave extends ListenerAdapter {

    public static ArrayList<VoiceChannel> delete = new ArrayList<>();

    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
        System.out.println("channel leave");
        VoiceChannel channel = event.getChannelLeft();
        if(channel.getName().contains(Configuration.getProp("Generated Channel Prefix")) && !(channel.getMembers().size()==0)){
            ConsoleControl.addLog(event.getGuild(),"[CHANNEL] Removing channel: "+channel.getName(), Color.YELLOW);
            channel.delete().queue();
        }

    }

    @Override
    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
        System.out.println("channel move");
        VoiceChannel channel = event.getChannelLeft();
        if(channel.getName().contains(Configuration.getProp("Generated Channel Prefix")) && !(channel.getMembers().size()==0)){
            ConsoleControl.addLog(event.getGuild(),"[CHANNEL] Removing channel: "+channel.getName(), Color.YELLOW);
            channel.delete().queue();
        }
    }
}
