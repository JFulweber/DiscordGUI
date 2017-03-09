package main.config;

import main.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by Adair on 02/06/17.
 */
public class Configuration {
    static String token;
    static String prefix;
    static String customRolePrefix;
    static String jailRoleName;
    static String generatedChannelPrefix;
    static String botName;


    static Properties prop = new Properties();
    static OutputStream outputStream = null;

    public static ArrayList<String> properties = new ArrayList<>();

    public static boolean setup(){
        properties.add("token");
        properties.add("prefix");
        properties.add("customRolePrefix");
        properties.add("generatedChannelPrefix");
        properties.add("jailRoleName");
        properties.add("botName");
        if(!new File("config.properties").exists()){
            try{
                outputStream = new FileOutputStream("config.properties");
                prop.setProperty("token", ""); //MjIxODU3NDM2NTk0NzMzMDU2.C3_PEw.HgByFJW2iPVNV_M3nVLPQrhtFiI
                prop.setProperty("prefix", "-");
                prop.setProperty("customRolePrefix","~");
                prop.setProperty("generatedChannelPrefix","A:");
                prop.setProperty("jailRoleName","jail");
                prop.setProperty("botName","lemon stealing whore");
                prop.store(outputStream, null);
                return false;
            }catch(Exception e){
                System.out.println("in config setup");
                e.printStackTrace();
            }
        }
        else {
            try {
                prop.load(new FileInputStream("config.properties"));
                token = ((String) prop.get("token"));
                if(token.length()==0) {
                    return false;
                }
                botName = (String) prop.get("botName");
                prefix = (String) prop.get("prefix");
                customRolePrefix = (String) prop.get("customRolePrefix");
                jailRoleName = (String) prop.get("jailRoleName");
                generatedChannelPrefix = (String) prop.get("generatedChannelPrefix");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getProp(String property){
        if(properties.contains(property)){
            return prop.getProperty(property);
        }
        return null;
    }
}