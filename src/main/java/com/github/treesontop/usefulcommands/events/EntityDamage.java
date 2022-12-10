package com.github.treesontop.usefulcommands.events;

import com.github.treesontop.usefulcommands.UserHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamage implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(UserHandler.getUser(p).isGod()) {
                event.setCancelled(true);
            }
        }
    }
}
