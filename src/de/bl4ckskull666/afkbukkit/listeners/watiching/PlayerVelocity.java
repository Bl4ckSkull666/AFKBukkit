/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.afkbukkit.listeners.watiching;

import de.bl4ckskull666.afkbukkit.AFKBukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;

/**
 *
 * @author PapaHarni
 */
public class PlayerVelocity implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerVelocity(PlayerVelocityEvent e) {
        if(AFKBukkit.getPlugin().getConfig().getBoolean("check.player-velocity", false))
            AFKBukkit.getPlugin().sendPluginMessage(e.getPlayer());
    }
}
