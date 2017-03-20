package main.bot.commands;

import main.bot.commands.CommandInterpret.CommandContainer;

/**
 * Created by Adair on 03/16/17.
 */
public interface Command {

    boolean safe(CommandContainer info);

    void action(CommandContainer info);

    String help();
}
