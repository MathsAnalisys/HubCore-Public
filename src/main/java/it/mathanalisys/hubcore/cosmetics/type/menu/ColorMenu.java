package it.mathanalisys.hubcore.cosmetics.type.menu;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotPos;
import it.mathanalisys.hubcore.backend.data.HubData;
import it.mathanalisys.hubcore.cosmetics.type.ColorChatApply;
import it.mathanalisys.hubcore.HubCore;
import it.mathanalisys.hubcore.utils.CC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ColorMenu implements InventoryProvider {

    public static SmartInventory inventoryColor = SmartInventory.builder()
            .id("COLOR_MENU")
            .provider(new ColorMenu())
            .manager(HubCore.get().getInventoryManager())
            .size(3, 9)
            .title("§8Color Menu")
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        HubData hubData = new HubData(player.getUniqueId());
        contents.set(new SlotPos(1, 1), ClickableItem.of(new ItemStack(Material.CACTUS), inventoryClickEvent -> {
            hubData.setColorChatApply(ColorChatApply.GREEN);
            hubData.save(true);
            init(player, contents);
            player.sendMessage(CC.translate("&aFatto"));
        }));

    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
