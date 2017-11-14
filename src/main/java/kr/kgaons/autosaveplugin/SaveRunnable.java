package kr.kgaons.autosaveplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class SaveRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',AutoSavePlugin.getInstance().configstartmsg));
            Bukkit.savePlayers();
            Bukkit.getWorlds().forEach(w -> w.save());
            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',AutoSavePlugin.getInstance().configendmsg));
        } catch (Exception ex) {
            Bukkit.getLogger().warning("[AutoSavePlugin] 자동 저장 중 오류가 발생하였습니다!");
            ex.printStackTrace();
        }
    }
}
