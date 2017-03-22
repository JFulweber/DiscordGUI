package main.bot.user_info;

import main.bot.commands.Command;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;

import java.util.HashMap;

/**
 * Created by Adair on 03/19/17.
 */
public class UserWrapper {
    private Member member;
    private String userId;
    private Guild guild;
    private String guildId;
    private Role role;
    private String roleId;
    private HashMap<Command, Boolean> permissions;

    public UserWrapper(Member member) {
        this.member = member;
        this.userId = member.getUser().getId();
        this.guild = member.getGuild();
        this.guildId = guild.getId();
        this.role = UserRole.findRole(this);
        this.roleId = role.getId();
    }

    public String getUserId() {
        return userId;
    }

    public Role getRole() {
        return role;
    }

    public HashMap<Command, Boolean> getPermissions() {
        return permissions;
    }

    public Member getMember() {
        return member;
    }

    public Guild getGuild() {
        return guild;
    }

    public String getGuildId() {
        return guildId;
    }

    public String getRoleId() {
        return roleId;
    }
}
