package it.killergamerpls.hubcore.bungee;

import it.killergamerpls.hubcore.main.HubCore;
import lombok.Setter;
import org.bukkit.Bukkit;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class PlayerCounterGlobal extends Thread{

    @Setter public static int GLOBAL_ONLINE = 1;

    public PlayerCounterGlobal(){
        setName("HubCore-GlobalOnline");
    }

    @Override
    public void run() {
        while (true){
            counterBungee();
            try {
                sleep(250);
            }catch (Exception exception){exception.printStackTrace();}
        }
    }

    //Function for count global Online Server
    private void counterBungee(){
        try {
            ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayInputStream);
            dataOutputStream.writeUTF("PlayerCount");
            dataOutputStream.writeUTF("ALL");
            Bukkit.getServer().sendPluginMessage(HubCore.get().getPlugin(), "BungeeCord", byteArrayInputStream.toByteArray());
        }catch (Exception exception){exception.printStackTrace();}
    }
}
