package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        File configFile = new File(UsefulCommands.getMainClass().getDataFolder(), "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        if (config.getBoolean("vanished")) {
            event.getPlayer().sendMessage("You are vanished.");
            for (Player p : event.getPlayer().getServer().getOnlinePlayers()) {
                p.hidePlayer(UsefulCommands.getMainClass(), event.getPlayer());
            }
            if (config.getBoolean("LeaveOnVanish")) {
                event.setJoinMessage(null);
            }
        }
        UsefulCommands.Players.put(event.getPlayer().getUniqueId().toString(), configFile);
    }
}
