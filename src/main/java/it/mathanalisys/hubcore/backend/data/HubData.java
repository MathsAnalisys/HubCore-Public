package it.mathanalisys.hubcore.backend.data;

import it.mathanalisys.hubcore.cosmetics.type.ColorChatApply;
import it.mathanalisys.hubcore.HubCore;
import lombok.Data;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Data
public class HubData {

    private static Map<UUID, HubData> hubDataMap = new HashMap<>();
    private String name;
    private UUID uuid;

    //Data for cosmetics
    private ColorChatApply colorChatApply;


    public HubData(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        hubDataMap.put(uuid, this);
        load(true);
    }

    public HubData(UUID uuid) {
        this.uuid = uuid;
        this.name = Bukkit.getPlayer(uuid) != null ? Bukkit.getPlayer(uuid).getName() : Bukkit.getOfflinePlayer(uuid).getName();
        hubDataMap.put(uuid, this);
    }

    public void load(boolean asyncMethod){
        if (asyncMethod){
            new BukkitRunnable() {
                @Override
                public void run() {
                    load();
                }
            }.runTaskAsynchronously(HubCore.getInstance());
        }else {
            load();
        }
    }

    public void load() {
        Document document = HubCore.get().getDatabaseManager().getPlayerDocument(uuid);

        if (document != null) {
            if (this.name == null) {
                this.name = document.getString("name");
            }

            this.colorChatApply = Objects.requireNonNull(document).get("colorChatApply") != null ? ColorChatApply.valueOf(document.getString("colorChatApply")) : ColorChatApply.NONE;
        }else {
            save();
        }
    }

    public void save(boolean asyncMethod){
        if (asyncMethod) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    save();
                }
            }.runTaskAsynchronously(HubCore.getInstance());
        }else {
            save();
        }
    }

    private void save(){
        Document document = HubCore.getInstance().getDatabaseManager().getPlayerDocument(uuid);

        if (document == null){
            document = new Document();
        }

        document.put("uuid", uuid.toString());
        document.put("name", name);
        document.put("colorChat", colorChatApply != null ? colorChatApply.getColor().name() : "None");

        HubCore.getInstance().getDatabaseManager().replacePlayerDocument(this, document);
    }

    public static HubData getUuid(UUID uuid) {
        HubData hubData = hubDataMap.get(uuid);
        if (hubData == null) {
            hubData = new HubData(uuid);
            hubData.load(true);
        }
        return hubData;
    }

    public static HubData getName(String name) {
        return getUuid(Bukkit.getPlayer(name) == null ? Bukkit.getOfflinePlayer(name).getUniqueId() : Bukkit.getPlayer(name).getUniqueId());

    }
}
