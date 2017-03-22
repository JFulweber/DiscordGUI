package main.bot.user_info;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

/**
 * Created by fulwejam000 on 3/21/2017.
 */
public class UserRole {
    public static Role createRole(Member member){
        Role role = member.getGuild().getController().createRole().complete();
        role.getManager().setName(member.getEffectiveName()).queue();
        member.getGuild().getController().addRolesToMember(member, role).queue();
        return role;
    }

    public static Role findRole(UserWrapper wrapper){
        Member member = wrapper.getMember();
        for(Role role:member.getRoles()){
            if(role.getId()==wrapper.getRoleId()){
                System.out.println(wrapper.getRoleId());
                return role;
            }
        }
        return createRole(member);
    }
}
