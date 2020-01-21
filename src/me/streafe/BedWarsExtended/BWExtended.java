package src.me.streafe.BedWarsExtended;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import src.me.streafe.BedWarsExtended.arenas.ArenaManager;
import src.me.streafe.BedWarsExtended.arenas.ArenaWorldManager;
import src.me.streafe.BedWarsExtended.bedwars_utils.BedWarsCommand;
import src.me.streafe.BedWarsExtended.server_utils.UtilsWrapper;
import src.me.streafe.BedWarsExtended.server_utils.Utils_1_8_R3;
import src.me.streafe.BedWarsExtended.server_utils.Utils_1_9_R1;

import java.io.File;
import java.io.IOException;

public class BWExtended extends JavaPlugin {

    private static BWExtended instance;

    private UtilsWrapper utils;
    private ArenaManager arenaManager;
    private WorldEditPlugin worldEditPlugin;
    private ArenaWorldManager arenaWorldManager;

    @Override
    public void onDisable(){
    }

    @Override
    public void onEnable(){
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();

        switch (getVersion()){
            case "v1_8_R3":
                this.utils = new Utils_1_8_R3();
                break;
            case "v1_9_R1":
                this.utils = new Utils_1_9_R1();
                break;
            default:
                getServer().getConsoleSender().sendMessage(getPrefix()+" disabled since server running a non supportive version");
                getServer().getPluginManager().disablePlugin(this);
                break;
        }


        getServer().getConsoleSender().sendMessage(getPrefix() + "§arunning " + getVersion());

        getCommand("bedwars").setExecutor(new BedWarsCommand());

        arenaManager = new ArenaManager();
        arenaWorldManager = new ArenaWorldManager();


        try{
            worldEditPlugin = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        }catch (Exception e){
            e.printStackTrace();
        }

        //LOAD ARENAS
        arenaManager.loadArenas();

    }

    public String getVersion(){
        return this.getServer().getClass().getPackage().getName().replace(".",",").split(",")[3];
    }

    public static BWExtended getInstance() {
        return instance;
    }

    public UtilsWrapper getUtils() {
        return utils;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public WorldEditPlugin getWorldEditPlugin() {
        return worldEditPlugin;
    }

    public String getPrefix(){
        return utils.translate(getConfig().getString("system.prefix"));
    }

    public ArenaWorldManager getArenaWorldManager() {
        return arenaWorldManager;
    }
}
