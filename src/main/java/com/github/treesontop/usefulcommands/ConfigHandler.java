package com.github.treesontop.usefulcommands;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class ConfigHandler {

    private static YamlConfiguration config;

    /* might use later
    public static File getData() {
        File file = new File(UsefulCommands.getMainClass().getDataFolder(), "data.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
        return file;
    }*/

    /*public static File getPlayerData(String uuid) {
        File path = new File(UsefulCommands.getMainClass().getDataFolder() + File.separator + "PlayerData");
        File file = new File(path, uuid + ".yml");
        if (!path.exists()) {
            path.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                // player data template
                InputStream inputStream = UsefulCommands.getMainClass().getResource("playerData.yml");
                // where the template is written to
                OutputStream outputStream = new FileOutputStream(file);
                int read;
                byte[] bytes = new byte[1024];
                // write the bytes
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (IOException ignored) {
            }
        }
        return file;
    }*/

    public static void reloadConfig() {
        File configFile = new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            UsefulCommands.getMainClass().saveResource("config.yml", true);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
