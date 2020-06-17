public class TripAdvisor_Config {

    public static final String BASE_URL = "http://www.semanticweb.org/tourpedia#";
    public static final String OUTPUT_PATH = "src/main/resources/output/";
    public static final String RDFTYPE_URL = "http://www.w3.org/1999/02/22-rdf-syntax-ns#type";

    // nodes data
    public static final String REVIEW_PATH = "src/main/resources/reviews.csv";
    public static final String TAG_PATH = "src/main/resources/tags.csv";
    public static final String LOCATION_PATH = "src/main/resources/attractions.csv";

    // relationships data
    public static final String HAS_TAG_PATH = "src/main/resources/has_tag.csv";
    public static final String ABOUT_LOCATION_PATH = "src/main/resources/about_attraction.csv";

    // Class IRIs
    public static final String REVIEW_BASE_URL = BASE_URL + "Review";
    public static final String LOCATION_BASE_URL = BASE_URL + "Location";
    public static final String Tag_BASE_URL = BASE_URL + "Tag";

    // DataType Properties IRIs
    public static final String review_PlaceType_PROPERTY_URL = BASE_URL + "ReviewPlaceType";
    public static final String Location_MinPrice_PROPERTY_URL = BASE_URL + "LocationMinPrice";
    public static final String Location_Name_PROPERTY_URL = BASE_URL + "LocationName";
    public static final String Location_Rank_PROPERTY_URL = BASE_URL + "LocationRank";
    public static final String Location_ReviewsAVG_PROPERTY_URL = BASE_URL + "LocationReviewsAVG";
    public static final String Location_totalReviews_PROPERTY_URL = BASE_URL + "LocationTotalReviews";
    public static final String Location_URL_PROPERTY_URL = BASE_URL + "LocationURL";
    public static final String Review_Date_PROPERTY_URL = BASE_URL + "ReviewDate";
    public static final String Review_Rating_PROPERTY_URL = BASE_URL + "ReviewRating";
    public static final String Review_Text_PROPERTY_URL = BASE_URL + "ReviewText";
    public static final String Review_URL_NUMBER_PROPERTY_URL = BASE_URL + "ReviewURL";
    public static final String tagName_PROPERTY_URL = BASE_URL + "tagName";


    // ObjectType Properties
    public static final String about_location_PROPERTY_URL = BASE_URL + "about_location";
    public static final String has_tag_PROPERTY_URL = BASE_URL + "has_tag";


}