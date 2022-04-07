package com.github.treesontop.usefulcommands;

import com.github.treesontop.usefulcommands.commands.Fly;
import com.github.treesontop.usefulcommands.commands.Reload;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private static UsefulCommands mainClass;
    public static UsefulCommands getMainClass() { return mainClass; }
}
