package main.bot.commands.CommandObj;

import main.bot.JDAController;
import main.bot.commands.Command;
import main.bot.commands.CommandInterpret.CommandContainer;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

/**
 * Created by Adair on 03/16/17.
 */
public class PingCommand implements Command{
    @Override
    public boolean safe(CommandContainer info) {
        return true;
    }

    @Override
    public void action(CommandContainer info) {
        ((GuildMessageReceivedEvent) info.event).getChannel().sendMessage("Pong!").queue();
    }

    @Override
    public String help() {
        return null;
    }
}
