package tk.shanebee.hg.events;

import java.util.Collection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.shanebee.hg.game.Game;

/** Called when a game ends */
public class GameEndEvent extends Event {

  private static final HandlerList handlers = new HandlerList();

  private Game game;
  private Collection<Player> winners;
  private boolean death;

  public GameEndEvent(Game game, Collection<Player> winners, boolean death) {
    this.game = game;
    this.winners = winners;
    this.death = death;
  }

  @SuppressWarnings("unused")
  public static HandlerList getHandlerList() {
    return handlers;
  }

  /**
   * Get the game that ended
   *
   * @return Game that ended
   */
  public Game getGame() {
    return this.game;
  }

  /**
   * Get the winners of this game
   *
   * @return Winners of the game
   */
  public Collection<Player> getWinners() {
    return this.winners;
  }

  /**
   * Get whether or not the game ended by death
   *
   * @return True if the game finished by the result of death, false if the game was force stopped
   */
  public boolean byDeath() {
    return this.death;
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public HandlerList getHandlers() {
    return handlers;
  }
}
