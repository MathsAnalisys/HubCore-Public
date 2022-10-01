package it.killergamerpls.hubcore.bungee.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import it.killergamerpls.hubcore.main.HubCore;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class BungeeServerUtils implements PluginMessageListener {

    public int playerCountServer;

    public BungeeServerUtils(){
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(HubCore.get().getPlugin(), "BungeeCord", this);
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(HubCore.get().getPlugin(), "BungeeCord");
        loadSelector();
    }

    public void loadSelector(){
        new BukkitRunnable() {
            @Override
            public void run() {
                getCount("Server");
            }
        }.runTaskTimerAsynchronously(HubCore.get().getPlugin(), 2L, 2L);
    }

    public static void sendToServer(Player player, String server){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF(server);
        }catch (Exception e){e.printStackTrace();}
        player.sendPluginMessage(HubCore.get().getPlugin(), "BungeeCord", byteArrayOutputStream.toByteArray());
    }

    public void getCount(String server){
        try {
            ByteArrayDataOutput byteArrayDataOutput = ByteStreams.newDataOutput();
            byteArrayDataOutput.writeUTF("PlayerCount");
            byteArrayDataOutput.writeUTF(server);
        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player unsed, @NotNull byte[] message) {
        if(!channel.equals("BungeeCord"))return;
        try {
            DataInputStream dataInputStream  = new DataInputStream(new ByteArrayInputStream(message));
            String subChannel = dataInputStream.readUTF();
            if (subChannel.equals("PlayerCount")){
                String server = dataInputStream.readUTF();
                int playerCount = dataInputStream.readInt();
                if (server.equals("Server")){
                    HubCore.get().getBungeeServerUtils().playerCountServer = playerCount;
                }
            }
        }catch (Exception e){
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage(e.getMessage());
            }
        }

    }
}
