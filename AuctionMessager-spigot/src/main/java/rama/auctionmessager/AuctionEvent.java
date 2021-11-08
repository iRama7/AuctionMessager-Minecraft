package rama.auctionmessager;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.qKing12.AuctionMaster.API.Events.BINPurchaseEvent;
import me.qKing12.AuctionMaster.API.Events.OutbidEvent;
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
            String item = e.getAuction().getItemStack().getType().name();
            switch(item){
                case "DIAMOND_SWORD":
                    item = "Espada de diamante";
                    break;
                case "STONE_SWORD":
                    item = "Espada de piedra";
                    break;
                case "COAL_ORE":
                    item = "Mena de carbon";
                    break;
                case "IRON_ORE":
                    item = "Mena de Hierro";
                    break;
                case "COPPER_ORE":
                    item = "Mena de cobre";
                    break;
                case "GOLD_ORE":
                    item = "Mena de oro";
                    break;
                case "DIAMOND_ORE":
                    item = "Mena de diamante";
                    break;
                case "EMERALD_ORE":
                    item = "Mena de esmeralda";
                    break;
                case "ANCIENT_DEBRIS":
                    item = "Escombros Ancestrales";
                    break;
                case "COAL":
                    item = "Carbon";
                    break;
                case "IRON":
                    item = "Hierro";
                    break;
                case "COPPER_INGOT":
                    item = "Lingote de Cobre";
                    break;
                case "GOLD_INGOT":
                    item = "Lingote de oro";
                    break;
                case "DIAMOND":
                    item = "Diamante";
                    break;
                case "EMERALD":
                    item = "Esmeralda";
                    break;
                case "NETHERITE_INGOT":
                    item = "Lingote de Netherita";
                    break;
                case "BLOCK_OF_COAL":
                    item = "Bloque de Carbon";
                    break;
                case "BLOCK_OF_IRON":
                    item = "Bloque de Hierro";
                    break;
                case "BLOCK_OF_COPPER":
                    item = "Bloque de Cobre";
                    break;
                case "BLOCK_OF_GOLD":
                    item = "Bloque de oro";
                    break;
                case "BLOCK_OF_DIAMOND":
                    item = "Bloque de Diamante";
                    break;
                case "BLOCK_OF_EMERALD":
                    item = "Bloque de Esmeralda";
                    break;
                case "BLOCK_OF_NETHERITE":
                    item = "Lingote de Netherita";
                    break;
                case "RAW_COAL":
                    item = "Carbon Crudo";
                    break;
                case "RAW_IRON":
                    item = "Hierro Crudo";
                    break;
                case "RAW_COPPER":
                    item = "Cobre Crudo";
                    break;
                case "DIAMOND_PICKAXE":
                    item = "Pico de Diamante";
                    break;
                case "STONE_PICKAXE":
                    item = "Pico de piedra";
                    break;
                case "NETHERITE_PICKAXE":
                    item = "Pico de Netherita";
                    break;
                case "NETHERITE_SWORD":
                    item = "Espada de Netherita";
                    break;
                case "BEACON":
                    item = "Faro";
                    break;
                case "IRON_HELMET":
                    item = "Casco de hierro";
                    break;
                case "IRON_CHESPLATE":
                    item = "Peto de Hierro";
                    break;
                case "IRON_LEGGINGS":
                    item = "Perneras de hierro";
                    break;
                case "IRON_BOOTS":
                    item = "Botas de Hierro";
                    break;
                case "DIAMOND_HELMET":
                    item = "Casco de Diamante";
                    break;
                case "DIAMOND_CHESPLATE":
                    item = "Peto de Diamante";
                    break;
                case "DIAMOND_LEGGINGS":
                    item = "Perneras de Diamante";
                    break;
                case "DIAMOND_BOOTS":
                    item = "Botas de Diamante";
                    break;
                case "NETHERITE_HELMET":
                    item = "Casco de Netherita";
                    break;
                case "NETHERITE_CHESPLATE":
                    item = "Peto de Netherita";
                    break;
                case "NETHERITE_LEGGINGS":
                    item = "Perneras de Netherita";
                    break;
                case "NETHERITE_BOOTS":
                    item = "Botas de Netherita";
                    break;
                case "ANVIL":
                    item = "Yunque";
                    break;
                default: item = e.getAuction().getItemStack().getType().name().replace("_", " ").toLowerCase();
            }
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
            String item = e.getAuction().getItemStack().getType().name();
            switch(item){
                case "DIAMOND_SWORD":
                    item = "Espada de diamante";
                    break;
                case "STONE_SWORD":
                    item = "Espada de piedra";
                    break;
                case "COAL_ORE":
                    item = "Mena de carbon";
                    break;
                case "IRON_ORE":
                    item = "Mena de Hierro";
                    break;
                case "COPPER_ORE":
                    item = "Mena de cobre";
                    break;
                case "GOLD_ORE":
                    item = "Mena de oro";
                    break;
                case "DIAMOND_ORE":
                    item = "Mena de diamante";
                    break;
                case "EMERALD_ORE":
                    item = "Mena de esmeralda";
                    break;
                case "ANCIENT_DEBRIS":
                    item = "Escombros Ancestrales";
                    break;
                case "COAL":
                    item = "Carbon";
                    break;
                case "IRON":
                    item = "Hierro";
                    break;
                case "COPPER_INGOT":
                    item = "Lingote de Cobre";
                    break;
                case "GOLD_INGOT":
                    item = "Lingote de oro";
                    break;
                case "DIAMOND":
                    item = "Diamante";
                    break;
                case "EMERALD":
                    item = "Esmeralda";
                    break;
                case "NETHERITE_INGOT":
                    item = "Lingote de Netherita";
                    break;
                case "BLOCK_OF_COAL":
                    item = "Bloque de Carbon";
                    break;
                case "BLOCK_OF_IRON":
                    item = "Bloque de Hierro";
                    break;
                case "BLOCK_OF_COPPER":
                    item = "Bloque de Cobre";
                    break;
                case "BLOCK_OF_GOLD":
                    item = "Bloque de oro";
                    break;
                case "BLOCK_OF_DIAMOND":
                    item = "Bloque de Diamante";
                    break;
                case "BLOCK_OF_EMERALD":
                    item = "Bloque de Esmeralda";
                    break;
                case "RAW_COAL":
                    item = "Carbon Crudo";
                    break;
                case "RAW_IRON":
                    item = "Hierro Crudo";
                    break;
                case "RAW_COPPER":
                    item = "Cobre Crudo";
                    break;
                case "DIAMOND_PICKAXE":
                    item = "Pico de Diamante";
                    break;
                case "STONE_PICKAXE":
                    item = "Pico de piedra";
                    break;
                case "NETHERITE_PICKAXE":
                    item = "Pico de Netherita";
                    break;
                case "NETHERITE_SWORD":
                    item = "Espada de Netherita";
                    break;
                case "BEACON":
                    item = "Faro";
                    break;
                case "IRON_HELMET":
                    item = "Casco de hierro";
                    break;
                case "IRON_CHESTPLATE":
                    item = "Peto de Hierro";
                    break;
                case "IRON_LEGGINGS":
                    item = "Perneras de hierro";
                    break;
                case "IRON_BOOTS":
                    item = "Botas de Hierro";
                    break;
                case "DIAMOND_HELMET":
                    item = "Casco de Diamante";
                    break;
                case "DIAMOND_CHESTPLATE":
                    item = "Peto de Diamante";
                    break;
                case "DIAMOND_LEGGINGS":
                    item = "Perneras de Diamante";
                    break;
                case "DIAMOND_BOOTS":
                    item = "Botas de Diamante";
                    break;
                case "NETHERITE_HELMET":
                    item = "Casco de Netherita";
                    break;
                case "NETHERITE_CHESTPLATE":
                    item = "Peto de Netherita";
                    break;
                case "NETHERITE_LEGGINGS":
                    item = "Perneras de Netherita";
                    break;
                case "NETHERITE_BOOTS":
                    item = "Botas de Netherita";
                    break;
                case "ANVIL":
                    item = "Yunque";
                    break;
                default: item = e.getAuction().getItemStack().getType().name().replace("_", " ").toLowerCase();
            }

            String channel = "PurchaseChannel";
            if (!Seller.isOnline()) {
                sendMessage(channel, buyerName, SellerName, pricePaid, item);
            }
        }
    }
    @EventHandler
    public void outBidEvent(OutbidEvent e){
        FileConfiguration config = plugin.getConfig();
        boolean isVilla = config.getString("server").equalsIgnoreCase("villa");
        if (isVilla) {
            String SellerName = e.getAuction().getSellerName();
            OfflinePlayer Seller = Bukkit.getOfflinePlayer(SellerName);
            Player outBidder = e.getOutbidder();
            Player outBidded = e.getPlayer();


            String outBidderName = outBidder.getName();
            String outBiddedName = outBidded.getName();
            String bidAmount = String.valueOf(e.getBidAmount());
            String item = e.getAuction().getItemStack().getType().name();
            switch(item){
                case "DIAMOND_SWORD":
                    item = "Espada de diamante";
                    break;
                case "STONE_SWORD":
                    item = "Espada de piedra";
                    break;
                case "COAL_ORE":
                    item = "Mena de carbon";
                    break;
                case "IRON_ORE":
                    item = "Mena de Hierro";
                    break;
                case "COPPER_ORE":
                    item = "Mena de cobre";
                    break;
                case "GOLD_ORE":
                    item = "Mena de oro";
                    break;
                case "DIAMOND_ORE":
                    item = "Mena de diamante";
                    break;
                case "EMERALD_ORE":
                    item = "Mena de esmeralda";
                    break;
                case "ANCIENT_DEBRIS":
                    item = "Escombros Ancestrales";
                    break;
                case "COAL":
                    item = "Carbon";
                    break;
                case "IRON":
                    item = "Hierro";
                    break;
                case "COPPER_INGOT":
                    item = "Lingote de Cobre";
                    break;
                case "GOLD_INGOT":
                    item = "Lingote de oro";
                    break;
                case "DIAMOND":
                    item = "Diamante";
                    break;
                case "EMERALD":
                    item = "Esmeralda";
                    break;
                case "NETHERITE_INGOT":
                    item = "Lingote de Netherita";
                    break;
                case "BLOCK_OF_COAL":
                    item = "Bloque de Carbon";
                    break;
                case "BLOCK_OF_IRON":
                    item = "Bloque de Hierro";
                    break;
                case "BLOCK_OF_COPPER":
                    item = "Bloque de Cobre";
                    break;
                case "BLOCK_OF_GOLD":
                    item = "Bloque de oro";
                    break;
                case "BLOCK_OF_DIAMOND":
                    item = "Bloque de Diamante";
                    break;
                case "BLOCK_OF_EMERALD":
                    item = "Bloque de Esmeralda";
                    break;
                case "BLOCK_OF_NETHERITE":
                    item = "Lingote de Netherita";
                    break;
                case "RAW_COAL":
                    item = "Carbon Crudo";
                    break;
                case "RAW_IRON":
                    item = "Hierro Crudo";
                    break;
                case "RAW_COPPER":
                    item = "Cobre Crudo";
                    break;
                case "DIAMOND_PICKAXE":
                    item = "Pico de Diamante";
                    break;
                case "STONE_PICKAXE":
                    item = "Pico de piedra";
                    break;
                case "NETHERITE_PICKAXE":
                    item = "Pico de Netherita";
                    break;
                case "NETHERITE_SWORD":
                    item = "Espada de Netherita";
                    break;
                case "BEACON":
                    item = "Faro";
                    break;
                case "IRON_HELMET":
                    item = "Casco de hierro";
                    break;
                case "IRON_CHESPLATE":
                    item = "Peto de Hierro";
                    break;
                case "IRON_LEGGINGS":
                    item = "Perneras de hierro";
                    break;
                case "IRON_BOOTS":
                    item = "Botas de Hierro";
                    break;
                case "DIAMOND_HELMET":
                    item = "Casco de Diamante";
                    break;
                case "DIAMOND_CHESPLATE":
                    item = "Peto de Diamante";
                    break;
                case "DIAMOND_LEGGINGS":
                    item = "Perneras de Diamante";
                    break;
                case "DIAMOND_BOOTS":
                    item = "Botas de Diamante";
                    break;
                case "NETHERITE_HELMET":
                    item = "Casco de Netherita";
                    break;
                case "NETHERITE_CHESPLATE":
                    item = "Peto de Netherita";
                    break;
                case "NETHERITE_LEGGINGS":
                    item = "Perneras de Netherita";
                    break;
                case "NETHERITE_BOOTS":
                    item = "Botas de Netherita";
                    break;
                case "ANVIL":
                    item = "Yunque";
                    break;
                default: item = e.getAuction().getItemStack().getType().name().replace("_", " ").toLowerCase();
            }

            String channel = "OutbidChannel";
            if (!Seller.isOnline()) {
                sendMessage(channel, outBidderName, outBiddedName, bidAmount, item);
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
