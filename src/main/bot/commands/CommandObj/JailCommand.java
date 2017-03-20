package main.bot.commands.CommandObj;

import main.bot.commands.Command;
import main.bot.commands.CommandInterpret.CommandContainer;

/**
 * Created by Adair on 03/17/17.
 */
public class JailCommand implements Command{
    @Override
    public boolean safe(CommandContainer info) {
        if(info.raw.getMentionedUsers().size()==0) return false;
        return true;
    }

    @Override
    public void action(CommandContainer info) {

    }

    @Override
    public String help() {
        return null;
    }
}
