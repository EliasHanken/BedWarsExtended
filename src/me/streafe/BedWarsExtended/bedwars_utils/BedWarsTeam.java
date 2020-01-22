package src.me.streafe.BedWarsExtended.bedwars_utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BedWarsTeam {

    private boolean isAlive = true;
    private TeamColor teamColor;
    private List<UUID> teamPlayers;
    private Location spawnLocation;
    private Location bedLocation;

    public BedWarsTeam(TeamColor teamColor){
        this.teamColor = teamColor;
        this.teamPlayers = new ArrayList<>();
    }

    public void removeAllPlayersFromTeam(){
        for(UUID uuid : getTeamPLayers()){
            getTeamPLayers().remove(uuid);
        }
    }

    public void setBedLocation(Location location){
        this.bedLocation = location;
    }

    public Location getBedLocation(){
        return this.bedLocation;
    }

    public List<UUID> getTeamPLayers() {
        return teamPlayers;
    }

    public void addPlayerToTeam(UUID uuid) {
        this.teamPlayers.add(uuid);
    }

    public void removePlayerFromTeam(UUID uuid){
        this.teamPlayers.remove(uuid);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

}
