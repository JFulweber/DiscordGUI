package main.config;

import java.io.*;
import java.util.HashMap;
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


    static Properties propertiesObj = new Properties();
    static OutputStream outputStream = null;

    public static HashMap<String, String> propertyHash = new HashMap<>();

    public static boolean setup(){

        propertyHash.put("Prefix","-");
        propertyHash.put("Custom Role Prefix","-");
        propertyHash.put("Generated Channel Prefix","A:");
        propertyHash.put("Jail Role Name","gay jail");
        propertyHash.put("Bot Name","Lemon Stealing Whore");
        propertyHash.put("Bot Token","set token");

        if(!new File("config.properties").exists()){
            try{
                for(String key: propertyHash.keySet()){
                    propertiesObj.setProperty(key,propertyHash.get(key));
                }
                save();
                return false;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else {
            try {
                propertiesObj.load(new FileInputStream("config.properties"));
                String token = propertiesObj.getProperty("Bot Token");
                return !token.equals(propertyHash.get("Bot Token"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getProp(String property){
        if(propertyHash.containsKey(property)){
            return propertiesObj.getProperty(property);
        }
        return null;
    }

    public static void setProp(String property, String value){
        if(propertyHash.containsKey(property)){
            propertiesObj.setProperty(property, value);
        }
    }

    public static void save() {
        try {
            propertiesObj.store(new FileOutputStream("config.properties"),null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}