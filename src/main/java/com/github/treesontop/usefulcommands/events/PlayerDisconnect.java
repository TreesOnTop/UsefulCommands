package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.ConfigHandler;
import com.github.treesontop.usefulcommands.UsefulCommands;
import com.github.treesontop.usefulcommands.UserHandler;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerDisconnect implements Listener {
    @EventHandler
    public void onPlayerDisconnect(PlayerJoinEvent event) {
        YamlConfiguration config = ConfigHandler.getConfig();
        UserHandler.removeUser(event.getPlayer());
        if (config.getBoolean("LeaveOnVanish")) {
            event.setJoinMessage(null);
        }
    }
}
