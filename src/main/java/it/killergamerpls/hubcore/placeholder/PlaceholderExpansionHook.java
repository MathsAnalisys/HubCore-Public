package it.killergamerpls.hubcore.placeholder;

import it.killergamerpls.hubcore.main.HubCore;
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
        return HubCore.get().getPlugin().getDescription().getVersion();
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

        return null;
    }
}
