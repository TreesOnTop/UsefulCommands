package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.material.EnderChest;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

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
        Objects.requireNonNull(this.getCommand("fly")).setExecutor(new Fly());
        Objects.requireNonNull(this.getCommand("reload")).setExecutor(new Reload());
        Objects.requireNonNull(this.getCommand("broadcast")).setExecutor(new Broadcast());
        Objects.requireNonNull(this.getCommand("heal")).setExecutor(new Heal());
        Objects.requireNonNull(this.getCommand("feed")).setExecutor(new Feed());
        Objects.requireNonNull(this.getCommand("clearchat")).setExecutor(new ClearChat());
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new Enderchest());
        Permissions.register();
    }
    private static UsefulCommands mainClass;
    public static UsefulCommands getMainClass() { return mainClass; }
}
