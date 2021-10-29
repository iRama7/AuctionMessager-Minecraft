package rama.auctionmessager;

import net.md_5.bungee.api.plugin.Plugin;

public final class AuctionMessagerBungeeCord extends Plugin{

    public static AuctionMessagerBungeeCord plugin;

    @Override
    public void onEnable() {
        getProxy().registerChannel("my:channel");
        getProxy().getPluginManager().registerListener(this, new readData());
        plugin = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static AuctionMessagerBungeeCord getPlugin(){
        return plugin;
    }
}
