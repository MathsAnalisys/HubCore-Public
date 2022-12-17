package it.mathanalisys.hubcore.cosmetics.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ColorChatApply{

    NONE("None", ChatColor.WHITE),
    GREEN("Green", ChatColor.GREEN),
    RED("Red", ChatColor.RED),
    BLUE("Blue", ChatColor.BLUE),
    YELLOW("Yellow", ChatColor.YELLOW),
    AQUA("Aqua", ChatColor.AQUA),
    BLACK("Black", ChatColor.BLACK),
    GRAY("Gray", ChatColor.GRAY),
    DARK_GREEN("Dark Green", ChatColor.DARK_GREEN),
    DARK_RED("Dark Red", ChatColor.DARK_RED);

    //Add more color if you want!
    private String name;
    private ChatColor color;


    public static ColorChatApply getName(String input){
        return Arrays.stream(values())
                .filter(colorChatApply -> colorChatApply.name.equalsIgnoreCase(input) || colorChatApply.getName().equalsIgnoreCase(input))
                .findFirst()
                .orElse(null);
    }

    public boolean hasPermission(Player player){
        return player.hasPermission("hubcore.colorchat." + name.toLowerCase());
    }

    public String AllPermissionColor(){
        return "hubcore.colorchat.*";
    }

    public String PermissionColor(){
        return "hubcore.colorchat." + name.toLowerCase();
    }


}
