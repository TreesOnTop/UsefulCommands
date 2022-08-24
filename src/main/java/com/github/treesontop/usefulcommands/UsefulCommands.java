package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import com.github.treesontop.usefulcommands.events.AsyncChat;
import com.github.treesontop.usefulcommands.events.PlayerDisconnect;
import com.github.treesontop.usefulcommands.events.PlayerJoin;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;

public final class UsefulCommands extends JavaPlugin {

    public static final HashMap<String, File> Players = new HashMap<>();
    private static UsefulCommands mainClass;
    private static String permissionMessage;

    public static String getPermissionMessage() {
        return permissionMessage;
    }

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
        this.getCommand("kickall").setExecutor(new KickAll());
        this.getCommand("tpall").setExecutor(new TpAll());
        this.getCommand("gms").setExecutor(new Gms());
        this.getCommand("gma").setExecutor(new Gma());
        this.getCommand("gmsp").setExecutor(new Gmsp());
        this.getCommand("gmc").setExecutor(new Gmc());
        this.getCommand("tempban").setExecutor(new Tempban());
        this.getCommand("mute").setExecutor(new Mute());
        File paperFile;
        if (Bukkit.getServer().getClass().getPackage().getName().contains("1.19")) {
            paperFile = new File(this.getDataFolder().getParentFile().getParentFile(), "\\config\\paper-global.yml");
            TextComponent permissionMessageComponent = PlainTextComponentSerializer.plainText().deserialize(YamlConfiguration.loadConfiguration(paperFile).getString("messages.no-permission"));
            permissionMessage = ChatColor.translateAlternateColorCodes('&', LegacyComponentSerializer.legacyAmpersand().serialize(permissionMessageComponent));
        } else {
            paperFile = new File(this.getDataFolder().getParentFile().getParentFile(), "paper.yml");
            permissionMessage = ChatColor.translateAlternateColorCodes('&', YamlConfiguration.loadConfiguration(paperFile).getString("messages.no-permission"));
        }
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDisconnect(), this);
        Bukkit.getPluginManager().registerEvents(new AsyncChat(), this);
        Bukkit.getConsoleSender().sendMessage("UsefulCommands started");
    }

    @Override
    public void onDisable() {

    }
}
