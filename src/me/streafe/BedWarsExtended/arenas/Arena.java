package src.me.streafe.BedWarsExtended.arenas;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.util.FileUtil;
import src.me.streafe.BedWarsExtended.BWExtended;
import src.me.streafe.BedWarsExtended.bedwars_utils.BedWarsTeam;
import src.me.streafe.BedWarsExtended.bedwars_utils.TeamColor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Arena {

    private String name;
    private Map<UUID, BedWarsTeam> playersList;
    private Map<BedWarsTeam, Location> bedLocationList;
    private Map<String, BedWarsTeam> teamList;
    private Location lobbyLocation;
    private boolean isAvailable = false;

    public Arena(String name){
        this.teamList = new HashMap<>();
        this.name = name;
        this.playersList = new HashMap<>();
        this.bedLocationList = new HashMap<>();
        this.lobbyLocation = null;
    }

    public boolean hasEverythingSet(){
        if(!(getTeamListMap().size() > 1)){
            return false;
        }else if(lobbyLocation == null){
            return false;
        }
        return true;
    }

    public String sendMissing(){
        if(!(getTeamListMap().size() > 1)){
            return "Missing teams, create at least 2";
        }else if(lobbyLocation == null){
            return "Missing lobby location";
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addNewPlayer(UUID uuid) {
        getPlayersList().put(uuid, null);
    }

    public void addPlayerToTeam(UUID uuid, BedWarsTeam team){
        if(getPlayersList().containsKey(uuid)){
            getPlayersList().put(uuid,team);
            if(!getPlayersList().get(uuid).getTeamPLayers().contains(uuid)){
                getPlayersList().get(uuid).addPlayerToTeam(uuid);
                Bukkit.getPlayer(uuid).sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("Joined team " + getPlayersList().get(uuid).getTeamColor().getPrefix()
                        + " in arena &a" + getName()));
            }else{
                Bukkit.getPlayer(uuid).sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cAlready in team "+getPlayersList().get(uuid).getTeamColor().getPrefix()));
            }

        }else{
            Bukkit.getPlayer(uuid).sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cNot in game!"));
        }

    }

    public void removePlayerFromTeam(UUID uuid){
        try{
            getPlayersList().get(uuid).removePlayerFromTeam(uuid);
            Bukkit.getPlayer(uuid).sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cRemoved from team " + getPlayersList()
            .get(uuid).getTeamColor().getPrefix()));
        }catch (Exception e){
            Bukkit.getPlayer(uuid).sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cNot in a team!"));
        }




    }

    public Map<String,BedWarsTeam> getTeamListMap(){
        return teamList;
    }

    public BedWarsTeam getTeam(String name){
        for(Map.Entry<String, BedWarsTeam> entry : getTeamListMap().entrySet()){
            if(entry.getKey().equalsIgnoreCase(name)){
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean teamExists(String name){
        if(teamList.get(name) != null){
            return true;
        }
        return false;
    }

    public void addNewTeam(String name, TeamColor teamColor, Location spawnLocation){
        if(teamList.get(name)== null){
            teamList.put(name,new BedWarsTeam(teamColor));
            getTeamListMap().get(name).setSpawnLocation(spawnLocation);
        }
    }

    public void removeAllPlayersFromGame(){
        for(Map.Entry<UUID, BedWarsTeam> entry : getPlayersList().entrySet()){
            getPlayersList().remove(entry.getKey());
            Bukkit.getPlayer(entry.getKey()).sendMessage(BWExtended.getInstance().getUtils().translate(BWExtended.getInstance().getPrefix() + "&cYou were removed from the arena &a" + getName()));
        }
    }

    public void removePlayerFromGame(UUID uuid){
        for(Map.Entry<String, BedWarsTeam> entry : getTeamListMap().entrySet()){
            entry.getValue().removePlayerFromTeam(uuid);
        }
    }

    public void removeAllPlayersFromAllTeams(){
        for(Map.Entry<String, BedWarsTeam> entry : getTeamListMap().entrySet()){
            entry.getValue().removeAllPlayersFromTeam();
            Bukkit.getPlayer(entry.getKey()).sendMessage(BWExtended.getInstance().getUtils().translate(BWExtended.getInstance().getPrefix() + "&cYou were removed from the team " + entry.getValue().getTeamColor().getPrefix()));
        }
    }

    public void saveArena(){
        File folder = new File(BWExtended.getInstance().getDataFolder() + "/","arenas");
        if(!folder.exists()){
            folder.mkdir();
        }
        File file = new File(BWExtended.getInstance().getDataFolder() +"/arenas/", getName() + ".yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
            yamlConfiguration.set("name",getName());
            yamlConfiguration.set("lobbyLocation",BWExtended.getInstance().getUtils().saveLoc(lobbyLocation));
            for(Map.Entry<String,BedWarsTeam> entry : teamList.entrySet()){
                yamlConfiguration.set("teams."+entry.getKey().toUpperCase() + ".spawnLocation",BWExtended.getInstance().getUtils().saveLoc(entry.getValue().getSpawnLocation()));
            }
            try {
                yamlConfiguration.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteArenaFile(){
        File file = new File(BWExtended.getInstance().getDataFolder() + "/arenas/",getName() + ".yml");
        if(file.exists()){
            file.delete();
        }
    }


    public Map<BedWarsTeam, Location> getBedLocationList() {
        return bedLocationList;
    }

    public void setBedLocation(BedWarsTeam team, Location location) {
        try{
            getBedLocationList().remove(team);
            getBedLocationList().put(team,location);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean bool){
        this.isAvailable = bool;
    }

    public Map<UUID, BedWarsTeam> getPlayersList() {
        return playersList;
    }

    public boolean playerAlreadyInGame(UUID uuid){
        if(playersList.containsKey(uuid)){
            return true;
        }
        return false;
    }

    public boolean playerAlreadyInTeam(UUID uuid){
        for(Map.Entry<String, BedWarsTeam> entry : teamList.entrySet()){
            if(entry.getValue().getTeamPLayers().contains(uuid)) return true;
        }
        return false;
    }

    public BedWarsTeam getTeamFromPlayer(UUID uuid){
        for(Map.Entry<UUID,BedWarsTeam> entry : getPlayersList().entrySet()){
            if(entry.getKey() == uuid){
                return entry.getValue();
            }
        }
        return null;
    }

    public Location getLobbyLocation() {
        return lobbyLocation;
    }

    public void setLobbyLocation(Location lobbyLocation) {
        this.lobbyLocation = lobbyLocation;
    }
}
