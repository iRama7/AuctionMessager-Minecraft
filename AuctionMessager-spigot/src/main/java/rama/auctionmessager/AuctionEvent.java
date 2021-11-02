package rama.auctionmessager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.qKing12.AuctionMaster.API.Events.BINPurchaseEvent;
import me.qKing12.AuctionMaster.API.Events.PlaceBidEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AuctionEvent implements Listener {

    private final AuctionMessagerSpigot plugin;

    public AuctionEvent(AuctionMessagerSpigot plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBidPlace(PlaceBidEvent e) {
        FileConfiguration config = plugin.getConfig();
        boolean isVilla = config.getString("server").equalsIgnoreCase("villa");
        if (isVilla) {
            Player Bidder = e.getPlayer();
            String SellerName = e.getAuction().getSellerName();
            OfflinePlayer Seller = Bukkit.getOfflinePlayer(SellerName);


            String BidderName = Bidder.getDisplayName();
            String bidAmount = String.valueOf(e.getBidAmount());
            String item = e.getAuction().getItemStack().getType().name().replace("_", " ").toLowerCase();
            String Channel = "BidPlaceChannel";
            if (!Seller.isOnline()) {
                sendMessage(Channel, BidderName, SellerName, bidAmount, item);
            }
        }
    }
    @EventHandler
    public void purchaseEvent(BINPurchaseEvent e) {
        FileConfiguration config = plugin.getConfig();
        boolean isVilla = config.getString("server").equalsIgnoreCase("villa");
        if (isVilla) {
            Player Buyer = e.getPlayer();
            String SellerName = e.getAuction().getSellerName();
            OfflinePlayer Seller = Bukkit.getOfflinePlayer(SellerName);

            String buyerName = Buyer.getDisplayName();
            Double price = e.getPricePaid();
            String pricePaid = String.valueOf(price);
            String item = e.getAuction().getItemStack().getType().name().replace("_", " ").toLowerCase();

            String channel = "PurchaseChannel";
            if (!Seller.isOnline()) {
                sendMessage(channel, buyerName, SellerName, pricePaid, item);
            }
        }
    }

    public void sendMessage(String channel, String data1, String data2, String data3, String data4){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(channel);
        out.writeUTF(data1);
        out.writeUTF(data2);
        out.writeUTF(data3);
        out.writeUTF(data4);
        plugin.getServer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

}
