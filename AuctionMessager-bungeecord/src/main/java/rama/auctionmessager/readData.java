package rama.auctionmessager;

import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
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
                    String SellerUUID = in.readUTF();
                    String bidAmount = in.readUTF();
                    String item = in.readUTF();
                    sendData.sendData(channel, bidderName, SellerUUID, bidAmount, item);
                }else if(channel.equalsIgnoreCase("PurchaseChannel")){

                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
