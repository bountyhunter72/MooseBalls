package net.charter.jpatterson72.MooseBall;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class MooseBall5SecondTimer {



	public static void MooseBall5SecondTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {

					myPlugin.getServer().broadcastMessage(ChatColor.RED + "MooseBall: " + ChatColor.AQUA
							+ "Next Match Begins In 5 Seconds!  Get Ready!!!");
						
					}
				}, 500);
		

		return;
	}
}








