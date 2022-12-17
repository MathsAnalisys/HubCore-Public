package it.mathanalisys.hubcore;

import fr.minuskube.inv.InventoryManager;
import it.mathanalisys.hubcore.backend.DatabaseManager;
import it.mathanalisys.hubcore.bungee.BungeeCounterListener;
import it.mathanalisys.hubcore.bungee.PlayerCounterGlobal;
import it.mathanalisys.hubcore.bungee.utils.BungeeServerUtils;
import it.mathanalisys.hubcore.commands.SpawnCommand;
import it.mathanalisys.hubcore.commands.staff.SetspawnCommand;
import it.mathanalisys.hubcore.listener.WorldListener;
import it.mathanalisys.hubcore.provider.ProviderManager;
import it.mathanalisys.hubcore.utils.ConfigFile;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
public class HubCore extends JavaPlugin {

    @Getter
    private static HubCore instance;
    private ConfigFile config;
    private ConfigFile message;
    private ProviderManager providerManager;
    private BungeeServerUtils bungeeServerUtils;
    private InventoryManager inventoryManager;
    //private PlayerCounterGlobal playerCounterGlobal;
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        loadManager();
    }

    @Override
    public void onDisable() {
        //this.playerCounterGlobal.interrupt();
    }

    private void loadManager(){
        this.providerManager = new ProviderManager();
        this.bungeeServerUtils = new BungeeServerUtils();
        //this.playerCounterGlobal = new PlayerCounterGlobal();
        this.databaseManager = new DatabaseManager();


        this.inventoryManager = new InventoryManager(this);
        inventoryManager.init();


        registerBungeeListeners();
        loadConfig();
        loadListenerAndCommands();
    }

    private void loadListenerAndCommands(){
        List.of(
                new WorldListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));

        List.of(
                new SetspawnCommand(),
                new SpawnCommand()
        ).forEach(command-> Bukkit.getCommandMap().register(this.getName(), command));
    }

    private void loadConfig(){
        this.config = new ConfigFile(this, "config.yml");
        this.message = new ConfigFile(this, "message.yml");
    }

    private void registerBungeeListeners(){
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeCounterListener());
    }

    public static HubCore get(){
        return instance;
    }
}
