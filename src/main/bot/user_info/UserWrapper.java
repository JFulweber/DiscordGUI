package main.bot.user_info;

import main.bot.commands.Command;
import net.dv8tion.jda.core.entities.Role;

import java.util.HashMap;

/**
 * Created by Adair on 03/19/17.
 */
public class UserWrapper {
    private String userId;
    private Role role;
    private HashMap<Command, Boolean> permissions;

    public UserWrapper(String userId /*Role roleHashMap<Command, Boolean> permissions*/) {
        this.userId = userId;
        //this.role = role;
        //this.permissions = permissions;
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
}
