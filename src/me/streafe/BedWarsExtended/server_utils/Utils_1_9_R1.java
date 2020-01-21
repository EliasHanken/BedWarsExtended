package src.me.streafe.BedWarsExtended.server_utils;


import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Utils_1_9_R1 implements UtilsWrapper{


    @Override
    public void sendTitle(Player player, String string, ChatColor chatColor) {

    }

    public String translate(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    @Override
    public String saveLoc(Location loc) {
        return null;
    }

    @Override
    public Location getLocation(String string) {
        return null;
    }


}
