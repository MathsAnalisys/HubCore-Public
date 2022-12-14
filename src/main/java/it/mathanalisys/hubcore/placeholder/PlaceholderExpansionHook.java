package it.mathanalisys.hubcore.placeholder;

import it.mathanalisys.hubcore.bungee.PlayerCounterGlobal;
import it.mathanalisys.hubcore.HubCore;
import it.mathanalisys.hubcore.utils.LuckPermsUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlaceholderExpansionHook extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "hubcore";
    }

    @Override
    public @NotNull String getAuthor() {
        return "KillerGamerPls";
    }

    @Override
    public @NotNull String getVersion() {
        return HubCore.get().getDescription().getVersion();
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String match) {
        if (match.equalsIgnoreCase("player")){
            return player.getName();
        }

        if (match.equalsIgnoreCase("ping")){
            return String.valueOf(player.getPing());
        }

        if(match.equalsIgnoreCase("prefix_rank")){
            return LuckPermsUtils.getPrefix(player);
        }

        if (match.equalsIgnoreCase("color_rank")){
            return LuckPermsUtils.getRankColor(player);
        }

        if (match.equalsIgnoreCase("globalOnline")){
            return String.valueOf(PlayerCounterGlobal.GLOBAL_ONLINE);
        }

        return null;
    }
}
