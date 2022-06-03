package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class UsefulCommands extends JavaPlugin {

    private static UsefulCommands mainClass;

    public static UsefulCommands getMainClass() {
        return mainClass;
    }

    @Override
    public void onEnable() {
        Logger log = this.getLogger();
        mainClass = this;
        if (String.valueOf(Bukkit.getServer()).contains("Spigot") && !String.valueOf(Bukkit.getServer()).contains("Paper")) {
            log.warning("Warning");
            log.warning("Spigot is not supported by UsefulCommands and may break");
            log.warning("Parts of UsefulCommands may work but it's not guaranteed");
            log.warning("To make guarantee compatibility you should switch to paper at");
            log.warning("https://papermc.io/");
            log.warning("Warning");
        }
        ConfigHandler.reloadConfig();
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("reload").setExecutor(new Reload());
        this.getCommand("broadcast").setExecutor(new Broadcast());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("feed").setExecutor(new Feed());
        this.getCommand("clearchat").setExecutor(new ClearChat());
        this.getCommand("enderchest").setExecutor(new Enderchest());
        this.getCommand("repair").setExecutor(new Repair());
        this.getCommand("suicide").setExecutor(new Suicide());
        this.getCommand("speed").setExecutor(new Speed());
        Bukkit.getConsoleSender().sendMessage("UsefulCommands started");
    }
}
