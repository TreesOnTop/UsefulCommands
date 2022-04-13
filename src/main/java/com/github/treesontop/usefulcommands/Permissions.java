package com.github.treesontop.usefulcommands;

import org.bukkit.Bukkit;

public class Permissions {
    public static void register() {
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.broadcast"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.clearchat"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.enderchest"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.enderchest.others"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.fly"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.fly.others"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.feed"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.feed.others"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.heal"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.heal.others"));
        Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission("usefulcommands.reload"));
    }
}
