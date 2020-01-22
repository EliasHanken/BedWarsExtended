package src.me.streafe.BedWarsExtended.arenas;

import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;
import src.me.streafe.BedWarsExtended.BWExtended;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ArenaWorldManager {

    public void createNewWorld(String name){
        WorldCreator wc = new WorldCreator(name);

        wc.type(WorldType.FLAT);
        wc.generatorSettings("2;0;1;");

        wc.createWorld();
    }

    public void deleteWorld(String name){
        Bukkit.unloadWorld(name,true);
        try {
            FileUtils.deleteDirectory(new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void teleportToWorld(Player player, String name){

        Location newWorld = new Location(Bukkit.getWorld(name),0,50,0);

        try{
            player.teleport(newWorld);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadCustomWorlds(){
        for(Map.Entry<String,Arena> entry : BWExtended.getInstance().getArenaManager().getAllArenas().entrySet()){
            try{
                WorldCreator wc = new WorldCreator(entry.getValue().getName());
                wc.createWorld();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
