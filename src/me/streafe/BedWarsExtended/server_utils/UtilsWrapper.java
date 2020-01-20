package src.me.streafe.BedWarsExtended.server_utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface UtilsWrapper {

    void sendTitle(Player player, String string, ChatColor chatColor);

    String translate(String text);
}
