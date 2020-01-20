package src.me.streafe.BedWarsExtended.bedwars_utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.me.streafe.BedWarsExtended.BWExtended;
import src.me.streafe.BedWarsExtended.arenas.ArenaManager;

public class BedWarsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String args[]) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(cmd.getName().equalsIgnoreCase("bedwars")){
                if(args.length == 0){
                    player.sendMessage(BWExtended.getInstance().getUtils().translate("&c/bw <args>..."));
                    return true;
                }else if(args.length == 2 && args[0].equalsIgnoreCase("create")){
                    if(!BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        BWExtended.getInstance().getArenaManager().createArena(args[1]);
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&fcreated the arena &a" + args[1]));
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&farena &a" + args[1] + " &falready exists"));
                    }
                    return true;
                }else if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        BWExtended.getInstance().getArenaManager().deleteArena(args[1]);
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&fdeleted the arena &a" + args[1]));
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&c"+args[1]+" doesn't exist"));
                    }
                    return true;

                }
            }
        }

        return true;
    }
}
