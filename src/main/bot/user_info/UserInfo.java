package main.bot.user_info;

import main.bot.JDAController;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by Adair on 03/19/17.
 */
public class UserInfo {
    public static void loadInfo() {
        for (Guild guild : JDAController.jda.getGuilds()) {
            for (Member member : guild.getMembers()) {
                UserWrapper wrapper = new UserWrapper(member);
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

    public static void loadSpecific(Member member) {
        UserWrapper wrapper = new UserWrapper(member);
        Path folder = Paths.get(member.getGuild().getId());
        Path user = Paths.get(member.getGuild().getId() + "/" + wrapper.getUserId() + ".json");
        if (!Files.exists(folder)||!Files.exists(user)) {
            try {
                Files.createDirectory(folder);
                Files.createFile(user);
                JSONObject obj = new JSONObject();
                obj.put("userid",wrapper.getUserId());
                //obj.put("member",wrapper.getMember());
                //obj.put("guild",wrapper.getGuild());
                obj.put("guildid",wrapper.getGuildId());
                //obj.put("role",wrapper.getRole());
                obj.put("roleid",wrapper.getRoleId());
                FileWriter writer = new FileWriter(user.toFile());
                writer.write(obj.toJSONString());
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        else{
            try{
                Scanner scanner = new Scanner(new File(member.getGuild().getId()+"/"+member.getUser().getId()+".json"));
                String jsonString = "";
                while(scanner.hasNextLine()){
                    jsonString+=scanner.nextLine();
                }
                System.out.println("*** LOADING MEMBER "+wrapper.getMember().getEffectiveName()+"!");
                JSONObject obj = (JSONObject) new JSONParser().parse(jsonString);
                System.out.println(member.getGuild().getRoleById(obj.get("roleid").toString()).getName());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        //System.out.println(((Role) obj.get("role")).getName());
    }
}

