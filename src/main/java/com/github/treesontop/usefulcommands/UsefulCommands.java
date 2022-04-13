package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.material.EnderChest;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class UsefulCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        mainClass = this;
        Bukkit.getConsoleSender().sendMessage("UsefulCommands started");
        if (String.valueOf(Bukkit.getServer()).contains("Spigot") && !String.valueOf(Bukkit.getServer()).contains("Paper")) {
            Bukkit.getConsoleSender().sendMessage("§cWarning");
            Bukkit.getConsoleSender().sendMessage("§cSpigot is not supported by UsefulCommands and may break");
            Bukkit.getConsoleSender().sendMessage("§cParts of UsefulCommands may work but it's not guaranteed");
            Bukkit.getConsoleSender().sendMessage("§cTo make guarantee compatibility you should switch to paper at");
            Bukkit.getConsoleSender().sendMessage("§chttps://papermc.io/");
            Bukkit.getConsoleSender().sendMessage("§cWarning");
        }
        ConfigHandler.reloadConfig();
        this.getCommand("fly").setExecutor(new Fly());
        this.getCommand("reload").setExecutor(new Reload());
        this.getCommand("broadcast").setExecutor(new Broadcast());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("feed").setExecutor(new Feed());
        this.getCommand("clearchat").setExecutor(new ClearChat());
        this.getCommand("enderchest").setExecutor(new Enderchest());
        Permissions.register();
    }
    private static UsefulCommands mainClass;
    public static UsefulCommands getMainClass() { return mainClass; }
}
