package com.github.treesontop.usefulcommands.commands;

import com.github.treesontop.usefulcommands.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Speed implements CommandExecutor {

    public static void setSpeed(Player player, float speed, String type) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (type.equals("walk")) {
            player.setWalkSpeed(speed);
            player.sendMessage(prefix + "Your walk speed has been set to " + speed);
        } else if (type.equals("fly")) {
            player.setFlySpeed(speed);
            player.sendMessage(prefix + "Your fly speed has been set to " + speed);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 0) {
            float speed;
            try {
                speed = Float.parseFloat(args[args.length - 1]);
                if (speed < 0.0f || speed > 1.0f) {
                    sender.sendMessage(prefix + "Speed must be between 0.0 and 1.0.");
                    return true;
                }
            } catch (Exception e) {
                sender.sendMessage(prefix + "Speed must be a number.");
                return true;
            }
            if (args.length > 1) {
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (label.equalsIgnoreCase("flyspeed") || label.equalsIgnoreCase("fs")) {
                        setSpeed(target, speed, "fly");
                        sender.sendMessage(prefix + target.getName() + "'s fly speed has been set to " + speed);
                    } else {
                        setSpeed(target, speed, "walk");
                        sender.sendMessage(prefix + target.getName() + "'s walk speed has been set to " + speed);
                    }
                } catch (Exception ignored) {
                    sender.sendMessage(prefix + "Player not found.");
                }
                return true;
            }
            if (sender instanceof Player player) {
                if (label.equalsIgnoreCase("flyspeed") || label.equalsIgnoreCase("fs")) {
                    setSpeed(player, speed, "fly");
                } else if (label.equalsIgnoreCase("walkspeed") || label.equalsIgnoreCase("ws")) {
                    setSpeed(player, speed, "walk");
                } else {
                    if (player.isFlying()) {
                        setSpeed(player, speed, "fly");
                    } else {
                        setSpeed(player, speed, "walk");
                    }
                }
            } else {
                sender.sendMessage(prefix + "You must be a player to use this command");
            }
        } else {
            sender.sendMessage(prefix + "You must specify a player and a speed.");
        }
        return true;
    }
}
