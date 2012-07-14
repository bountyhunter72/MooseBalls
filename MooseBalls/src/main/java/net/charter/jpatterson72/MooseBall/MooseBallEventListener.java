package net.charter.jpatterson72.MooseBall;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class MooseBallEventListener implements Listener {
	public static MooseBall plugin;

	public MooseBallEventListener(MooseBall instance) {

		plugin = instance;
	}

	Logger log;

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamage(EntityDamageEvent event) {
		if ((event.getEntity() instanceof Player)) {

			Player victim = (Player) event.getEntity();
			if (victim.hasMetadata("MooseBall")) {

				if (event.getDamage() >= victim.getHealth()) {
					victim.setMetadata("Eleminated", new FixedMetadataValue(
							plugin, "true"));
					MooseBallHandler.clearMooseBall(victim);
					MooseBallHandler.lobbySpawn(victim);
					MooseBallHandler.deColorize(victim);
					MooseBallHandler.eleminatePlayer(victim);
					MooseBallHandler.checkWin();
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

		if ((event.getEntity() instanceof Player)) {

			Player victim = (Player) event.getEntity();
			if (victim.hasMetadata("MooseBall")) {

				Entity entity = event.getDamager();

				if (entity instanceof Snowball) {

					Snowball balls = (Snowball) event.getDamager();
					event.setDamage(0);
					if (balls.getShooter() instanceof Player) {
						Player attacker = (Player) balls.getShooter();
						String attackername = attacker.getName();

						if (attacker.hasMetadata("MooseBall")) {
							String vicTeam = victim.getMetadata("MooseBall")
									.get(0).asString();
							String attTeam = attacker.getMetadata("MooseBall")
									.get(0).asString();
							if (!(vicTeam.equalsIgnoreCase(attTeam)))

							{

								int MooseBalls = attacker
										.getMetadata("MooseBalls").get(0)
										.asInt();
								MooseBalls++;
								attacker.setMetadata("MooseBalls",
										new FixedMetadataValue(plugin,
												MooseBalls));

								victim.setMetadata("Eleminated",
										new FixedMetadataValue(plugin, "true"));
								MooseBallHandler.clearMooseBall(victim);
								MooseBallHandler.lobbySpawn(victim);
								MooseBallHandler.deColorize(victim);
								MooseBallHandler.eleminatePlayer(victim);

								MooseBallBroadCast.MooseBallBraodCast(
										victim.getName(), attackername);
								MooseBallHandler.checkWin();

								event.setCancelled(true);
							} else {
								event.setCancelled(true);
							}
						}
					}
				}
				event.setCancelled(true);
			}
		}

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onToggleSneakEvent(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		if (!(player.isSneaking()))
			;
		if (player.hasMetadata("MooseBall")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if (player.hasMetadata("MooseBallAFK")) {

			player.removeMetadata("MooseBallAFK", plugin);
		}
	}

	@EventHandler
	public void clickEvent(InventoryClickEvent event) {
		Entity e = event.getWhoClicked();

		if (e instanceof Player) {
			Player player = (Player) e;
			if (player.hasMetadata("MooseBall")) {

				event.setCancelled(true);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		MooseBallSQL.SavePlayer(player);
		if (MooseBallHandler.redTeamcue.contains(playerName)) {
			MooseBallHandler.redTeamcue.remove(playerName);

		}

		if (MooseBallHandler.blueTeamcue.contains(playerName)) {
			MooseBallHandler.blueTeamcue.remove(playerName);

		}

		if (MooseBallHandler.blueTeamMatch.contains(playerName)) {
			if (player.hasMetadata("Eleminated")) {
				MooseBallHandler.blueTeamMatch.remove(playerName);
			} else {
				MooseBallHandler.blueTeamMatch.remove(playerName);
				MooseBallHandler.clearMooseBall(player);
				MooseBallHandler.lobbySpawn(player);
				MooseBallHandler.deColorize(player);
				MooseBallHandler.checkWin();
			}
		}
		if (MooseBallHandler.redTeamMatch.contains(playerName)) {
			if (player.hasMetadata("Eleminated")) {
				MooseBallHandler.redTeamMatch.remove(playerName);
			} else {
				MooseBallHandler.redTeamMatch.remove(playerName);
				MooseBallHandler.clearMooseBall(player);
				MooseBallHandler.lobbySpawn(player);
				MooseBallHandler.deColorize(player);
				MooseBallHandler.checkWin();
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		player.setMetadata("MooseBalls", new FixedMetadataValue(plugin, 0));
		player.setMetadata("MooseBallArmor", new FixedMetadataValue(plugin,
				"leather"));

		int rowcount = MooseBallSQL.playerInSQL(player);

		if (rowcount > 0) {
			MooseBallSQL.LoadPlayer(player, plugin);
		}

		if (rowcount < 1)

		{
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent evt)

	{		Player player = evt.getPlayer();

	String eventString = evt.getMessage().toString();
		if (player.hasPermission("figadmin.ban")) 
		{
			boolean valid = eventString.contains("/tp") || eventString.contains("/spawn");

			if (valid == true) {
				if (player.hasMetadata("MooseBall"))

				{

					player.sendMessage(ChatColor.DARK_RED
							+ "Please use /team leave to exit match before using that command!!");

					evt.setCancelled(true);

				}
			}
		}	

		if (!(player.hasPermission("figadmin.ban"))) {
			boolean valid = eventString.contains("/team")
					|| eventString.contains("/msg");

			if (valid != true) {
				if (player.hasMetadata("MooseBall"))

				{

					player.sendMessage(ChatColor.DARK_RED
							+ "Please use /team leave to exit match before using that command!!");

					evt.setCancelled(true);

				}
			}
		}
		

		

	}
}
