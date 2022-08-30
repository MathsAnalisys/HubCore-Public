package it.killergamerpls.hubcore.provider;

import it.killergamerpls.hubcore.main.HubCore;

public class ProviderManager {

    public ProviderManager(){
        HubBoardProvider hubBoardProvider = new HubBoardProvider();
        hubBoardProvider.runTaskTimer(HubCore.get().getPlugin(), 0L, 20L);
    }
}
