package net.fabricmc.ifoundnetherite;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import com.google.gson.Gson;

public class Config {
    public static File configDir = new File("config");
    public static File configFile = new File("config/IFoundNetherite_config.json");

    private Gson gson = new Gson();
    public static HashMap<String, String> config_map = new HashMap<String, String>();

    public void load_config() {
        configDir.mkdirs();
        try {
            if (configFile.createNewFile()) {
                FileWriter writer = new FileWriter(configFile);
                config_map.put("item.minecraft.diamond", "0");
                writer.append(gson.toJson(config_map));
                writer.close();
            } else {
                FileReader reader = new FileReader(configFile);
                config_map = gson.fromJson(reader, HashMap.class);
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save_config() {
        try {
            configDir.mkdirs();
            FileWriter writer = new FileWriter(configFile);
            writer.append(gson.toJson(config_map));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean in_config(String item_id) {
        return config_map.containsKey(item_id);
    }
}
