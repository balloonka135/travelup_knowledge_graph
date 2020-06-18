package graph_ontology.config;

public class Config {
    public static final String OUTPUT_PATH = "src/main/resources/output/";
    public final String BASE_URL = "http://www.semanticweb.org/twitter#";
    public final String RDFTYPE_URL = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

    // Class IRIs
    public final String TWEET_BASE_URL = BASE_URL + "Tweet";
    public final String LOCATION_BASE_URL = BASE_URL + "Location";

    // DataType Properties IRIs
    public final String TWEET_CONTENT_PROPERTY_URL = BASE_URL + "tweet_content";
    public final String PUBLISHED_DATE_PROPERTY_URL = BASE_URL + "published_date";
    public final String LOCATION_NAME_PROPERTY_URL = BASE_URL + "location_name";

    // ObjectType Properties
    public final String TWEET_LOCATION_PROPERTY_URL = BASE_URL + "has_location";

    // nodes data
    public  String DATA_PATH = "src/main/resources/locations.csv";

    public Config(Boolean isDev) {
        if (!isDev) {
            this.DATA_PATH = "twitter/tweets.csv";
        }
    }
}
