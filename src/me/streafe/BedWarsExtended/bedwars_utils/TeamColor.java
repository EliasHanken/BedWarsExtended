package src.me.streafe.BedWarsExtended.bedwars_utils;

import org.bukkit.Material;

public enum TeamColor {

    RED("RED"),
    BLUE("BLUE"),
    YELLOW("YELLOW"),
    WHITE("WHITE");

    private String name;

    TeamColor(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }




}
