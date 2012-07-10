package net.charter.jpatterson72.MooseBall;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class MooseBall1SecondTimer {

	public static void MooseBall1SecondTimer(final Plugin myPlugin) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {

						MooseBallHandler.normalizeTeams();
						
					}
				}, 590);
		

		return;
	}
}










