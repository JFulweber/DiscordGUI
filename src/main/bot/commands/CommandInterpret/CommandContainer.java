package main.bot.commands.CommandInterpret;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.Event;

import java.util.ArrayList;

/**
 * Created by Adair on 03/16/17.
 */
public class CommandContainer{
    public Message raw;
    public String invoke;
    public ArrayList<String> argsList;
    public String args;
    public Event event;

    public CommandContainer(Message raw, String invoke, ArrayList<String> argsList, String args, Event event) {
        this.raw = raw;
        this.invoke = invoke;
        this.argsList = argsList;
        this.args = args;
        this.event = event;
    }
}