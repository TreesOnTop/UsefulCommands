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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("Prefix"));
        if (args.length > 1) {
            float speed;
            try {
                speed = Float.parseFloat(args[1]);
                if (speed < 0.0f || speed > 1.0f) {
                    sender.sendMessage(prefix + "Speed must be between 0.0 and 1.0.");
                    return true;
                }
            } catch (Exception e) {
                sender.sendMessage(prefix + "Speed must be a number.");
                return true;
            }
            try {
                Player target = Bukkit.getPlayer(args[0]);
                if (label.equalsIgnoreCase("walkspeed") || label.equalsIgnoreCase("ws")) {
                    target.setWalkSpeed(speed);
                    sender.sendMessage(prefix + target.getName() + "'s walk speed has been set to " + speed);
                    target.sendMessage(prefix + "Your walk speed has been  set to " + speed);
                } else if (label.equalsIgnoreCase("flyspeed") || label.equalsIgnoreCase("fs")) {
                    target.setFlySpeed(speed);
                    sender.sendMessage(prefix + target.getName() + "'s fly speed has been set to " + speed);
                    target.sendMessage(prefix + "Your fly speed has been  set to " + speed);
                } else {
                    if (target.isFlying()) {
                        target.setFlySpeed(speed);
                        sender.sendMessage(prefix + target.getName() + "'s fly speed has been set to " + speed);
                        target.sendMessage(prefix + "Your fly speed has been  set to " + speed);
                    } else {
                        target.setWalkSpeed(speed);
                        sender.sendMessage(prefix + target.getName() + "'s walk speed has been set to " + speed);
                        target.sendMessage(prefix + "Your walk speed has been  set to " + speed);
                    }
                }
            } catch (Exception e) {
                sender.sendMessage(prefix + args[0] + " is not a valid player");
            }
        } else if (args.length > 0) {
            float speed;
            try {
                speed = Float.parseFloat(args[0]);
                if (speed < 0.0f || speed > 1.0f) {
                    sender.sendMessage(prefix + "Speed must be between 0.0 and 1.0.");
                    return true;
                }
            } catch (Exception e) {
                sender.sendMessage(prefix + "Speed must be a number.");
                return true;
            }
            if (label.equalsIgnoreCase("walkspeed") || label.equalsIgnoreCase("ws")) {
                ((Player) sender).setWalkSpeed(speed);
                sender.sendMessage(prefix + "Your walk speed has been set to " + speed);
            } else if (label.equalsIgnoreCase("flyspeed") || label.equalsIgnoreCase("fs")) {
                ((Player) sender).setFlySpeed(speed);
                sender.sendMessage(prefix + "Your fly speed has been set to " + speed);
            } else {
                if (((Player) sender).isFlying()) {
                    ((Player) sender).setFlySpeed(speed);
                    sender.sendMessage(prefix + "Your fly speed has been set to " + speed);
                } else {
                    ((Player) sender).setWalkSpeed(speed);
                    sender.sendMessage(prefix + "Your walk speed has been set to " + speed);
                }
            }
        } else {
            sender.sendMessage(prefix + "You must specify a speed.");
        }
        return true;
    }

}
