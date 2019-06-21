package dev.hackweek;

import com.google.gson.JsonObject;
import dev.hackweek.utils.JsonConfig;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

@Getter
public class HackWeek {
    private JsonConfig pluginConfig = new JsonConfig(new File("config.json"));

    public static void main(String[] args) {

    }

    private void loadConfing() {
        File file = new File("config.json");
        try {
            file.createNewFile();
            if (!file.exists()) {


                pluginConfig.setJsonObject(new JsonObject());
                pluginConfig.setString("motd", "§e§lE§r§exTasTy.net §8● §7Dein §3Minigame §7Netzwerk §8× §d1.8.X §8- §d1.12.X\n §8➤ §aAccount-Verifyer");
                pluginConfig.setString("collection", "ExTasTy");
                pluginConfig.setString("host", "localhost");
                pluginConfig.setString("username", "admin");
                pluginConfig.setString("password", "nimad");
                pluginConfig.setInteger("port", 27017);
                pluginConfig.save();
                System.out.println("After saving");
                System.out.println("After saving");
            } else {
                pluginConfig.load();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
