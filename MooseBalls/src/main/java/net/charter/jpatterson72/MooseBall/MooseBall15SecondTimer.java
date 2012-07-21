package net.charter.jpatterson72.MooseBall;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class MooseBall15SecondTimer {


	public static int MooseBall15SecondTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {

					myPlugin.getServer().broadcastMessage(ChatColor.RED + "MooseBall: " + ChatColor.AQUA
							+ "Next Match Begins In 15 Seconds!");
					while (!(MooseBallHandler.selectArena())) {
						;
					}
					}
				}, 300);
		

		return timerID;
	}
}