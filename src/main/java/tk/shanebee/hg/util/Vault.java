package tk.shanebee.hg.util;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

/** General Vault class */
public class Vault {

  public static Economy economy = null;

  public static void setupEconomy() {
    RegisteredServiceProvider<Economy> economyProvider =
        Bukkit.getServer()
            .getServicesManager()
            .getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
      economy = economyProvider.getProvider();
    }
  }
}
