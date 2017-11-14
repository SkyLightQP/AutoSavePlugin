package kr.kgaons.autosaveplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String s, String[] args) {
        CommandSender sender;
        if(cs instanceof Player) {
            sender = cs;
        }
        else sender = Bukkit.getConsoleSender();
        if(sender.isOp()) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    try {
                        AutoSavePlugin.getInstance().config.save(AutoSavePlugin.getInstance().file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage(AutoSavePlugin.getInstance().PREFIX + "리로드 완료");
                }
            } else {
                sender.sendMessage(AutoSavePlugin.getInstance().PREFIX + "/asp reload - 플러그인 리로드");
                sender.sendMessage(AutoSavePlugin.getInstance().PREFIX + "/autosaveplugin reload - 플러그인 리로드");
            }
        } else sender.sendMessage(AutoSavePlugin.getInstance().PREFIX + "권한이 없습니다.");
        return false;
    }
}
