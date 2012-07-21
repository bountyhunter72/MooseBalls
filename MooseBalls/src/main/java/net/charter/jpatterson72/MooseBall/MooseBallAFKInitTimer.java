package net.charter.jpatterson72.MooseBall;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

public class MooseBallAFKInitTimer {
	
	public static void MooseBallAFKInitTimer(final Plugin myPlugin, final Player player) {
		final int timerID = myPlugin.getServer().getScheduler()
				.scheduleSyncDelayedTask(myPlugin, new Runnable() {

					public void run() {
						player.setMetadata("MooseBallAFK", new FixedMetadataValue(
								myPlugin, "true"));

					}
					
					
					
				}, 60);
		

		return;
	}
}





