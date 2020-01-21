package src.me.streafe.BedWarsExtended.arenas;

import org.bukkit.Location;
import org.bukkit.Material;
import src.me.streafe.BedWarsExtended.BWExtended;
import src.me.streafe.BedWarsExtended.bedwars_utils.BedWarsTeam;
import src.me.streafe.BedWarsExtended.bedwars_utils.TeamColor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ArenaManager {

    Map<String,Arena> arenaList;
    Map<String, BedWarsTeam> teamList;

    public ArenaManager(){
        this.arenaList = new HashMap<>();
        this.teamList = new HashMap<>();
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

    public Map<String,BedWarsTeam> getTeamListMap(){
        return teamList;
    }

    public void addNewTeam(String name, TeamColor teamColor, Location spawnLocation){
        if(teamList.get(name)!= null){
            teamList.put(name,new BedWarsTeam(teamColor));
            BWExtended.getInstance().getArenaManager().getTeamListMap().get("name").setSpawnLocation(spawnLocation);
        }
    }
}
