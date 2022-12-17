package it.mathanalisys.hubcore.cosmetics;

import it.mathanalisys.hubcore.utils.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CosmeticCommand extends Command {

    protected CosmeticCommand() {
        super("cosmetic");
        setAliases(List.of("cosmetici"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(CC.translate("&cSolo i giocatori possono eseguire questo comando!"));
            return true;
        }

        new ComsmeticsMenu().inventory.open((Player) sender);
        sender.sendMessage(CC.translate("&aAperto menu dei cosmetici!"));

        return false;
    }
}
