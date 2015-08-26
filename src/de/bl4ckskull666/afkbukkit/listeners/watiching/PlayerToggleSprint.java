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
import org.bukkit.event.player.PlayerToggleSprintEvent;

/**
 *
 * @author PapaHarni
 */
public class PlayerToggleSprint implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerToggleSprint(PlayerToggleSprintEvent e) {
        if(AFKBukkit.getPlugin().getConfig().getBoolean("check.player-toggle-sprint", false))
            AFKBukkit.getPlugin().sendPluginMessage(e.getPlayer());
    }
}
