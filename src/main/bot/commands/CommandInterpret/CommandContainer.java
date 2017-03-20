package main.bot.CommandInterpret;

import net.dv8tion.jda.core.events.Event;

import java.util.ArrayList;

/**
 * Created by Adair on 03/16/17.
 */
public class CommandContainer{
    private String raw;
    private String invoke;
    private ArrayList<String> argsList;
    private String args;
    private Event event;

    public CommandContainer(String raw, String invoke, ArrayList<String> argsList, String args, Event event) {
        this.raw = raw;
        this.invoke = invoke;
        this.argsList = argsList;
        this.args = args;
        this.event = event;
    }
}