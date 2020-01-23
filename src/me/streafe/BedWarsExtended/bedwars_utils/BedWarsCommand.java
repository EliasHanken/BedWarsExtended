package src.me.streafe.BedWarsExtended.bedwars_utils;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import src.me.streafe.BedWarsExtended.BWExtended;
import src.me.streafe.BedWarsExtended.arenas.Arena;
import src.me.streafe.BedWarsExtended.arenas.ArenaManager;

import java.util.Map;
import java.util.UUID;

public class BedWarsCommand implements CommandExecutor {

    BWExtended bw;

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String args[]) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;

            if(cmd.getName().equalsIgnoreCase("bedwars")){
                if(args.length == 0){
                    player.sendMessage(BWExtended.getInstance().getUtils().translate("&c/bw <args>..."));
                    return true;
                }else if(args.length == 1 && args[0].equalsIgnoreCase("help")){
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7---&eArena List&7---"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw create &7<&aarenaName&7>"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw createNewTeam &7<&ateamName&7> <&aarenaName&7> &7(sets your loc to team spawn loc)"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw list &7(gets the list of all arenas)"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw saveArena &7<&aarenaName&7> &7(saves arena to files so it can load on restart)"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw sql saveArena &7<&aarenaName&7> &7(saving arena to SQL)"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw worldManager create &7<&aworldName&7>"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw worldManager tp &7<&aworldName&7>"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&e/bw worldManager delete &7<&aworldName&7>"));

                }else if(args.length == 2 && args[0].equalsIgnoreCase("setLobby")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        BWExtended.getInstance().getArenaManager().getArena(args[1]).setLobbyLocation(player.getLocation());
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7Set lobby location for &a" + args[1]));
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cThat arena doesn't exist"));
                    }
                }else if(args.length == 2 && args[0].equalsIgnoreCase("create") && args[1] != null){
                    if(!BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        BWExtended.getInstance().getArenaManager().createArena(args[1]);
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&fcreated the arena &a" + args[1]));
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&farena &a" + args[1] + " &falready exists"));
                    }
                    return true;
                }else if(args.length == 3 && args[0].equalsIgnoreCase("createNewTeam")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[2]) && !BWExtended.getInstance().getArenaManager().getArena(args[2]).teamExists(args[1])){
                        if(!BWExtended.getInstance().getArenaManager().getArena(args[2]).teamExists(args[1])){
                            TeamColor test = null;
                            try{
                                test = TeamColor.valueOf(args[1].toUpperCase());
                            }catch (Exception e){
                                player.sendMessage("That is not a valid team");
                                player.sendMessage("use: §cred §agreen §eyellow §bblue");
                                return true;
                            }
                            BWExtended.getInstance().getArenaManager().getArena(args[2]).addNewTeam(args[1],TeamColor.valueOf(args[1].toUpperCase()),player.getLocation());
                            player.sendMessage(BWExtended.getInstance().getUtils().translate(BWExtended.getInstance().getPrefix() + "&7Set location for team " + test.getPrefix() +" in arena: &a"+ args[2]));
                            player.sendMessage(BWExtended.getInstance().getUtils().translate(BWExtended.getInstance().getPrefix() + "&a" + player.getLocation().getBlockX() + ", "
                            + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ()));
                        }else{
                            player.sendMessage("team already exists");
                        }
                    }else{
                        player.sendMessage("team already exists");
                    }


                }else if(args.length == 2 && args[0].equalsIgnoreCase("delete") && args[1] != null){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        try{
                            BWExtended.getInstance().getArenaManager().getArena(args[1]).removeAllPlayersFromGame();
                            BWExtended.getInstance().getArenaManager().getArena(args[1]).removeAllPlayersFromAllTeams();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        BWExtended.getInstance().getArenaManager().getArena(args[1]).deleteArenaFile();
                        BWExtended.getInstance().getArenaManager().deleteArena(args[1]);
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&fdeleted the arena &a" + args[1]));
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&c"+args[1]+" doesn't exist"));
                    }
                    return true;

                }else if(args.length == 1 && args[0].equalsIgnoreCase("list")){
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7---&eArena List&7---"));
                    StringBuilder arenas = new StringBuilder();
                    for(Map.Entry<String, Arena> entry : BWExtended.getInstance().getArenaManager().getAllArenas().entrySet()){
                        arenas.append(BWExtended.getInstance().getUtils().translate("&a"+entry.getKey() + "&7, "));
                    }
                    player.sendMessage(BWExtended.getInstance().getPrefix() + arenas);
                }else if(args.length == 2 && args[0].equalsIgnoreCase("saveArena")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        if(BWExtended.getInstance().getArenaManager().getArena(args[1]).hasEverythingSet()){
                            BWExtended.getInstance().getArenaManager().getArena(args[1]).saveArena();
                            player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7Saved &a" + args[1] + " &7to files"));
                        }else{
                            player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getArenaManager().getArena(args[1]).sendMissing());
                        }
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cError: &a"+args[1]+" is null"));

                    }
                    return true;
                }else if(args.length == 3 && args[0].equalsIgnoreCase("worldManager")){
                    if(args[1].equalsIgnoreCase("create")){
                        BWExtended.getInstance().getArenaWorldManager().createNewWorld(args[2]);
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7Created a new world &a" + args[2]));
                    }else if(args[1].equalsIgnoreCase("tp")){
                        player.sendMessage("teleporting...");
                        BWExtended.getInstance().getArenaWorldManager().teleportToWorld(player,args[2]);
                    }else if(args[1].equalsIgnoreCase("delete")){
                        if(args[2].equalsIgnoreCase("world")){
                            return true;
                        }
                        BWExtended.getInstance().getArenaWorldManager().deleteWorld(args[2]);
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cdeleted world &a" + args[2]));
                    }
                }else if(args.length == 2 && args[0].equalsIgnoreCase("join")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        if(!BWExtended.getInstance().getArenaManager().getArena(args[1]).playerAlreadyInGame(player.getUniqueId())){
                            try{
                                BWExtended.getInstance().getArenaManager().getArena(args[1]).addNewPlayer(player.getUniqueId());
                                player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7You joined &a" + args[1]));
                                return true;
                            }catch (Exception e){
                                player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cThat is not a valid team!"));
                            }
                        }else{
                            player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cYou are already in a game"));
                        }
                    }
                }else if(args.length == 3 && args[0].equalsIgnoreCase("joinTeam")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[2])){
                        if(!BWExtended.getInstance().getArenaManager().getArena(args[2]).playerAlreadyInTeam(player.getUniqueId())){
                            BWExtended.getInstance().getArenaManager().getArena(args[2]).addPlayerToTeam(player.getUniqueId(),BWExtended.getInstance().getArenaManager().getArena(args[2])
                                    .getTeam(args[1]));
                            BWExtended.getInstance().getArenaManager().startGame(args[2]);
                            return true;
                        }else{
                            player.sendMessage("Already in team");
                            return true;
                        }

                    }
                    /*
                    if(!BWExtended.getInstance().getArenaManager().getArena(args[2]).playerAlreadyInTeam(player.getUniqueId())){
                        BWExtended.getInstance().getArenaManager().getArena(args[2]).addPlayerToTeam(player.getUniqueId(),BWExtended.getInstance().getArenaManager().getArena(args[2]).getTeam(args[1]));
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("You joined the team " + TeamColor.valueOf(args[1].toUpperCase()).getPrefix()));
                    }else{
                        player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cYou are already in team " + BWExtended.getInstance().getArenaManager()
                        .getArena(args[2]).getTeamFromPlayer(player.getUniqueId())));
                    }

                     */
                }else if(args.length == 2 && args[0].equalsIgnoreCase("leaveTeam")){
                    if(BWExtended.getInstance().getArenaManager().arenaExists(args[1])){
                        if(BWExtended.getInstance().getArenaManager().getArena(args[1]).playerAlreadyInTeam(player.getUniqueId())){
                            BWExtended.getInstance().getArenaManager().getArena(args[1]).removePlayerFromTeam(player.getUniqueId());
                        }
                    }
                }else if(args.length == 2 && args[0].equalsIgnoreCase("teamList")){
                    String teams = "";
                    for(Map.Entry<String,BedWarsTeam> entry : BWExtended.getInstance().getArenaManager().getArena(args[1]).getTeamListMap().entrySet()){
                        teams += TeamColor.valueOf(entry.getKey()).getPrefix();
                    }
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&7----&eTeam List&7----"));
                    player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate(teams));
                }else{
                    try{
                        if(args[2] == null){
                            player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cError"));
                        }else if(args[2] != null){
                            player.sendMessage(BWExtended.getInstance().getPrefix() + BWExtended.getInstance().getUtils().translate("&cError: a" + args[2] + "&cdoesn't exist"));
                        }
                        return true;
                    }catch (Exception e){

                    }

                }
            }
        }

        return true;
    }
}
