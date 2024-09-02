package bigjango.logcapitator;

import net.fabricmc.api.ModInitializer;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;

public class Logcapitator implements ModInitializer {
    public static final String MOD_ID = "logcapitator";
    public static boolean inTreeGen = false;
    public static boolean reverse = false;

    static {
        Properties prop = new Properties();
        prop.setProperty("reverse", "0");

        ConfigHandler config = new ConfigHandler(MOD_ID, prop);
        reverse = config.getInt("reverse") == 1;
    }

    @Override
    public void onInitialize() {
    }
}
