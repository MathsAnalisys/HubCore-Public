package it.mathanalisys.hubcore.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import lombok.SneakyThrows;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class BungeeCounterListener implements PluginMessageListener{

    @Override
    @SneakyThrows
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equalsIgnoreCase("BungeeCord")){
            return;
        }
        try {
            ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(message);
            String subChannel = byteArrayDataInput.readUTF();
            if (subChannel.equalsIgnoreCase("PlayerCount")){
                String server = byteArrayDataInput.readUTF();
                int playerCount = byteArrayDataInput.readInt();
                PlayerCounterGlobal.setGLOBAL_ONLINE(playerCount);
            }
        }catch (Exception exception){exception.printStackTrace();}
    }
}
