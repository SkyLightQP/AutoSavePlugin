package kr.kgaons.autosaveplugin;

import kr.kgaons.autosaveplugin.utils.Util;
import kr.kgaons.autosaveplugin.utils.WebHook;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class AutoSavePlugin extends JavaPlugin{
    final String VERSION = this.getDescription().getVersion();
    final String NAME = this.getDescription().getName();
    public final String PREFIX = "§f[§aAutoSavePlugin§f] §r";

    public FileConfiguration config = null;
    public File file = new File(this.getDataFolder(), "config.yml");
    public String configtime = null;
    public String configstartmsg = null;
    public String configendmsg = null;

    private static AutoSavePlugin plugin = null;

    public void onEnable(){
        plugin = this;

        Util.enablePlugin(NAME,VERSION);
        Util.checkOnline();
        WebHook.checkUtilVersion();

        getCommand("autosaveplugin").setExecutor(new MainCommand());
        configInit();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this,new SaveRunnable(),0,Long.parseLong(configtime)*20);
    }

    public void configInit(){
        config = YamlConfiguration.loadConfiguration(file);
        this.saveResource("config.yml",false);

        try {
            config.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        this.configtime = config.getString("time");
        this.configstartmsg = config.getString("start-message");
        this.configendmsg = config.getString("end-message");
    }

    public static AutoSavePlugin getInstance(){
        return plugin;
    }

    public void onDisable(){
        Util.disablePlugin(NAME,VERSION);
        Util.checkOnline();
    }
}
