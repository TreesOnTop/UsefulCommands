package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UsefulCommands extends JavaPlugin {

    private static UsefulCommands mainClass;

    public static UsefulCommands getMainClass() {
        return mainClass;
    }
    @Override
    public void onEnable() {
        mainClass = this;
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
