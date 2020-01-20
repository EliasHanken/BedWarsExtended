package src.me.streafe.BedWarsExtended.arenas;

import org.bukkit.Location;
import src.me.streafe.BedWarsExtended.bedwars_utils.BedWarsTeam;

import java.util.*;

public class Arena {

    private String name;
    private Map<UUID, Integer> playersList;
    private Map<BedWarsTeam, Location> bedLocationList;
    private boolean isAvailable = false;

    public Arena(String name){
        this.name = name;
        this.playersList = new HashMap<>();
        this.bedLocationList = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<UUID, Integer> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(Map<UUID, Integer> playersList) {
        this.playersList = playersList;
    }


    public Map<BedWarsTeam, Location> getBedLocationList() {
        return bedLocationList;
    }

    public void setBedLocationList(Map<BedWarsTeam, Location> bedLocationList) {
        this.bedLocationList = bedLocationList;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
