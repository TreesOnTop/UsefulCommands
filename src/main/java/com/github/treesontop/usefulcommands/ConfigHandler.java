package com.github.treesontop.usefulcommands;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {
    public static File getData(){
        File file = new File(UsefulCommands.getMainClass().getDataFolder(), "data.yml");
        if (!file.exists()){
            UsefulCommands.getMainClass().saveResource("data.yml", true);
        }
        return file;
    }

    public static void save(YamlConfiguration config){
        try{
            config.save(new File(UsefulCommands.getMainClass().getDataFolder(), "data.yml"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void reloadConfig(){
        File configFile = new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml");
        if(!configFile.exists()){
            UsefulCommands.getMainClass().saveResource("config.yml", true);
            configFile = new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }
    private static YamlConfiguration config;
    public static YamlConfiguration getConfig() { return config; }
}
