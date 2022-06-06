package tk.shanebee.hg.util;

import java.util.List;
import org.bukkit.entity.Player;

public interface Party {

  boolean hasParty(Player p);

  int partySize(Player p);

  boolean isOwner(Player p);

  List<Player> getMembers(Player owner);
}
