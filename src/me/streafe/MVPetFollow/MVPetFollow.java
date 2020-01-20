package src.me.streafe.MVPetFollow;

import org.bukkit.plugin.java.JavaPlugin;
import src.me.streafe.MVPetFollow.arenas.ArenaManager;
import src.me.streafe.MVPetFollow.server_utils.UtilsWrapper;
import src.me.streafe.MVPetFollow.server_utils.Utils_1_8_R3;
import src.me.streafe.MVPetFollow.server_utils.Utils_1_9_R1;

public class MVPetFollow extends JavaPlugin {

    private static MVPetFollow instance;

    private UtilsWrapper utils;
    private ArenaManager arenaManager;

    @Override
    public void onEnable(){
        instance = this;
        switch (getVersion()){
            case "1_8_R3":
                utils = new Utils_1_8_R3();
                break;
            case "1_9_R1":
                utils = new Utils_1_9_R1();
                break;
            default:
                getServer().getConsoleSender().sendMessage("MVPetFollow disabled since server running a non supportive version");
                getServer().getPluginManager().disablePlugin(this);
                break;
        }

        arenaManager = new ArenaManager();
    }

    public static String getVersion(){
        return getInstance().getServer().getClass().getPackage().getName().split("//.")[3];
    }

    public static MVPetFollow getInstance() {
        return instance;
    }

    public UtilsWrapper getUtils() {
        return utils;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}
