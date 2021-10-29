package rama.auctionmessager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class AuctionMessagerSpigot extends JavaPlugin{

    public String rutaConfig;

    private static AuctionMessagerSpigot instance;

    @Override
    public void onEnable() {
        setInstance(this);
        registerEvents();
        sendInfo();
        registerConfig();
        getServer().getMessenger().registerIncomingPluginChannel(this, "my:channel", new MinasEvent(this));
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");


    }

    @Override
    public void onDisable() {
        disableInfo();
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }


    public void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new AuctionEvent(this), this);
    }

    public void sendInfo(){
        getLogger().info("El plugin ha cargado correctamente.");
        getLogger().info("por ImRama");
    }
    public void disableInfo(){
        getLogger().info("El plugin ha sido desactivado correctamente.");
        getLogger().info("por ImRama");
    }

    public void registerConfig(){
        File config = new File(this.getDataFolder(),"config.yml");
        rutaConfig = config.getPath();
        if(!config.exists()){
            this.getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

    public static AuctionMessagerSpigot getInstance(){
        return instance;
    }

    private static void setInstance(AuctionMessagerSpigot instance){
        AuctionMessagerSpigot.instance = instance;
    }

}
