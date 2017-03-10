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

        propertyHash.put("token","set token");
        propertyHash.put("prefix","-");
        propertyHash.put("customRolePrefix","-");
        propertyHash.put("generatedChannelPrefix","A:");
        propertyHash.put("jailRoleName","gay jail");
        propertyHash.put("botName","Lemon Stealing Whore");

        if(!new File("config.properties").exists()){
            try{
                for(String key: propertyHash.keySet()){
                    propertiesObj.setProperty(key,propertyHash.get(key));
                    System.out.println("Setting key \""+key+"\" to "+propertyHash.get(key));
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
                String token = propertiesObj.getProperty("token");
                if (!(token.equals("set token") || token.length() == 0)) return true;
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