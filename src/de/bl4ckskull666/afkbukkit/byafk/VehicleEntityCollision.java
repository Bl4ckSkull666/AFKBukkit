/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.afkbukkit.byafk;

import de.bl4ckskull666.afkbukkit.AFKBukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

/**
 *
 * @author PapaHarni
 */
public class VehicleEntityCollision implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onVehicleEntityCollision(VehicleEntityCollisionEvent e) {
        if(e.getEntity() instanceof Player) {
            if(AFKBukkit.isPlayerAFK((Player)e.getEntity()) && AFKBukkit.getPlugin().getConfig().getBoolean("by-afk.no-vehicle-collision", false))
                e.setCollisionCancelled(true);
        }
    }
}
