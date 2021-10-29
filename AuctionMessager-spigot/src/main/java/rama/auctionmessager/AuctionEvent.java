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

    private AuctionMessagerSpigot plugin;

    public AuctionEvent(AuctionMessagerSpigot plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBidPlace(PlaceBidEvent e) {
        FileConfiguration config = plugin.getConfig();
        boolean isVilla = config.getString("server").equalsIgnoreCase("villa");
        if (isVilla) {
            Player Bidder = e.getPlayer();
            String SellerUUID = e.getAuction().getSellerUUID();
            OfflinePlayer Seller = Bukkit.getOfflinePlayer(SellerUUID);


            String BidderName = Bidder.getDisplayName();
            String bidAmount = String.valueOf(e.getBidAmount());
            String item = e.getAuction().getItemStack().getItemMeta().getDisplayName();
            String Channel = "BidPlaceChannel";
            if (!Seller.isOnline()) {
                sendMessage(Channel, BidderName, SellerUUID, bidAmount, item);
                plugin.getLogger().info("Enviando información al servidor de Minas: ");
                plugin.getLogger().info("Channel: " + Channel);
                plugin.getLogger().info("bidderName: " + BidderName);
                plugin.getLogger().info("sellerUUID: " + SellerUUID);
                plugin.getLogger().info("bidAmount: " + bidAmount);
            }
        }
    }
    @EventHandler
    public void purchaseEvent(BINPurchaseEvent e) {
        FileConfiguration config = plugin.getConfig();
        boolean isVilla = config.getString("server").equalsIgnoreCase("villa");
        if (isVilla) {
            Player Buyer = e.getPlayer();
            String SellerUUID = e.getAuction().getSellerUUID();
            OfflinePlayer Seller = Bukkit.getOfflinePlayer(SellerUUID);

            String buyerName = Buyer.getDisplayName();
            Double price = e.getPricePaid();
            String pricePaid = String.valueOf(price);
            String item = e.getAuction().getItemStack().getItemMeta().getDisplayName();
            String channel = "PurchaseChannel";
            if (!Seller.isOnline()) {
                sendMessage(channel, buyerName, SellerUUID, pricePaid, item);
                plugin.getLogger().info("Enviando información al servidor de Minas: ");
                plugin.getLogger().info("Channel: " + channel);
                plugin.getLogger().info("buyerName: " + buyerName);
                plugin.getLogger().info("sellerUUID: " + SellerUUID);
                plugin.getLogger().info("pricePaid: " + pricePaid);
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
