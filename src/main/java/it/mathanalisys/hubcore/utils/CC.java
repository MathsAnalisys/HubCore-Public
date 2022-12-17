package it.mathanalisys.hubcore.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String translate(String input){
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> translate(List<String> input){
        List<String> toReturn = new ArrayList<>();
        for(String s : input){
            toReturn.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return toReturn;
    }
}
