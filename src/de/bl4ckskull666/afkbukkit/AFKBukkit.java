/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.bl4ckskull666.afkbukkit;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import de.bl4ckskull666.afkbukkit.listeners.PlayerJoin;
import de.bl4ckskull666.afkbukkit.listeners.PlayerLeft;
import de.bl4ckskull666.afkbukkit.byafk.VehicleEntityCollision;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryClick;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryClose;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryCraftItem;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryCreative;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryDrag;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryFurnaceExtract;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryInteract;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryOpen;
import de.bl4ckskull666.afkbukkit.listeners.watiching.InventoryPickupItem;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerAchievementAwarded;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerAnimation;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerArmorStandManipulate;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerBedEnter;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerBedLeave;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerBucketEmpty;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerBucketFill;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerChangedWorld;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerChatTapComplete;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerCommandPreprocess;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerDropItem;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerEditBook;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerEggThrow;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerExpChange;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerFish;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerGameModeChange;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerInteract;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerInteractEntity;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerItemBreak;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerItemConsume;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerItemDamage;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerItemHeld;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerKick;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerLevelChange;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerLogin;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerMove;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerQuit;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerRegisterChannel;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerRespawn;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerShearEntity;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerStatisticIncrement;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerTeleport;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerToggleFlight;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerToggleSneak;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerToggleSprint;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerUnleashEntity;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerUnregisterChannel;
import de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerVelocity;
import de.bl4ckskull666.afkbukkit.listeners.watiching.VehicleDamage;
import de.bl4ckskull666.afkbukkit.listeners.watiching.VehicleEnter;
import de.bl4ckskull666.afkbukkit.listeners.watiching.VehicleExit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

/**
 *
 * @author PapaHarni
 */
public class AFKBukkit extends JavaPlugin implements PluginMessageListener {
    private static AFKBukkit _plugin;
    private static final ArrayList<UUID> _isAFK = new ArrayList<>();
    private static final HashMap<UUID, Long> _lastFire = new HashMap<>();
    
    @Override
    public void onEnable() {
        _plugin = this;
        if(!getDataFolder().exists()) {
            getDataFolder().mkdir();
            getConfig().options().copyDefaults(true);
        }
        saveConfig();
        
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeft(), this);
        getServer().getPluginManager().registerEvents(new VehicleEntityCollision(), this);
        
        //Watching Listeners
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new InventoryClose(), this);
        getServer().getPluginManager().registerEvents(new InventoryCraftItem(), this);
        getServer().getPluginManager().registerEvents(new InventoryCreative(), this);
        getServer().getPluginManager().registerEvents(new InventoryDrag(), this);
        getServer().getPluginManager().registerEvents(new InventoryFurnaceExtract(), this);
        getServer().getPluginManager().registerEvents(new InventoryInteract(), this);
        getServer().getPluginManager().registerEvents(new InventoryOpen(), this);
        getServer().getPluginManager().registerEvents(new InventoryPickupItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerAchievementAwarded(), this);
        getServer().getPluginManager().registerEvents(new PlayerAnimation(), this);
        getServer().getPluginManager().registerEvents(new PlayerArmorStandManipulate(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedEnter(), this);
        getServer().getPluginManager().registerEvents(new PlayerBedLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerBucketEmpty(), this);
        getServer().getPluginManager().registerEvents(new PlayerBucketFill(), this);
        getServer().getPluginManager().registerEvents(new PlayerChangedWorld(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatTapComplete(), this);
        getServer().getPluginManager().registerEvents(new PlayerCommandPreprocess(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerEditBook(), this);
        getServer().getPluginManager().registerEvents(new PlayerEggThrow(), this);
        getServer().getPluginManager().registerEvents(new PlayerExpChange(), this);
        getServer().getPluginManager().registerEvents(new PlayerFish(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChange(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemBreak(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemConsume(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemDamage(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeld(), this);
        getServer().getPluginManager().registerEvents(new de.bl4ckskull666.afkbukkit.listeners.watiching.PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerKick(), this);
        getServer().getPluginManager().registerEvents(new PlayerLevelChange(), this);
        getServer().getPluginManager().registerEvents(new PlayerLogin(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerRegisterChannel(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new PlayerShearEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerStatisticIncrement(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleport(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleFlight(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleSneak(), this);
        getServer().getPluginManager().registerEvents(new PlayerToggleSprint(), this);
        getServer().getPluginManager().registerEvents(new PlayerUnleashEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerUnregisterChannel(), this);
        getServer().getPluginManager().registerEvents(new PlayerVelocity(), this);
        getServer().getPluginManager().registerEvents(new VehicleDamage(), this);
        getServer().getPluginManager().registerEvents(new VehicleEnter(), this);
        getServer().getPluginManager().registerEvents(new VehicleExit(), this);
        
    }
    
    public void sendPluginMessage(Player p) {
        if((_lastFire.containsKey(p.getUniqueId()) && 
                ((System.currentTimeMillis()-_lastFire.get(p.getUniqueId()))/1000) < 30) && 
                !_isAFK.contains(p.getUniqueId()))
            return;

        _lastFire.put(p.getUniqueId(), System.currentTimeMillis());
        
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("AFKB");
        out.writeUTF("Player");
        out.writeUTF(p.getUniqueId().toString());
        ByteArrayDataInput in = ByteStreams.newDataInput(out.toByteArray());
        debugMe("Send PluginMessage with " + in.readLine());
        p.sendPluginMessage(this, "BungeeCord", out.toByteArray());
        
    }
    
    public static void hidePlayer(Player p, boolean hide) {
        if(hide) {
            for(Player pl: Bukkit.getOnlinePlayers()) {
                if(!pl.hasPermission("amcserver.team") && !pl.getUniqueId().equals(p.getUniqueId())) {
                    pl.hidePlayer(p);
                }
            }
        } else {
            for(Player pl: Bukkit.getOnlinePlayers()) {
                pl.showPlayer(p);
            }
        }
    }
    
    public static void setHiddenPlayers(Player p) {
        for(UUID uuid: _isAFK) {
            Player pl = Bukkit.getPlayer(uuid);
            if(pl != null)
                p.hidePlayer(pl);
        }
    }
    
    public static void setPlayerStatus(Player p, boolean isAway) {
        if(isAway)
            _isAFK.add(p.getUniqueId());
        else
            _isAFK.remove(p.getUniqueId());
        
        if(_plugin.getConfig().getBoolean("on-afk.no-item-pickup", false))
            p.setCanPickupItems(!isAway);
        if(_plugin.getConfig().getBoolean("on-afk.use-custom-name", false)) {
            p.setCustomNameVisible(isAway);
            if(isAway)
                p.setCustomName(_plugin.getConfig().getString("on-afk.get-custom-name", ""));
            else
                p.setCustomName(p.getName());
        }
        
        if(_plugin.getConfig().getBoolean("on-afk.ignore-by-sleeping", false))
            p.setSleepingIgnored(isAway);
        
        if(_plugin.getConfig().getBoolean("on-afk.invisible", false))
            hidePlayer(p, isAway);
        
        if(_plugin.getConfig().getBoolean("on-afk.use-custom-name", false)) {
            if(isAway)
                p.setNoDamageTicks(Integer.MAX_VALUE);
            else
                p.setNoDamageTicks(0);
        }
    }
    
    public static boolean isPlayerAFK(Player p) {
        return _isAFK.contains(p.getUniqueId());
    }
    
    public static void debugMe(String msg) {
        if(_plugin.getConfig().getBoolean("debug", false))
            _plugin.getLogger().log(Level.INFO, "[DEBUG] {0}", msg);
    }
    
    public static AFKBukkit getPlugin() {
        return _plugin;
    }
    
    public static void checkSwitchPlayer(UUID uuid, boolean status) {
        if(status && !_isAFK.contains(uuid) || !status && _isAFK.contains(uuid)) {
            Player p = Bukkit.getPlayer(uuid);
            if(p != null)
                setPlayerStatus(p, status);
        }
    }
    
    @Override
    public void onPluginMessageReceived(String c, Player p, byte[] m) {
        if (!c.equalsIgnoreCase("BungeeCord"))
            return;

        ByteArrayDataInput in = ByteStreams.newDataInput(m);
        String sub = in.readUTF();
        if(!sub.equalsIgnoreCase("AFKB"))
            return;
        
        String cat = in.readUTF();
        if(cat.equalsIgnoreCase("Player")) {
            UUID uuid = UUID.fromString(in.readUTF());
            boolean bol = in.readBoolean();
            debugMe("Receive " + uuid.toString() + " with " + bol);
            AFKBukkit.checkSwitchPlayer(uuid, bol);
        } else if(cat.equalsIgnoreCase("Config")) {
            debugMe("Receive Configuration");
            String conf = in.readUTF();
            String val = in.readUTF().replace("__--__", " ");
            if(conf != null && val != null) {
                AFKBukkit.getPlugin().getConfig().set(conf, val);
                debugMe("Receive " + conf + " Configuration from Bungee and set " + val + " it.");
            }
            saveConfig();
        }
    }
}
