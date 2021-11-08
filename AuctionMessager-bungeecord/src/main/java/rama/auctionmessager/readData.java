package rama.auctionmessager;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class readData implements Listener {

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e){
        if(e.getTag().equalsIgnoreCase("BungeeCord")){
            DataInputStream in = new DataInputStream(new ByteArrayInputStream((e.getData())));
            try{
                String channel = in.readUTF();
                if(channel.equalsIgnoreCase("BidPlaceChannel")){
                    String bidderName = in.readUTF();
                    String SellerName = in.readUTF();
                    String bidAmount = in.readUTF();
                    String item = in.readUTF();
                    sendData.sendData(channel, bidderName, SellerName, bidAmount, item);
                }else if(channel.equalsIgnoreCase("PurchaseChannel")){
                    String buyerName = in.readUTF();
                    String SellerName = in.readUTF();
                    String amount = in.readUTF();
                    String item = in.readUTF();
                    sendData.sendData(channel, buyerName, SellerName, amount, item);
                }else if(channel.equalsIgnoreCase("OutbidChannel")){
                    String outBidderName = in.readUTF();
                    String outBiddedName = in.readUTF();
                    String bidAmount = in.readUTF();
                    String item = in.readUTF();
                    sendData.sendData(channel, outBidderName, outBiddedName, bidAmount, item);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
