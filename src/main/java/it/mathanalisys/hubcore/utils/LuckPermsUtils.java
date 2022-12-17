package it.mathanalisys.hubcore.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.InheritanceNode;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LuckPermsUtils {

    public String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String translateHexColorCodes(String message) {
        Pattern hexPattern = Pattern.compile("&#([A-Fa-f\\d]{6})");
        char colorChar = ChatColor.COLOR_CHAR;

        Matcher matcher = hexPattern.matcher(message);
        StringBuilder buffer = new StringBuilder(message.length() + 4 * 8);

        while (matcher.find()) {
            String group = matcher.group(1);

            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }

    public static String getPrefix(Player player) {
        String prefix = playerMeta(player).getPrefix();

        return prefix != null ? prefix : "";
    }

    public static String getSuffix(Player player) {
        String suffix = playerMeta(player).getSuffix();

        return suffix != null ? suffix : "";
    }

    public static String getPrefixes(Player player) {
        SortedMap<Integer, String> map = playerMeta(player).getPrefixes();
        StringBuilder prefixes = new StringBuilder();
        for (String prefix : map.values()) {
            prefixes.append(prefix);
        }
        return prefixes.toString();
    }

    public static String getSuffixes(Player player) {
        SortedMap<Integer, String> map = playerMeta(player).getSuffixes();
        StringBuilder suffixes = new StringBuilder();
        for (String prefix : map.values()) {
            suffixes.append(prefix);
        }
        return suffixes.toString();
    }

    public static CachedMetaData playerMeta(Player player) {
        return loadUser(player.getUniqueId()).getCachedData().getMetaData(getApi().getContextManager().getQueryOptions(player));
    }

    public static CachedMetaData groupMeta(String group) {
        return loadGroup(group).getCachedData().getMetaData(getApi().getContextManager().getStaticQueryOptions());
    }

    public static User loadUser(UUID uuid) {
        return getApi().getUserManager().getUser(uuid);
    }

    public static Integer getRankWeight(final Player player) {
        final User user = getApi().getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            return 0;
        }
        final Group group = getApi().getGroupManager().getGroup(user.getPrimaryGroup());
        if (group == null) {
            return 0;
        }
        if (group.getWeight().isEmpty()) {
            return 0;
        }

        return group.getWeight().getAsInt();
    }

    public static Group loadGroup(String group) {
        return getApi().getGroupManager().getGroup(group);
    }

    public static String getRankColor(final Player player) {
        final User user = getApi().getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            final Group group = loadGroup(user.getPrimaryGroup());
            return (group != null && group.getDisplayName() != null) ? CC.translate(group.getDisplayName()) : CC.translate("&7");
        }
        return CC.translate("&7");
    }

    public static LuckPerms getApi() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServer().getServicesManager().getRegistration(LuckPerms.class);
        Validate.notNull(provider);
        return provider.getProvider();
    }

    public boolean isPlaceholderAPIEnabled() {
        return Bukkit.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    public static boolean hasRank(Player player, String rank){
        net.luckperms.api.model.user.User user = LuckPermsUtils.getApi().getUserManager().getUser(player.getUniqueId());

        if(user == null) return false;

        Set<String> groups = user.getNodes().stream()
                .filter(NodeType.INHERITANCE::matches)
                .map(NodeType.INHERITANCE::cast)
                .map(InheritanceNode::getGroupName)
                .collect(Collectors.toSet());
        for(String a : groups){
            if(a.equalsIgnoreCase(rank)) return true;
        }
        return false;
    }
}