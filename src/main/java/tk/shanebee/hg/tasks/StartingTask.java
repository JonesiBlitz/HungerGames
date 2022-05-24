package tk.shanebee.hg.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import tk.shanebee.hg.HG;
import tk.shanebee.hg.data.Config;
import tk.shanebee.hg.data.Language;
import tk.shanebee.hg.game.Game;
import tk.shanebee.hg.util.Util;

import java.util.Objects;

public class StartingTask implements Runnable {

    private int timer;
    private final int id;
    private final Game game;
    private final Language lang;

    public StartingTask(Game g) {
        this.timer = 30;
        this.game = g;
        this.lang = HG.getPlugin().getLang();
        String name = g.getGameArenaData().getName();
        String broadcast = lang.game_started
                .replace("<arena>", name)
                .replace("<seconds>", "" + timer);
        if (Config.broadcastJoinMessages) {
            Util.broadcast(broadcast);
            Util.broadcast(lang.game_join.replace("<arena>", name));
        } else {
            game.getGamePlayerData().msgAll(broadcast);
        }
        this.id = Bukkit.getScheduler().scheduleSyncRepeatingTask(HG.getPlugin(), this, 5 * 20L, 5 * 20L);
    }

    @Override
    public void run() {
        timer = (timer - 5);

        if (timer <= 0) {
            //clear inventory on game start

            if ((Config.enableleaveitem) || Config.enableforcestartitem)
                game.getGamePlayerData().getPlayers().forEach(uuid -> {
                    Player player = Bukkit.getPlayer(uuid);
                    assert player != null;
                    if (player.getInventory().contains(Objects.requireNonNull(Material.getMaterial(Config.forcestartitem)))  || player.getInventory().contains(Objects.requireNonNull(Material.getMaterial(Config.leaveitemtype)))) {
                        player.getInventory().clear();
                    }
                });

            stop();
            game.startFreeRoam();
        } else {
            game.getGamePlayerData().msgAll(lang.game_countdown.replace("<timer>", String.valueOf(timer)));
        }
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(id);
    }

}
