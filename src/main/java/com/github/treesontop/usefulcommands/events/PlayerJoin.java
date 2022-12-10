package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import com.github.treesontop.usefulcommands.UserHandler;
import com.github.treesontop.usefulcommands.classes.User;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        YamlConfiguration config = ConfigHandler.getConfig();
        String prefix = config.getString("Prefix");
        UserHandler.addUser(event.getPlayer());
        User u = UserHandler.getUser(event.getPlayer());
        if (u.isVanished()) {
            event.getPlayer().sendMessage("You are vanished.");
            for (Player p : event.getPlayer().getServer().getOnlinePlayers()) {
                p.hidePlayer(UsefulCommands.getMainClass(), event.getPlayer());
            }
            if (config.getBoolean("LeaveOnVanish")) {
                event.setJoinMessage(null);
            }
        }
    }
}
