package tk.shanebee.hg;

import org.bukkit.configuration.Configuration;

import java.io.File;
import java.util.List;

public class Config {

	//Basic settings
	static boolean spawnmobs;
	static int spawnmobsinterval;
	public static boolean bossbar;
	public static int trackingstickuses;
	public static int playersfortrackingstick;
	public static int maxchestcontent;
	public static int maxTeam;
	public static boolean teleportEnd;
	public static int teleportEndTime;

	//Reward info
	static boolean giveReward;
	static int cash;
	static List<String> rewardCommands;
	static List<String> rewardMessages;

	//Rollback config info
	public static boolean breakblocks;
	public static boolean fixleaves;
	public static boolean preventtrample;
	public static List<String> blocks;

	//Random chest
	static boolean randomChest;
	public static int randomChestInterval;
	static int randomChestMaxContent;

	private HG plugin;

	public Config(HG plugin) {
		this.plugin = plugin;
		if (!new File(plugin.getDataFolder(), "config.yml").exists()) {
			Util.log("Config not found. Generating default config!");
			plugin.saveDefaultConfig();
		}
		Configuration config = plugin.getConfig().getRoot();
		assert config != null;
		config.options().copyDefaults(true);
		plugin.reloadConfig();
		config = plugin.getConfig();
		updateConfig(config);
		Util.log("Config loaded!");

		spawnmobs = config.getBoolean("settings.spawn-mobs");
		spawnmobsinterval = config.getInt("settings.spawn-mobs-interval") * 20;
		bossbar = config.getBoolean("settings.bossbar-countdown");
		trackingstickuses = config.getInt("settings.trackingstick-uses");
		playersfortrackingstick = config.getInt("settings.players-for-trackingstick");
		maxchestcontent = config.getInt("settings.max-chestcontent");
		maxTeam = config.getInt("settings.max-team-size");
		giveReward = config.getBoolean("reward.enabled");
		cash = config.getInt("reward.cash");
		rewardCommands = config.getStringList("reward.commands");
		rewardMessages = config.getStringList("reward.messages");
		maxTeam = config.getInt("settings.max-team-size");
		giveReward = config.getBoolean("reward.enabled");
		cash = config.getInt("reward.cash");
		breakblocks = config.getBoolean("rollback.allow-block-break");
		fixleaves = config.getBoolean("rollback.fix-leaves");
		preventtrample = config.getBoolean("rollback.prevent-trampling");
		blocks = config.getStringList("rollback.editable-blocks");
		randomChest = config.getBoolean("random-chest.enabled");
		randomChestInterval = config.getInt("random-chest.interval") * 20;
		randomChestMaxContent = config.getInt("random-chest.max-chestcontent");
		teleportEnd = config.getBoolean("settings.teleport-at-end");
		teleportEndTime = config.getInt("teleport-at-end-time");

		if (giveReward) {
			try {
				Vault.setupEconomy();
				if (Vault.economy == null) {
					Util.log("&cUnable to setup vault!");
					Util.log(" - &cEconomy provider is missing.");
					Util.log(" - Cash rewards will not be given out..");
					giveReward = false;
				}
			} catch (NoClassDefFoundError e) {
				Util.log("&cUnable to setup vault!");
				Util.log("  - Cash rewards will not be given out..");
				giveReward = false;
			}
		}
	}

	private void updateConfig(Configuration config) {
		if (!config.isSet("settings.bossbar-countdown")) {
			config.set("settings.bossbar-countdown", true);
		}
		plugin.saveConfig();
	}

}