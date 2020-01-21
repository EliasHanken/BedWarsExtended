package src.me.streafe.BedWarsExtended.server_utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public interface UtilsWrapper {

    void sendTitle(Player player, String string, ChatColor chatColor);

    String translate(String text);

    String saveLoc(Location loc);

    Location getLocation(String string);
}
