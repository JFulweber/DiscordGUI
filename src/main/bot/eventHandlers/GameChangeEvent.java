package main.bot.eventHandlers;

import javafx.scene.paint.Color;
import main.UIControl.ConsoleControl;
import main.config.Configuration;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.user.UserGameUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Adair on 03/17/17.
 */
public class GameChangeEvent extends ListenerAdapter{

    String genChanPrefix = Configuration.getProp("Generated Channel Prefix");

    @Override
    public void onUserGameUpdate(UserGameUpdateEvent event) {
        Guild guild = event.getGuild();
        HashMap<Game, Integer> games = new HashMap<>();
        for(Member mem: guild.getMembers()){
            if(games.containsKey(mem.getGame())){
                games.put(mem.getGame(),games.get(mem.getGame())+1);
            }
            else{
                games.put(mem.getGame(),1);
            }
        }
        // delete channel prior to remaking it, too lazy to do something more efficiently
        for(VoiceChannel channel: guild.getVoiceChannels()){
            if(channel.getName().indexOf(genChanPrefix)==0) {
                GuildVoiceLeave.delete.add(channel);
            }
        }
        // create new channels
        for(Game game:games.keySet()){
            if(game==null) continue;
            if(games.get(game).intValue()>=2){
                if(guild.getVoiceChannelsByName(genChanPrefix+game.getName(), true).size()==0){
                    guild.getController().createVoiceChannel(genChanPrefix+game.getName()).queue();
                    ConsoleControl.addLog(event.getGuild(),"[CHANNEL] Creating channel: " + genChanPrefix+game.getName(), Color.YELLOW);
                }
                else if(guild.getVoiceChannelsByName(genChanPrefix+game.getName(), true).size()==1){
                    GuildVoiceLeave.delete.remove(guild.getVoiceChannelsByName(genChanPrefix+game.getName(), true).get(0));
                }
            }
        }
    }
}
