package com.github.treesontop.usefulcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.time.Duration;

public class Mute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            
            try {
                Duration.parse("PT" + args[0]).toSeconds();
            } catch (Exception e) {
                sender.sendMessage("§cInvalid time format");
                return true;
            }
        }
        return true;
    }
}
