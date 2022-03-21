package com.kakadooka.areaalert;

import com.kakadooka.areaalert.PointInsideShape;


import java.io.IOException;
import java.util.List;
import java.io.File;
import java.awt.Point;
import java.lang.String;
        
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.Location;

/**
 *
 * @author Przemek
 */
public class PlayerInfo {

        public String playerName;
        public List<String> pointList;       
        public List<String> rulesList;
        public String[] splitStringHolder;
        
        public PointInsideShape insideChecker;
        
        
        
        
        public PlayerInfo(String playerName, List<String> pointList, List<String> rulesList){
            this.playerName = playerName;
            this.pointList = pointList;
            this.rulesList = rulesList;
        }
        
        public PlayerInfo(){
        }
            
        public void writeToAreas() {
            File loopFile = new File("test");
            
            // sprawdz ile jest plikow w folderze Areas
            File directory = new File("plugins\\AreaAlert\\Areas");
            int fileCount=directory.list().length;
            System.out.println(fileCount);
            
            // sprawdz kazdy plik po kolei, jezeli plik o danym numerze nie istnieje, to go stworz
            for(int i = 0; i <= fileCount; i++){
                loopFile = new File("plugins\\AreaAlert\\Areas\\"+i+".yml");
                if(!loopFile.exists()){
                    try{
                        loopFile.createNewFile();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    break;
                }
            }

            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(loopFile);
            
            yaml.createSection("owner");
            yaml.createSection("pointList");
            
            yaml.set("owner", playerName);
            yaml.set("pointList", pointList);
            
            try {
                yaml.save(loopFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        
        public void readPolygonsFromAreas(Player player){
            
            boolean AreaTrue = false;
            
            File directory = new File("plugins\\AreaAlert\\Areas");

            Location loc = player.getLocation();
            Point locPoint = new Point(loc.getBlockX(), loc.getBlockZ());
            
            int fileCount=directory.list().length;
            List<?> StringPointList;
            
             for(int i = 0; i <= fileCount; i++){
                 
                
                 File loopFile = new File("plugins\\AreaAlert\\Areas\\"+i+".yml");
                 FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(loopFile);
                 StringPointList = fileConfig.getList("pointList");
                 
                 if(StringPointList != null){
                     Point[] polygon = new Point[StringPointList.size()];
                    for(int j = 0; j < StringPointList.size(); j++ ){
                    splitStringHolder = StringPointList.get(j).toString().split(";");

                    try{
                       polygon[j] = new Point(Integer.parseInt(splitStringHolder[0]), Integer.parseInt(splitStringHolder[1]));
                    }
                    catch(Exception e){               
                    }


                   }
                    if (insideChecker.isInside(polygon, StringPointList.size(), locPoint)){
                        player.sendMessage("Wlasciciel: " + fileConfig.getString("owner"));
                        AreaTrue = true;
                        break;
                    }
                }
                 else{
                     break;
                 }

             }
                 if (!AreaTrue){
                     player.sendMessage("Jestes na bezprawnej puszczy.");
                 }
        }
        
        public void addAreaToPlayer(){
            
            return;
        }

    }

