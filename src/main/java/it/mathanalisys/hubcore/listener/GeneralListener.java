package it.mathanalisys.hubcore.listener;

import it.mathanalisys.hubcore.backend.data.HubData;
import it.mathanalisys.hubcore.HubCore;
import it.mathanalisys.hubcore.utils.CC;
import it.mathanalisys.hubcore.utils.LuckPermsUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;
import java.util.Objects;

public class GeneralListener implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<String> messages = HubCore.get().getMessage().getStringList("Message.OnJoin");

        new HubData(player.getUniqueId(), player.getName());

        for (String message : messages){
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, CC.translate(message)));
        }

        if(HubCore.get().getConfig().getBoolean("Settings.onJoinMessage")){
            event.joinMessage(Component.text(PlaceholderAPI.setPlaceholders(player, Objects.requireNonNull(CC.translate(HubCore.get().getMessage().getString("Message.OnJoinWelcomeMessage"))))));
        }
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event){
        Player player = event.getPlayer();

        if(HubCore.get().getConfig().getBoolean("Settings.onQuitMessage")){
            event.quitMessage(Component.text(PlaceholderAPI.setPlaceholders(player, Objects.requireNonNull(CC.translate(HubCore.get().getMessage().getString("Message.OnQuitWelcomeMessage"))))));
        }
    }

    @EventHandler
    public void onPlayerChatting(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        HubData playerData = HubData.getUuid(player.getUniqueId());

        event.setFormat(LuckPermsUtils.getRankColor(player) + player.getName() + ": " + playerData.getColorChatApply().getColor() + message);
    }




}
