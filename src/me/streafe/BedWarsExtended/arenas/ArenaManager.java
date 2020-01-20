package src.me.streafe.BedWarsExtended.arenas;

import src.me.streafe.BedWarsExtended.BWExtended;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ArenaManager {

    Map<String,Arena> arenaList;

    public ArenaManager(){
        this.arenaList = new HashMap<>();
    }

    public void createArena(String name){
        arenaList.put(name,new Arena(name));
    }

    public void deleteArena(String name){
        arenaList.remove(name);
    }

    public boolean arenaExists(String name){
        if(arenaList.containsKey(name)){
            return true;
        }

        return false;
    }


    public void saveAllArenas(){
        for(int i = 1; i < arenaList.size(); i++){
            File file = new File(BWExtended.getInstance().getDataFolder() + arenaList.get(i).getName()+".yml");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
