package it.mathanalisys.hubcore.cosmetics;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import it.mathanalisys.hubcore.cosmetics.type.menu.ColorMenu;
import it.mathanalisys.hubcore.HubCore;
import it.mathanalisys.hubcore.utils.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ComsmeticsMenu implements InventoryProvider {

    public SmartInventory inventory = SmartInventory.builder()
            .id("COSMETICS_MENU")
            .provider(this)
            .size(3, 9)
            .manager(HubCore.get().getInventoryManager())
            .title(CC.translate("&8Cosmetici"))
            .build();


    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(new SlotPos(1, 1), ClickableItem.of(new ItemStack(Material.NOTE_BLOCK), inventoryClickEvent -> {
            ColorMenu.inventoryColor.open(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
