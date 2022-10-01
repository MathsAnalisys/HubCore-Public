package it.killergamerpls.hubcore.main;

import it.killergamerpls.hubcore.bungee.BungeeCounterListener;
import it.killergamerpls.hubcore.bungee.utils.BungeeServerUtils;
import it.killergamerpls.hubcore.commands.SpawnCommand;
import it.killergamerpls.hubcore.commands.staff.SetspawnCommand;
import it.killergamerpls.hubcore.listener.WorldListener;
import it.killergamerpls.hubcore.provider.ProviderManager;
import it.killergamerpls.hubcore.utils.ConfigFile;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

@Getter
@Setter
public class HubCore {

    @Getter
    private static HubCore instance;
    private JavaPlugin plugin;
    private ConfigFile config;
    private ConfigFile message;
    private ProviderManager providerManager;
    private BungeeServerUtils bungeeServerUtils;


    public HubCore(JavaPlugin plugin){
        this.plugin = plugin;

        loadManager();
    }


    private void loadManager(){
        this.providerManager = new ProviderManager();
        this.bungeeServerUtils = new BungeeServerUtils();

        registerBungeeListeners();
        loadConfig();
        loadListenerAndCommands();
    }

    private void loadListenerAndCommands(){
        List.of(
                new WorldListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));

        List.of(
                new SetspawnCommand(),
                new SpawnCommand()
        ).forEach(command-> Bukkit.getCommandMap().register(plugin.getName(), command));
    }

    private void loadConfig(){
        this.config = new ConfigFile(plugin, "config.yml");
        this.message = new ConfigFile(plugin, "message.yml");
    }

    private void registerBungeeListeners(){
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", new BungeeCounterListener());
    }

    public static HubCore get(){
        return instance;
    }
}
