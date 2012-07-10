package net.charter.jpatterson72.MooseBall;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class MooseBallBroadCastTimer {
	public static void MooseBallBroadCastTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncRepeatingTask(myPlugin, new Runnable() {

					public void run() {

						if (!(MooseBallHandler.matchInit)) {
							Random rand = new Random();
							int randMessage = rand.nextInt(9);
							if (randMessage == 0) {
								myPlugin.getServer()
										.broadcastMessage(
												ChatColor.GOLD
														+ "Looking For fast paced action? "
														+ ChatColor.AQUA
														+ "Join MooseBall!");
							}

							if (randMessage == 1) {
								myPlugin.getServer().broadcastMessage(
										ChatColor.GOLD + "Splat Your Friends!"
												+ ChatColor.AQUA
												+ " Play MooseBall!");
							}

							if (randMessage == 2) {
								myPlugin.getServer().broadcastMessage(
										ChatColor.AQUA + "MooseBall "
												+ ChatColor.GOLD + "Game On!!");
							}

							if ((randMessage == 3) || (randMessage == 7) || (randMessage == 8)){
								int playersNeeded = (MooseBallConfig.minimumPlayers
										- MooseBallHandler.redTeamcue.size() - MooseBallHandler.blueTeamcue
										.size());
								myPlugin.getServer().broadcastMessage(
										ChatColor.AQUA + "MooseBall "
												+ ChatColor.GOLD
												+ "Looking for "
												+ playersNeeded
												+ " more Adventurous Players!");
							}

							if (randMessage == 4) {
								myPlugin.getServer().broadcastMessage(
										ChatColor.AQUA + "MooseBall "
												+ ChatColor.GOLD
												+ "Be a Baller!!");
							}
							if (randMessage == 5) {
								myPlugin.getServer()
										.broadcastMessage(
												ChatColor.AQUA
														+ "MooseBall "
														+ ChatColor.GOLD
														+ "Come Play!  Unless you're scared?");
							}

							if (randMessage == 6) {
								myPlugin.getServer().broadcastMessage(
										ChatColor.AQUA + "MooseBall "
												+ ChatColor.GOLD
												+ "Play Ball!!");
							}

						}

					}
				}, 0, 600);

		return;
	}

}
