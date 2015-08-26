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
import org.bukkit.event.player.PlayerKickEvent;

/**
 *
 * @author PapaHarni
 */
public class PlayerKick implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerKick(PlayerKickEvent e) {
        if(AFKBukkit.getPlugin().getConfig().getBoolean("check.player-kick", false))
            AFKBukkit.getPlugin().sendPluginMessage(e.getPlayer());
    }
}
