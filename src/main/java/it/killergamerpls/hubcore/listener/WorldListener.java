package it.killergamerpls.hubcore.listener;

import it.killergamerpls.hubcore.main.HubCore;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.weather.ThunderChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Objects;

public class WorldListener implements Listener {

    @EventHandler
    public void onThunderChange(ThunderChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInPortal(PlayerPortalEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onPrepareCraftItems(PrepareItemCraftEvent event){
        event.getInventory().setResult(null);
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onChangeFoodLevel(FoodLevelChangeEvent event){
        if (event.getEntity() instanceof Player){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onVinesGrow(BlockGrowEvent event) {
        if (event.getBlock().getType() == Material.VINE) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = event.getPlayer();

        if (player.hasPermission(Objects.requireNonNull(HubCore.get().getConfig().getString("Settings.permissionBreakAndPlace"))) && player.getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(false);
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if (player.hasPermission(Objects.requireNonNull(HubCore.get().getConfig().getString("Settings.permissionBreakAndPlace"))) && player.getGameMode().equals(GameMode.CREATIVE)){
            event.setCancelled(false);
        }
        event.setCancelled(true);
    }
}
