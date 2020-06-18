package config;

public class Config {
    public static String ATTRACTION_PATH = "src/main/resources/Attraction_Description.csv";
 

    public Config(Boolean isDev) {
        if (!isDev) {
            this.ATTRACTION_PATH = "tripadvisor/Attraction_Description.csv";
        }
    }
}
