package it.killergamerpls.hubcore.main;

import org.bukkit.plugin.java.JavaPlugin;

public class HubCoreMain extends JavaPlugin {

    @Override
    public void onEnable() {
        new HubCore(this);
    }

    @Override
    public void onDisable() {

    }
}
