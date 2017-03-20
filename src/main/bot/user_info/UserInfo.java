package main.bot.user_info;

import main.bot.JDAController;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.User;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Adair on 03/19/17.
 */
public class UserInfo {
    public static void loadInfo(){
        for(Guild guild:JDAController.jda.getGuilds()){
            for(Member member:guild.getMembers()){
                UserWrapper wrapper = new UserWrapper(member.getUser().getId());
                JSONObject obj = new JSONObject();
                /*obj.append("id",wrapper.getUserId());
                try{
                    FileWriter writer = new FileWriter("/users/"+wrapper.getUserId());
                    obj.write(writer);
                }
                catch(Exception e){
                    e.printStackTrace();
                }*/
            }
        }
    }

    public static void loadSpecific(User user){
        UserWrapper wrapper = new UserWrapper(user.getId());
        File file = new File("/users/"+wrapper.getUserId()+".json");
        file.getParentFile().mkdir();
        JSONObject obj = new JSONObject();
        obj.put("id",wrapper.getUserId());
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream("/users/"+wrapper.getUserId()+".json");
            outputStream.write(obj.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
