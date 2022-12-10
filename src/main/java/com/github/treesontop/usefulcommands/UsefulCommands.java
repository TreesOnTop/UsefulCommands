package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import com.github.treesontop.usefulcommands.events.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class UsefulCommands extends JavaPlugin {

    private static UsefulCommands mainClass;

    public static UsefulCommands getMainClass() {
        return mainClass;
    }

    @Override
    public void onEnable() {
        mainClass = this;
        ConfigHandler.reloadConfig();
        getCommand("fly").setExecutor(new Fly());
        getCommand("fly").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("usefulcommands").setExecutor(new UsefulCommandsCommand());
        getCommand("usefulcommands").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("broadcast").setExecutor(new Broadcast());
        getCommand("broadcast").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("heal").setExecutor(new Heal());
        getCommand("heal").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("feed").setExecutor(new Feed());
        getCommand("feed").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("clearchat").setExecutor(new ClearChat());
        getCommand("clearchat").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("enderchest").setExecutor(new Enderchest());
        getCommand("enderchest").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("repair").setExecutor(new Repair());
        getCommand("repair").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("suicide").setExecutor(new Suicide());
        getCommand("suicide").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("speed").setExecutor(new Speed());
        getCommand("speed").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("kickall").setExecutor(new KickAll());
        getCommand("kickall").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("tpall").setExecutor(new TpAll());
        getCommand("tpall").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("gms").setExecutor(new Gms());
        getCommand("gms").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("gma").setExecutor(new Gma());
        getCommand("gma").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("gmsp").setExecutor(new Gmsp());
        getCommand("gmsp").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("gmc").setExecutor(new Gmc());
        getCommand("gmc").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("tempban").setExecutor(new Tempban());
        getCommand("tempban").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("mute").setExecutor(new Mute());
        getCommand("mute").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("god").setExecutor(new God());
        getCommand("god").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        getCommand("freeze").setExecutor(new Freeze());
        getCommand("freeze").setPermissionMessage(ConfigHandler.getConfig().getString("PermissionMessage"));
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDisconnect(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncPlayerChat(), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMove(), this);
        for(Player p : Bukkit.getOnlinePlayers()) {
            UserHandler.addUser(p);
        }
        Bukkit.getConsoleSender().sendMessage("UsefulCommands started");
    }

    @Override
    public void onDisable() {
        for(Player p : Bukkit.getOnlinePlayers()){
            UserHandler.removeUser(p);
        }
    }
}
