package src.me.streafe.MVPetFollow.server_utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Utils_1_8_R3 implements UtilsWrapper{
    @Override
    public void sendPacket(Player player) {
        IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "text" + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");

        PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);


        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);

    }
}
