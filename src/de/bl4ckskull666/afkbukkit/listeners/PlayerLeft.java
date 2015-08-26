/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.afkbukkit.listeners;

import de.bl4ckskull666.afkbukkit.AFKBukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author PapaHarni
 */
public class PlayerLeft implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerKick(PlayerKickEvent e) {
        AFKBukkit.hidePlayer(e.getPlayer(), false);
        AFKBukkit.setPlayerStatus(e.getPlayer(), false);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent e) {
        AFKBukkit.hidePlayer(e.getPlayer(), false);
        AFKBukkit.setPlayerStatus(e.getPlayer(), false);
    }
}
