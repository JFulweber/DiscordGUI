package main.bot.commands.CommandInterpret;

import main.config.Configuration;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.util.ArrayList;

/**
 * Created by Adair on 03/16/17.
 */
public class CommandParser {
    public static CommandContainer parse(GuildMessageReceivedEvent event){
        Message raw = event.getMessage();
        String cut = raw.getContent().replace(Configuration.getProp("Prefix"),"");
        String invoke = cut.split(" ")[0];
        ArrayList<String> argsList = new ArrayList<>();
        String args = "";
        for(int i = 1; i<cut.split(" ").length;i++){
            argsList.add(cut.split(" ")[i]);
            args+=cut.split(" ")[i]+" ";
        }
        return new CommandContainer(raw, invoke, argsList, args.trim(), event);
    }


}
