package src.me.streafe.BedWarsExtended.bedwars_utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.UUID;

public class BedWarsTeam {

    private boolean isAlive = true;
    private TeamColor teamColor;
    private List<UUID> teamPLayers;
    private Location spawnLocation;

    public BedWarsTeam(TeamColor teamColor){
        this.teamColor = teamColor;
    }


    public List<UUID> getTeamPLayers() {
        return teamPLayers;
    }

    public void addPlayerToTeam(UUID uuid) {
        this.teamPLayers.add(uuid);
    }

    public void removePlayerFromTeam(UUID uuid){
        this.teamPLayers.remove(uuid);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public TeamColor getWoolColor() {
        return teamColor;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
}
