package graph_ontology.config;

public class Config {
    public static final String OUTPUT_PATH = "src/main/resources/output/";
    public final String BASE_URL = "http://www.semanticweb.org/tourpedia#";
    public final String RDFTYPE_URL = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

    // Class IRIs
    public final String REVIEW_BASE_URL = BASE_URL + "Review";
    public final String PLACE_BASE_URL = BASE_URL + "Place";
    public final String LOCATION_BASE_URL = BASE_URL + "Location";
    public final String CATEGORY_BASE_URL = BASE_URL + "Category";

    // DataType Properties IRIs
    public final String LANGUAGE_PROPERTY_URL = BASE_URL + "language";
    public final String REVIEW_POLARITY_PROPERTY_URL = BASE_URL + "review_polarity";
    public final String RATING_PROPERTY_URL = BASE_URL + "rating";
    public final String REVIEW_SOURCE_PROPERTY_URL = BASE_URL + "review_source";
    public final String REVIEW_TEXT_PROPERTY_URL = BASE_URL + "review_text";
    public final String REVIEW_TIME_PROPERTY_URL = BASE_URL + "review_time";
    public final String WORDS_COUNT_PROPERTY_URL = BASE_URL + "words_count";
    public final String REVIEW_DETAILS_PROPERTY_URL = BASE_URL + "review_details";
    public final String PLACE_NAME_PROPERTY_URL = BASE_URL + "place_name";
    public final String PHONE_NUMBER_PROPERTY_URL = BASE_URL + "phone_number";
    public final String INT_PHONE_NUMBER_PROPERTY_URL = BASE_URL + "international_phone_number";
    public final String DESCRIPTION_PROPERTY_URL = BASE_URL + "description";
    public final String WEBSITE_PROPERTY_URL = BASE_URL + "website";
    public final String ICON_PROPERTY_URL = BASE_URL + "icon";
    public final String SUB_CATEGORY_PROPERTY_URL = BASE_URL + "sub_category";
    public final String STATISTICS_PROPERTY_URL = BASE_URL + "statistics";
    public final String PLACE_POLARITY_PROPERTY_URL = BASE_URL + "place_polarity";
    public final String ADDRESS_PROPERTY_URL = BASE_URL + "address";
    public final String CITY_PROPERTY_URL = BASE_URL + "city";
    public final String LATITUDE_PROPERTY_URL = BASE_URL + "latitude";
    public final String LONGITUDE_PROPERTY_URL = BASE_URL + "longitude";
    public final String CATEGORY_NAME_PROPERTY_URL = BASE_URL + "category_name";

    // ObjectType Properties
    public final String PLACE_CATEGORY_PROPERTY_URL = BASE_URL + "has_category";
    public final String REVIEW_PLACE_PROPERTY_URL = BASE_URL + "is_about";
    public final String PLACE_LOCATION_PROPERTY_URL = BASE_URL + "located_at";

    // nodes data
    public  String REVIEW_PATH = "src/main/resources/reviews.csv";
    public String PLACE_PATH = "src/main/resources/places.csv";
    public  String LOCATION_PATH = "src/main/resources/locations.csv";
    public  String CATEGORY_PATH = "src/main/resources/categories.csv";

    // relationships data
    public  String HAS_CATEGORY_PATH = "src/main/resources/has_category.csv";
    public  String IS_ABOUT_PATH = "src/main/resources/is_about.csv";
    public  String LOCATED_AT_PATH = "src/main/resources/located_at.csv";

    public Config(Boolean isDev) {
        if (!isDev) {
            this.PLACE_PATH = "tourpedia/places.csv";
            this.LOCATION_PATH = "tourpedia/locations.csv";
            this.CATEGORY_PATH = "tourpedia/categories.csv";
            this.HAS_CATEGORY_PATH = "tourpedia/has_category.csv";
            this.IS_ABOUT_PATH = "tourpedia/is_about.csv";
            this.LOCATED_AT_PATH = "tourpedia/located_at.csv";
        }
    }
}
