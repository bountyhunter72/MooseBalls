package net.charter.jpatterson72.MooseBall;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class MooseBallCommand implements CommandExecutor {
	public static MooseBall plugin;

	public MooseBallCommand(MooseBall instance) {
		plugin = instance;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		Player player = null;
		if ((sender instanceof Player)) {
			player = (Player) sender;

			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "/team red "
						+ ChatColor.BLUE + "Join The " + ChatColor.RED + "RED "
						+ ChatColor.BLUE + "Team.");
				sender.sendMessage(ChatColor.GOLD + "/team blue "
						+ ChatColor.BLUE + "Join The " + ChatColor.DARK_BLUE
						+ "BLUE " + ChatColor.BLUE + "Team.");
				sender.sendMessage(ChatColor.GOLD + "/team leave "
						+ ChatColor.BLUE + "Leaves your current team");
				sender.sendMessage(ChatColor.GOLD + "/team points "
						+ ChatColor.BLUE + "Shows your current points");
				sender.sendMessage(ChatColor.GOLD + "/team upgrade "
						+ ChatColor.BLUE + "Spend points to upgrade armor");
				sender.sendMessage(ChatColor.WHITE + "------------>"
						+ ChatColor.BLUE + "Leather to Iron 500 Points");
				sender.sendMessage(ChatColor.WHITE + "------------>"
						+ ChatColor.BLUE + "Iron to Gold 1000 Points");
				sender.sendMessage(ChatColor.WHITE + "------------>"
						+ ChatColor.BLUE + "Gold  to ChainMail 2000 Points");
				sender.sendMessage(ChatColor.WHITE + "------------>"
						+ ChatColor.BLUE + "ChainMail to Diamond 3000 Points");
				sender.sendMessage(ChatColor.GOLD + "/team about "
						+ ChatColor.BLUE + "Learn More About MooseBall");
				if (sender.hasPermission("MooseBall.admin")) {
					sender.sendMessage(ChatColor.GOLD + "/team set lobby "
							+ ChatColor.BLUE + "Set the Lobby Spawn Point.");
					sender.sendMessage(ChatColor.GOLD + "/team set red #"
							+ ChatColor.BLUE
							+ "Set the Red Spawn Point (Arena 1-4).");
					sender.sendMessage(ChatColor.GOLD + "/team set blue #"
							+ ChatColor.BLUE
							+ "Set the Green Spawn Point (Arena 1-4).");
					sender.sendMessage(ChatColor.GOLD
							+ "/team promote <player>" + ChatColor.BLUE
							+ "Manually Upgrade Player to Next Armor Level");
					sender.sendMessage(ChatColor.GOLD
							+ "/team add <player> <amount>"
							+ ChatColor.BLUE
							+ "Manually Add Points to Player (No Safety!! For Testing Only)");
					sender.sendMessage(ChatColor.GOLD + "/team reset"
							+ ChatColor.BLUE
							+ " (Reset Everyone to lobby if a match hangs!)");
					sender.sendMessage(ChatColor.GOLD
							+ "/team active #"
							+ ChatColor.BLUE
							+ " Toggle Active Arenas ie. /team active 4 sets Arena 4 active");
					sender.sendMessage(ChatColor.GOLD
							+ "/team inactive #"
							+ ChatColor.BLUE
							+ " Toggle Active Arenas ie. /team inactive 4 sets Arena 4 inactive");
				}

				return true;
			}

			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("points")) {
					int MooseBalls = player.getMetadata("MooseBalls").get(0)
							.asInt();
					player.sendMessage(ChatColor.AQUA + "MooseBalls: "
							+ ChatColor.YELLOW + MooseBalls);

					return true;
				}

				if (args[0].equalsIgnoreCase("reset")) {
					if (sender.hasPermission("MooseBall.admin")) {
						Player[] playerlist = plugin.getServer()
								.getOnlinePlayers();
						int listsize = plugin.getServer().getOnlinePlayers().length;
						for (int i = 0; i < listsize; i++) {
							Player replayer = playerlist[i];
							if (replayer.hasMetadata("MooseBall")) {

								MooseBallHandler.clearMooseBall(replayer);
								MooseBallHandler.lobbySpawn(replayer);
								MooseBallHandler.deColorize(replayer);
								MooseBallHandler.eleminatePlayer(replayer);
								MooseBallHandler.checkWin();
							}

						}

					}

					return true;
				}

				if (args[0].equalsIgnoreCase("about")) {
					sender.sendMessage(ChatColor.AQUA + "[MooseBall] Author: "
							+ ChatColor.GOLD + "Bounty_Hunter72"
							+ ChatColor.AQUA + "  Version: " + ChatColor.GOLD
							+ "1.02.00");
					return true;

				}

				if (args[0].equalsIgnoreCase("upgrade")) {
					int MooseBalls = player.getMetadata("MooseBalls").get(0)
							.asInt();

					if (player.getMetadata("MooseBallArmor").get(0).asString()
							.equalsIgnoreCase("leather")) {
						if (MooseBalls >= 500) {
							player.setMetadata("MooseBallArmor",
									new FixedMetadataValue(plugin, "iron"));
							player.sendMessage(ChatColor.RED
									+ "You Have Upgraded to Iron Armor!");
							MooseBalls = MooseBalls - 500;
							player.setMetadata("MooseBalls",
									new FixedMetadataValue(plugin, MooseBalls));
							return true;
						} else {
							player.sendMessage(ChatColor.RED
									+ "Not Enough Points To Upgrade!");
						}
						return true;
					}
					if (player.getMetadata("MooseBallArmor").get(0).asString()
							.equalsIgnoreCase("iron")) {
						if (MooseBalls >= 1000) {
							player.setMetadata("MooseBallArmor",
									new FixedMetadataValue(plugin, "gold"));
							player.sendMessage(ChatColor.RED
									+ "You Have Upgraded to Gold Armor!");
							MooseBalls = MooseBalls - 1000;
							player.setMetadata("MooseBalls",
									new FixedMetadataValue(plugin, MooseBalls));
							return true;
						} else {
							player.sendMessage(ChatColor.RED
									+ "Not Enough Points To Upgrade!");
						}
						return true;
					}
					if (player.getMetadata("MooseBallArmor").get(0).asString()
							.equalsIgnoreCase("gold")) {
						if (MooseBalls >= 2000) {
							player.setMetadata("MooseBallArmor",
									new FixedMetadataValue(plugin, "chain"));
							player.sendMessage(ChatColor.RED
									+ "You Have Upgraded to ChainMail Armor!");
							MooseBalls = MooseBalls - 2000;
							player.setMetadata("MooseBalls",
									new FixedMetadataValue(plugin, MooseBalls));
							return true;
						} else {
							player.sendMessage(ChatColor.RED
									+ "Not Enough Points To Upgrade!");
						}
						return true;
					}
					if (player.getMetadata("MooseBallArmor").get(0).asString()
							.equalsIgnoreCase("chain")) {
						if (MooseBalls >= 3000) {
							player.setMetadata("MooseBallArmor",
									new FixedMetadataValue(plugin, "diamond"));
							player.sendMessage(ChatColor.RED
									+ "You Have Upgraded to Diamond Armor!");
							MooseBalls = MooseBalls - 3000;
							player.setMetadata("MooseBalls",
									new FixedMetadataValue(plugin, MooseBalls));
							return true;
						} else {
							player.sendMessage(ChatColor.RED
									+ "Not Enough Points To Upgrade!");
						}
						return true;
					}
					if (player.getMetadata("MooseBallArmor").get(0).asString()
							.equalsIgnoreCase("diamond")) {
						player.sendMessage(ChatColor.RED
								+ "Armor Already At Maximum Upgrade Level");
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("red")) {

					MooseBallHandler.addRedCue(player);
					MooseBallHandler.checkTeams();

					return true;
				} else if (args[0].equalsIgnoreCase("blue")) {

					MooseBallHandler.addBlueCue(player);
					MooseBallHandler.checkTeams();

					return true;
				} else if (args[0].equalsIgnoreCase("leave")) {

					MooseBallHandler.teamLeave(player);
					if (player.hasMetadata("MooseBallAFK")) {
						player.removeMetadata("MooseBallAFK", plugin);
					}
					return true;
				} else {
					sender.sendMessage(ChatColor.RED + "Unknown Command.");
					return true;
				}
			}
			if (args.length == 2) {

				if ((args[0].equalsIgnoreCase("active"))
						&& (args[1].equalsIgnoreCase("1"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 1: Active");
					MooseBallConfig.arena1 = true;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("active"))
						&& (args[1].equalsIgnoreCase("2"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 2: Active");
					MooseBallConfig.arena2 = true;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("active"))
						&& (args[1].equalsIgnoreCase("3"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 3: Active");
					MooseBallConfig.arena3 = true;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("active"))
						&& (args[1].equalsIgnoreCase("4"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 4: Active");
					MooseBallConfig.arena4 = true;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("inactive"))
						&& (args[1].equalsIgnoreCase("1"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 1: Inactive");
					MooseBallConfig.arena1 = false;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("inactive"))
						&& (args[1].equalsIgnoreCase("2"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 2: Inactive");
					MooseBallConfig.arena2 = false;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("inactive"))
						&& (args[1].equalsIgnoreCase("3"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 3: Inactive");
					MooseBallConfig.arena3 = false;
					MooseBallConfig.SaveSpawn();
					return true;
				}
				if ((args[0].equalsIgnoreCase("inactive"))
						&& (args[1].equalsIgnoreCase("4"))) {
					sender.sendMessage(ChatColor.AQUA + "Arena 4: Inactive");
					MooseBallConfig.arena4 = false;
					MooseBallConfig.SaveSpawn();
					return true;
				}

				if (args[0].equalsIgnoreCase("set")) {
					if (sender.hasPermission("MooseBall.admin")) {
						if (args[1].equalsIgnoreCase("lobby"))

						{
							// set lobby
							sender.sendMessage(ChatColor.AQUA
									+ "Lobby Spawn Set");
							MooseBallConfig.spawnX = player.getLocation()
									.getX();
							MooseBallConfig.spawnZ = player.getLocation()
									.getZ();
							MooseBallConfig.spawnY = player.getLocation()
									.getY();
							MooseBallConfig.spawnYaw = player.getLocation()
									.getYaw();
							MooseBallConfig.SaveSpawn();
							return true;

						}

						else {
							sender.sendMessage(ChatColor.RED
									+ "Unknown Command.");
							return true;
						}
					}
				}

				if (args[0].equalsIgnoreCase("promote")) {
					if (sender.hasPermission("MooseBall.admin")) {
						String playerName = args[1];
						if ((plugin.getServer().getPlayer(playerName)) != null) {
							Player p = plugin.getServer().getPlayer(playerName);

							if (p.getMetadata("MooseBallArmor").get(0)
									.asString().equalsIgnoreCase("leather")) {
								p.setMetadata("MooseBallArmor",
										new FixedMetadataValue(plugin, "iron"));
								sender.sendMessage(ChatColor.RED + "Promoted "
										+ playerName + " to Iron");
								return true;
							}

							else if (p.getMetadata("MooseBallArmor").get(0)
									.asString().equalsIgnoreCase("iron")) {
								p.setMetadata("MooseBallArmor",
										new FixedMetadataValue(plugin, "gold"));
								sender.sendMessage(ChatColor.RED + "Promoted "
										+ playerName + " to Gold");
								return true;
							}

							else if (p.getMetadata("MooseBallArmor").get(0)
									.asString().equalsIgnoreCase("gold")) {
								p.setMetadata("MooseBallArmor",
										new FixedMetadataValue(plugin, "chain"));
								sender.sendMessage(ChatColor.RED + "Promoted "
										+ playerName + " to ChainMail");
								return true;

							} else if (p.getMetadata("MooseBallArmor").get(0)
									.asString().equalsIgnoreCase("chain")) {
								p.setMetadata("MooseBallArmor",
										new FixedMetadataValue(plugin,
												"diamond"));
								sender.sendMessage(ChatColor.RED + "Promoted "
										+ playerName + " to Diamond");
								return true;

							} else if (p.getMetadata("MooseBallArmor").get(0)
									.asString().equalsIgnoreCase("diamond")) {
								sender.sendMessage(ChatColor.RED
										+ "Already Max Armor Level");
								return true;
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Unknown Player");
						}
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Unknown Command.");
					return true;
				}

			}

			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("add")) {
					if (sender.hasPermission("MooseBall.admin")) {
						String playerName = args[1];
						if ((plugin.getServer().getPlayer(playerName)) != null) {
							Player p = plugin.getServer().getPlayer(playerName);
							int points = (int) Double.parseDouble(args[2]);
							int MooseBalls = player.getMetadata("MooseBalls")
									.get(0).asInt();
							MooseBalls = MooseBalls + points;
							p.setMetadata("MooseBalls", new FixedMetadataValue(
									plugin, MooseBalls));
							sender.sendMessage(ChatColor.RED
									+ "You Have Given " + playerName + " "
									+ points + " Points");
						}
					}
				}

				else if ((args[1].equalsIgnoreCase("red"))
						&& (args[2].equalsIgnoreCase("1"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Red Spawn Set: Arena 1");
					MooseBallConfig.redX1 = player.getLocation().getX();
					MooseBallConfig.redZ1 = player.getLocation().getZ();
					MooseBallConfig.redY1 = player.getLocation().getY();
					MooseBallConfig.redYaw1 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				} else if ((args[1].equalsIgnoreCase("blue"))
						&& (args[2].equalsIgnoreCase("1"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Blue Spawn Set: Arena 1");
					MooseBallConfig.blueX1 = player.getLocation().getX();
					MooseBallConfig.blueZ1 = player.getLocation().getZ();
					MooseBallConfig.blueY1 = player.getLocation().getY();
					MooseBallConfig.blueYaw1 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				} else if ((args[1].equalsIgnoreCase("red"))
						&& (args[2].equalsIgnoreCase("2"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Red Spawn Set: Arena 2");
					MooseBallConfig.redX2 = player.getLocation().getX();
					MooseBallConfig.redZ2 = player.getLocation().getZ();
					MooseBallConfig.redY2 = player.getLocation().getY();
					MooseBallConfig.redYaw2 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				} else if ((args[1].equalsIgnoreCase("blue"))
						&& (args[2].equalsIgnoreCase("2"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Blue Spawn Set: Arena 2");
					MooseBallConfig.blueX2 = player.getLocation().getX();
					MooseBallConfig.blueZ2 = player.getLocation().getZ();
					MooseBallConfig.blueY2 = player.getLocation().getY();
					MooseBallConfig.blueYaw2 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				}

				else if ((args[1].equalsIgnoreCase("red"))
						&& (args[2].equalsIgnoreCase("3"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Red Spawn Set: Arena 3");
					MooseBallConfig.redX3 = player.getLocation().getX();
					MooseBallConfig.redZ3 = player.getLocation().getZ();
					MooseBallConfig.redY3 = player.getLocation().getY();
					MooseBallConfig.redYaw3 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				} else if ((args[1].equalsIgnoreCase("blue"))
						&& (args[2].equalsIgnoreCase("3"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Blue Spawn Set: Arena 3");
					MooseBallConfig.blueX3 = player.getLocation().getX();
					MooseBallConfig.blueZ3 = player.getLocation().getZ();
					MooseBallConfig.blueY3 = player.getLocation().getY();
					MooseBallConfig.blueYaw3 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				} else if ((args[1].equalsIgnoreCase("red"))
						&& (args[2].equalsIgnoreCase("4"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Red Spawn Set: Arena 4");
					MooseBallConfig.redX4 = player.getLocation().getX();
					MooseBallConfig.redZ4 = player.getLocation().getZ();
					MooseBallConfig.redY4 = player.getLocation().getY();
					MooseBallConfig.redYaw4 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				} else if ((args[1].equalsIgnoreCase("blue"))
						&& (args[2].equalsIgnoreCase("4"))) {
					sender.sendMessage(ChatColor.AQUA
							+ "Blue Spawn Set: Arena 4");
					MooseBallConfig.blueX4 = player.getLocation().getX();
					MooseBallConfig.blueZ4 = player.getLocation().getZ();
					MooseBallConfig.blueY4 = player.getLocation().getY();
					MooseBallConfig.blueYaw4 = player.getLocation().getYaw();
					MooseBallConfig.SaveSpawn();
					return true;
				}

			}

			else {
				sender.sendMessage(ChatColor.RED + "Unknown Command.");
				return true;
			}

		}
		return true;
	}

	public static boolean isNum(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}