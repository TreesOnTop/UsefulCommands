package com.github.treesontop.usefulcommands;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigHandler {
    private static YamlConfiguration config;
    private static YamlConfiguration data;

    public static void readData() {
        File file = new File(UsefulCommands.getMainClass().getDataFolder(), "data.yml");
        if (!file.exists()) {
            UsefulCommands.getMainClass().saveResource("data.yml", true);
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveData(YamlConfiguration data) {
        try {
            data.save(new File(UsefulCommands.getMainClass().getDataFolder(), "data.yml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reloadConfig() {
        File configFile = new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            UsefulCommands.getMainClass().saveResource("config.yml", true);
            configFile = new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static YamlConfiguration getData() {
        return data;
    }
}
