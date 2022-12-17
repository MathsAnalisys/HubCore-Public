package it.mathanalisys.hubcore.commands;

import it.mathanalisys.hubcore.utils.LocationUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpawnCommand extends Command {

    public SpawnCommand() {
        super("spawn");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player player){
            player.teleport(LocationUtil.deserializeLocation("Spawn"));
        }
        return false;
    }
}
