package src.me.streafe.BedWarsExtended.bedwars_utils;

import org.bukkit.Material;
import src.me.streafe.BedWarsExtended.BWExtended;

public enum TeamColor {

    RED("RED","&c&lRED &r"),
    BLUE("BLUE","&b&lBLUE &r"),
    YELLOW("YELLOW","&e&lYELLOW &r"),
    WHITE("WHITE","&f&lWHITE &r");

    private String name;
    private String prefix;

    TeamColor(String name, String prefix){
        this.name = name;
        this.prefix = prefix;
    }

    public String getName(){
        return name;
    }

    public String getPrefix(){
        return BWExtended.getInstance().getUtils().translate(prefix);
    }




}
