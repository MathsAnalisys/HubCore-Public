package it.killergamerpls.hubcore.commands.staff;

import it.killergamerpls.hubcore.main.HubCore;
import it.killergamerpls.hubcore.utils.CC;
import it.killergamerpls.hubcore.utils.LocationUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SetspawnCommand extends Command {

    public SetspawnCommand() {
        super("setspawn");
        setPermission("hubcore.command.setspawn");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!sender.hasPermission(Objects.requireNonNull(getPermission()))){
            sender.sendMessage(CC.translate("&cYou don't have permission to execute this command!"));
            return true;
        }

        if (sender instanceof Player player){
            Location location = player.getLocation();
            HubCore.get().getConfig().set("Spawn", LocationUtil.serializeLocation(location));
            HubCore.get().getConfig().save();
            sender.sendMessage(CC.translate("&aSaved location of spawn!"));
        }


        return false;
    }
}
