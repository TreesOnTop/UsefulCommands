package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.UsefulCommands;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UsefulCommands.Players.put(event.getPlayer().getName(),
            new File(UsefulCommands.getMainClass().getDataFolder() + "/players/" + event.getPlayer().getName() + ".yml"));
    }
}
