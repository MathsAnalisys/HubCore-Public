package it.mathanalisys.hubcore.provider;

import it.mathanalisys.hubcore.HubCore;
import it.mathanalisys.hubcore.utils.CC;
import it.mathanalisys.hubcore.utils.netherboard.Netherboard;
import it.mathanalisys.hubcore.utils.netherboard.bukkit.BPlayerBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class HubBoardProvider extends BukkitRunnable {

    public static void createScoreboard(Player player){
        List<String> board = HubCore.get().getConfig().getStringList("Scoreboard.Lines");
        BPlayerBoard bPlayerBoard = Netherboard.instance().getBoard(player);
        if (bPlayerBoard == null){
            bPlayerBoard = Netherboard.instance().createBoard(player, CC.translate("Scoreboard.Title"));
        }


        for (int i = 0; i < board.size(); i++){
            int pos = board.size()-1;
            bPlayerBoard.set(CC.translate(PlaceholderAPI.setPlaceholders(player, board.get(i))), pos);
        }
    }

    @Override
    public void run() {
        try {
            for (Player player : Bukkit.getServer().getOnlinePlayers()){
                createScoreboard(player);
            }
        }catch (Exception exception){exception.printStackTrace();}
    }
}
