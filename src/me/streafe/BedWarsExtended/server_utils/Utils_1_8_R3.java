package src.me.streafe.BedWarsExtended.server_utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;
import src.me.streafe.BedWarsExtended.BWExtended;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils_1_8_R3 implements UtilsWrapper{

    @Override
    public void sendTitle(Player player, String string, ChatColor chatColor) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "text" + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
    }

    public String translate(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    @Override
    public String saveLoc(Location loc) {
        String location = loc.getWorld().getName() + ":" + loc.getX() + ":" + loc.getY() + ":" + loc.getZ();
        return location;
    }

    @Override
    public Location getLocation(String string) {
        String[] l = string.split(":");
        return new Location(Bukkit.getWorld(l[0]), Double.parseDouble(l[1]), Double.parseDouble(l[2]), Double.parseDouble(l[3]));
    }
}
