package it.killergamerpls.hubcore.listener;

import it.killergamerpls.hubcore.main.HubCore;
import it.killergamerpls.hubcore.utils.CC;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.List;

public class GeneralListener implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        List<String> messages = HubCore.get().getMessage().getStringList("Message.OnJoin");
        for (String message : messages){
            player.sendMessage(PlaceholderAPI.setPlaceholders(player, CC.translate(message)));
        }
    }
}
