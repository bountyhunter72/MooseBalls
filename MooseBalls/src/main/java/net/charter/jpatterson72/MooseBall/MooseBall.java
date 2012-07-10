package net.charter.jpatterson72.MooseBall;

import java.util.logging.Logger;

import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Server;





public class MooseBall extends JavaPlugin {
	public static Logger log = Logger.getLogger("Minecraft");
	Server _server = null;
	JavaPlugin _parent = null;
	public static MooseBall plugin;
	public static MooseBallHandler _playerHandler = null;
	public static MooseBallBroadCast _broadcaster = null;
	public static MooseBallEventListener _entityListener = null;
	public static MooseBallCommand _commandExecutor = null;
	public String _dataFolder;
	public boolean _isShutdown = false;
	public static MooseBallConfig _configuration = null;
	
	public boolean Initialize(Server server, JavaPlugin parent,
			String dataFolder) {
		this._server = server;
		this._parent = parent;
		this._configuration = new MooseBallConfig(this);
		this._entityListener = new MooseBallEventListener(this);
		this._commandExecutor = new MooseBallCommand(this);
		MooseBall._playerHandler = new MooseBallHandler(this);
		this._broadcaster = new MooseBallBroadCast(this);
		this._dataFolder = dataFolder;
		getServer().getPluginManager().registerEvents(
				new MooseBallEventListener(this), this._parent);

		return true;
	}

	@Override
	public void onEnable() {
		log = this.getLogger();

		CommandExecutor myExecutor = new MooseBallCommand(this);
		getCommand("team").setExecutor(myExecutor);

		Initialize(getServer(), this, getDataFolder().getAbsolutePath() + "/");

		MooseBallConfig.Load();
		MooseBallConfig.LoadSpawn();
		MooseBallSQL.dbExists();
		
	}

	@Override
	public void onDisable() {

	}

}
