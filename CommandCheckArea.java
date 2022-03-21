package com.kakadooka.areaalert;

import com.kakadooka.areaalert.PlayerInfo;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

/**
 *
 * @author Przemek
 */
public class CommandCheckArea implements CommandExecutor {

    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            
            PlayerInfo playerInfo = new PlayerInfo();
            
            playerInfo.readPolygonsFromAreas(player);
            
        }

        return true;
    }
}
