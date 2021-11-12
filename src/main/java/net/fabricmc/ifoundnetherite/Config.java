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
                config_init();
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

    private static void config_init() {
        config_map.put("diamond", "0");
        config_map.put("ghast_tear", "0");
        config_map.put("ancient_debris", "0");
        config_map.put("netherite_ingot", "0");
        config_map.put("netherite_scrap", "0");
        config_map.put("heart_of_the_sea", "0");
        config_map.put("trident", "0");
        config_map.put("shulker_shell", "0");
        config_map.put("nether_star", "0");
        config_map.put("elytra", "0");
        config_map.put("enchanted_book", "0");
    }
}
