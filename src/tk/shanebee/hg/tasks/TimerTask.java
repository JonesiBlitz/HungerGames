package tk.shanebee.hg.tasks;

import org.bukkit.Bukkit;

import tk.shanebee.hg.Config;
import tk.shanebee.hg.Game;
import tk.shanebee.hg.HG;
import tk.shanebee.hg.Status;

public class TimerTask implements Runnable {

	private int remainingtime;
	private int teleportTimer = Config.teleportEndTime;
	private int id;
	private Game game;

	public TimerTask(Game g, int time) {
		this.remainingtime = time;
		this.game = g;
		
		this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.plugin, this, 30 * 20L, 30 * 20L);
	}
	
	@Override
	public void run() {
		if (game == null || game.getStatus() != Status.RUNNING) stop(); //A quick null check!
		
		remainingtime = (remainingtime - 30);
		if (Config.bossbar) game.bossbarUpdate(remainingtime);

		if (remainingtime <= teleportTimer && remainingtime > 10 && Config.teleportEnd) {
			game.msgAll(HG.lang.game_almost_over);
			game.respawnAll();
		} else if (this.remainingtime < 10) {
			stop();
			game.stop(false);
		} else {
			if (!Config.bossbar) {
				int minutes = this.remainingtime / 60;
				int asd = this.remainingtime % 60;
				if (minutes != 0) {
					if (asd == 0)
						game.msgAll(HG.lang.game_ending_min.replace("<minutes>", String.valueOf(minutes)));
					else

						game.msgAll(HG.lang.game_ending_minsec.replace("<minutes>", String.valueOf(minutes)).replace("<seconds>", String.valueOf(asd)));
				} else game.msgAll(HG.lang.game_ending_sec.replace("<seconds>", String.valueOf(this.remainingtime)));
			}
		}
	}
	
	public void stop() {
		Bukkit.getScheduler().cancelTask(id);
	}

}