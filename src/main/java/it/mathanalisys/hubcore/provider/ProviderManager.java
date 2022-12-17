package it.mathanalisys.hubcore.provider;

import it.mathanalisys.hubcore.HubCore;

public class ProviderManager {

    public ProviderManager(){
        HubBoardProvider hubBoardProvider = new HubBoardProvider();
        hubBoardProvider.runTaskTimer(HubCore.get(), 0L, 20L);
    }
}
