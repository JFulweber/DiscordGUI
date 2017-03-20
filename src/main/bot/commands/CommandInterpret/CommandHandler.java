package main.bot.commands.CommandInterpret;

import main.bot.commands.Command;
import main.bot.commands.CommandObj.PingCommand;

import java.util.HashMap;

/**
 * Created by Adair on 03/16/17.
 */
public class CommandHandler {

    public static HashMap<String,Command> commandHashMap = new HashMap<>();

    public static void handleCommand(CommandContainer info){

        commandHashMap.put("ping",new PingCommand());

        if(commandHashMap.containsKey(info.invoke)){
            if(commandHashMap.get(info.invoke).safe(info)) {
                commandHashMap.get(info.invoke).action(info);
            }
        }
        else{
            System.out.println("does not contain "+info.invoke);
        }
    }
}
