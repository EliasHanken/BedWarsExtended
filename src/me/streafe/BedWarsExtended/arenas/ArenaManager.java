package src.me.streafe.BedWarsExtended.arenas;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import src.me.streafe.BedWarsExtended.BWExtended;
import src.me.streafe.BedWarsExtended.bedwars_utils.BedWarsTeam;
import src.me.streafe.BedWarsExtended.bedwars_utils.TeamColor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ArenaManager {

    private Map<String,Arena> arenaList;

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

    public Arena getArena(String name){
        if(arenaExists(name)){
            for(Map.Entry<String,Arena> entry : getAllArenas().entrySet()){
                if(entry.getValue().getName().equalsIgnoreCase(name)){
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public void loadArenas(){
        try{
            File dataFolder = new File(BWExtended.getInstance().getDataFolder(),"arenas");
            File[] files = dataFolder.listFiles();

            assert files != null;
            for(File f : files){
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
                BWExtended.getInstance().getArenaManager().createArena(yaml.getString("name"));
                BWExtended.getInstance().getArenaManager().getArena(yaml.getString("name")).setLobbyLocation(BWExtended.getInstance().getUtils().getLocation(yaml.getString("lobbyLocation")));

                Arena a = BWExtended.getInstance().getArenaManager().getArena(yaml.getString("name"));

                for(String keys : yaml.getConfigurationSection("teams").getKeys(false)){
                    a.addNewTeam(keys,TeamColor.valueOf(keys),BWExtended.getInstance().getUtils().getLocation(yaml.getString("teams." + keys + ".spawnLocation")));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public Map<String,Arena> getAllArenas(){
        return arenaList;
    }


}
