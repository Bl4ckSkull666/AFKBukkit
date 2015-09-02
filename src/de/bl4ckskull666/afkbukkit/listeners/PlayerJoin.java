/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.afkbukkit.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.bl4ckskull666.afkbukkit.AFKBukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author PapaHarni
 */
public class PlayerJoin implements Listener {
    private static boolean _firstJoined = false;
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent e) {
        AFKBukkit.setHiddenPlayers(e.getPlayer());
        if(AFKBukkit.getPlugin().getConfig().getBoolean("use-bungeecord-config", false) && !_firstJoined) {
            AFKBukkit.debugMe("Request Configuration");
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("AFKB");
            out.writeUTF("Config");
            e.getPlayer().sendPluginMessage(AFKBukkit.getPlugin(), "BungeeCord", out.toByteArray());
            _firstJoined = true;
        }
    }
}
