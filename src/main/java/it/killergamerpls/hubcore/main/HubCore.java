package it.killergamerpls.hubcore.main;

import it.killergamerpls.hubcore.listener.WorldListener;
import it.killergamerpls.hubcore.utils.ConfigFile;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
@Getter
public class HubCore {

    @Getter
    private static HubCore instance;
    private JavaPlugin plugin;
    private ConfigFile config;
    private ConfigFile message;


    public HubCore(JavaPlugin plugin){
        this.plugin = plugin;

        loadConfig();
        loadListenerAndCommands();
    }

    private void loadConfig(){
        this.config = new ConfigFile(plugin, "config.yml");
        this.message = new ConfigFile(plugin, "message.yml");
    }

    private void loadListenerAndCommands(){
        List.of(
                new WorldListener()
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, plugin));
    }


    public static HubCore get(){
        return instance;
    }
}
