package src.me.streafe.BedWarsExtended.arenas;

import org.apache.commons.io.FileUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;

import java.io.File;
import java.io.IOException;

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
}
