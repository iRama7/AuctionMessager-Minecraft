package rama.auctionmessager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Map;

import static rama.auctionmessager.AuctionMessagerBungeeCord.plugin;

public class sendData {
    public static void sendData(String Channel, String data1, String data2, String data3, String data4){
        Collection<ProxiedPlayer> networkPlayers = ProxyServer.getInstance().getPlayers();
        if ( networkPlayers == null || networkPlayers.isEmpty() ){
            return;
        }
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        try{
            out.writeUTF(Channel);
            out.writeUTF(data1);
            out.writeUTF(data2);
            out.writeUTF(data3);
            out.writeUTF(data4);
            Map<String, ServerInfo> servers = ProxyServer.getInstance().getServers();
            for (Map.Entry<String, ServerInfo> en : servers.entrySet()) {
                String name = en.getKey();
                ServerInfo server = ProxyServer.getInstance().getServerInfo(name);
                server.sendData("my:channel", out.toByteArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        plugin.getProxy().getLogger().warning("Debug 01");
    }
}
