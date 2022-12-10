package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class UsefulCommandsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            String arg = args[0].toLowerCase();
            switch (arg) {
                case "reload":
                    ConfigHandler.reloadConfig();
                    sender.sendMessage(prefix + "Config reloaded.");
                    break;
                case "ver":
                case "version":
                    sender.sendMessage(prefix + "Version " + UsefulCommands.getMainClass().getDescription().getVersion());
                    break;
                case "reset":
                    new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml").delete();
                    ConfigHandler.reloadConfig();
                    sender.sendMessage(prefix + "Config reset.");
                    break;
                default:
                    sender.sendMessage("/usefulcommands reload - Reloads the config.");
                    sender.sendMessage("/usefulcommands version - Shows the version of the plugin.");
                    sender.sendMessage("/usefulcommands reset - Resets the config.");
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ConfigHandler.getConfig().getString("Prefix")) + "UsefulCommands v" + ConfigHandler.getConfig().getString("Version") + " by TreesOnTop");
        }
        return true;
    }
}
