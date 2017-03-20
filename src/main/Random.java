package main;

import main.bot.JDAController;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;

/**
 * Created by fulwejam000 on 3/9/2017.
 */
public class Random {
    public static Member botMember(Guild guild){
        Member bot = null;
        for(Member member:guild.getMembers()){
            if(member.getUser().getId()== JDAController.jda.getSelfUser().getId()) bot = member;
        }
        return bot;
    }
}
