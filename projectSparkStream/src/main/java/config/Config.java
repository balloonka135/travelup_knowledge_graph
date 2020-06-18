package config;

public class Config {
    public static String ATTRACTION_PATH = "src/main/resources/attractions_500.csv";
 

    public Config(Boolean isDev) {
        if (!isDev) {
            this.ATTRACTION_PATH = "tripadvisor/attractions_500.csv";
        }
    }
}
