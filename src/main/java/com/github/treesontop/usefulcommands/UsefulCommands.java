package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import com.github.treesontop.usefulcommands.events.AsyncPlayerChat;
import com.github.treesontop.usefulcommands.events.PlayerDisconnect;
import com.github.treesontop.usefulcommands.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class UsefulCommands extends JavaPlugin {

    public static final HashMap<String, File> Players = new HashMap<>();
    private static UsefulCommands mainClass;

    public static UsefulCommands getMainClass() {
        return mainClass;
    }

    @Override
    public void onEnable() {
        mainClass = this;
        ConfigHandler.reloadConfig();
        getCommand("fly").setExecutor(new Fly());
        getCommand("usefulcommands").setExecutor(new UsefulCommandsCommand());
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("heal").setExecutor(new Heal());
        getCommand("feed").setExecutor(new Feed());
        getCommand("clearchat").setExecutor(new ClearChat());
        getCommand("enderchest").setExecutor(new Enderchest());
        getCommand("repair").setExecutor(new Repair());
        getCommand("suicide").setExecutor(new Suicide());
        getCommand("speed").setExecutor(new Speed());
        getCommand("kickall").setExecutor(new KickAll());
        getCommand("tpall").setExecutor(new TpAll());
        getCommand("gms").setExecutor(new Gms());
        getCommand("gma").setExecutor(new Gma());
        getCommand("gmsp").setExecutor(new Gmsp());
        getCommand("gmc").setExecutor(new Gmc());
        getCommand("tempban").setExecutor(new Tempban());
        getCommand("mute").setExecutor(new Mute());
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDisconnect(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        Bukkit.getConsoleSender().sendMessage("UsefulCommands started");
    }

    @Override
    public void onDisable() {

    }
}
