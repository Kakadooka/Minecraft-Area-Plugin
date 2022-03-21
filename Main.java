package com.kakadooka.areaalert;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

/**
 *
 * @author Przemek
 */

public class Main extends JavaPlugin{
       
       public Main(){

       }
       
     @Override
    public void onEnable() {
        System.out.println("[AreaAlet] Working");
        
        File areaAlert = new File("plugins\\AreaAlert");
            if (!areaAlert.exists()){
                areaAlert.mkdirs();
            }
         File areas = new File("plugins\\AreaAlert\\Areas");
            if (!areas.exists()){
                areas.mkdirs();
            }
          File playerRules = new File("plugins\\AreaAlert\\PlayerRules");
            if (!playerRules.exists()){
                playerRules.mkdirs();
            }   

        this.getCommand("areacheck").setExecutor(new CommandCheckArea());

    }
}

