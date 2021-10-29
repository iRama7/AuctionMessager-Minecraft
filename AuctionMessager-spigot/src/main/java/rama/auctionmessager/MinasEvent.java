package rama.auctionmessager;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class MinasEvent implements PluginMessageListener {

    private AuctionMessagerSpigot plugin;

    public MinasEvent(AuctionMessagerSpigot plugin){
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player unused, byte[] bytes) {

        FileConfiguration config = plugin.getConfig();
        boolean isMinas = config.getString("server").equalsIgnoreCase("minas");
        if (isMinas) {
            if (!channel.equalsIgnoreCase("my:channel")) {
                return;
            }
            config.set("lang.placeBidEvent", "&6[Subasta] %bidder_name% &fha hecho una oferta de &6%amount% ⛁ &fen tu subasta de %item%!");
            config.set("lang.purchaseEvent", "&6[Subasta] %buyer_name% &fha comprado tu subasta de &6%item% &f por &6%amount% ⛁!");
            String lang_placeBidEvent = ChatColor.translateAlternateColorCodes('&', config.getString("lang.placeBidEvent"));
            String lang_purchaseEvent = ChatColor.translateAlternateColorCodes('&', config.getString("lang.purchaseEvent"));
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subChannel = in.readUTF();
            if (subChannel.equalsIgnoreCase("BidPlaceChannel")) {
                String bidderName = in.readUTF();
                String playerName = in.readUTF();
                String amount = in.readUTF();
                String item = in.readUTF();

                Player seller = Bukkit.getPlayer(playerName);
                seller.sendMessage(lang_placeBidEvent.replaceAll("%bidder_name%", bidderName).replaceAll("%amount%", amount).replaceAll("%item%", item));
            } else if (subChannel.equalsIgnoreCase("PurchaseChannel")) {
                String buyerName = in.readUTF();
                String playerName = in.readUTF();
                String amount = in.readUTF();
                String item = in.readUTF();
                Player seller = Bukkit.getPlayer(playerName);
                seller.sendMessage(lang_purchaseEvent.replaceAll("%buyer_name%", buyerName).replaceAll("%item%", item).replaceAll("%amount%", amount));
            }

        }
    }
}
