package net.disaapsesv.gamecore.killthecore;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import net.disaapsesv.gamecore.killthecore.command.Commands;
import net.disaapsesv.gamecore.killthecore.mysql.MySQL;

public class GameCore extends JavaPlugin implements Listener{

	private ProtocolManager protocolManager;
	public static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getLogger().info("Enable-GameCore");
		getLogger().info("--==KillTheCore==--");

		getServer().getPluginManager().registerEvents(new KTCListener(), this);
		getServer().getPluginManager().registerEvents(this, this);

	    getCommand("mysql").setExecutor(new Commands());

		Config.create();
		MySQL.connect();
		super.onEnable();
	}

	public void onDisable() {
		getLogger().info("Disable-GameCore");
		getLogger().info("--==KillTheCore==--");

		super.onDisable();
	}

	public void onLoad() {
	    protocolManager = ProtocolLibrary.getProtocolManager();
	}



}
