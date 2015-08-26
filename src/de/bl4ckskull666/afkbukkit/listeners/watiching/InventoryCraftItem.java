/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.afkbukkit.listeners.watiching;

import de.bl4ckskull666.afkbukkit.AFKBukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

/**
 *
 * @author PapaHarni
 */
public class InventoryCraftItem implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onCraftItem(CraftItemEvent e) {
        if(AFKBukkit.getPlugin().getConfig().getBoolean("check.inventory-craft", false)) {
            if(e.getWhoClicked() instanceof Player)
                AFKBukkit.getPlugin().sendPluginMessage(((Player)e.getWhoClicked()));
        }
    }
}
