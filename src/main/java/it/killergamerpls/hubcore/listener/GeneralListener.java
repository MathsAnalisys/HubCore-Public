package it.killergamerpls.hubcore.listener;

import it.killergamerpls.hubcore.main.HubCore;
import it.killergamerpls.hubcore.utils.CC;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Objects;

public class GeneralListener implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<String> messages = HubCore.get().getMessage().getStringList("Message.OnJoin");

        for (String message : messages){
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, CC.translate(message)));
        }

        if(HubCore.get().getConfig().getBoolean("Settings.onJoinMessage")){
            event.setJoinMessage(PlaceholderAPI.setPlaceholders(player, Objects.requireNonNull(CC.translate(HubCore.get().getMessage().getString("Message.OnJoinWelcomeMessage")))));
        }
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(HubCore.get().getConfig().getBoolean("Settings.onQuitMessage")){
            event.setQuitMessage(PlaceholderAPI.setPlaceholders(player, Objects.requireNonNull(CC.translate(HubCore.get().getMessage().getString("Message.OnQuitWelcomeMessage")))));
        }
    }




}
