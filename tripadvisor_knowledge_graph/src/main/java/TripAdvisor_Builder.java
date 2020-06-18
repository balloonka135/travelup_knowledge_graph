import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TripAdvisor_Builder {
    
    public static void build_tag(Model model) throws IOException {
        Property rdfType = model.getProperty(TripAdvisor_Config.RDFTYPE_URL);
        Resource tagResource = model.getResource(TripAdvisor_Config.Tag_BASE_URL);
        Property tagNameProp = model.getProperty(TripAdvisor_Config.tagName_PROPERTY_URL);

        BufferedReader csvReader = new BufferedReader(new FileReader(TripAdvisor_Config.TAG_PATH));
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String tagURI = row_data[1];
            String tagName = row_data[0];


            if(!tagName.equals("")){
                String[] taglist = tagName.split("[|]");
                for(String tag:taglist){
                    Resource currentTag = model.createResource(TripAdvisor_Config.BASE_URL + tagURI)
                            .addLiteral(tagNameProp, tag);
                    model.add(model.createStatement(currentTag, rdfType, tagResource));
                }
            }



        }
        csvReader.close();
    }

    public static void build_location(Model model) throws IOException {
        Property rdfType = model.getProperty(TripAdvisor_Config.RDFTYPE_URL);
        Resource locationResource = model.getResource(TripAdvisor_Config.LOCATION_BASE_URL);
        Property locationNameProp = model.getProperty(TripAdvisor_Config.Location_Name_PROPERTY_URL);
        Property locationMinPriceProp = model.getProperty(TripAdvisor_Config.Location_MinPrice_PROPERTY_URL);
        Property locationRankProp = model.getProperty(TripAdvisor_Config.Location_Rank_PROPERTY_URL);
        Property locationTotalReviewsProp = model.getProperty(TripAdvisor_Config.Location_totalReviews_PROPERTY_URL);
        Property locationReviewsAVGProp = model.getProperty(TripAdvisor_Config.Location_ReviewsAVG_PROPERTY_URL);
        Property locationURLProp = model.getProperty(TripAdvisor_Config.Location_URL_PROPERTY_URL);

        BufferedReader csvReader = new BufferedReader(new FileReader(TripAdvisor_Config.LOCATION_PATH));
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String locationUri = row_data[0];
            String locationName = row_data[1];
            String locationURL = row_data[2];
            String locationRank = row_data[3];
            String locationReviewAVG = row_data[4];
            String locationtotalReviews = row_data[5];
            String locationPrice= row_data[6];



            Resource currentLocation = model.createResource(TripAdvisor_Config.BASE_URL + locationUri)
                    .addLiteral(locationNameProp, locationName);


            model.add(model.createStatement(currentLocation, rdfType, locationResource));
        }
        csvReader.close();
    }


    public static void build_review(Model model) throws IOException {
        Property rdfType = model.getProperty(TripAdvisor_Config.RDFTYPE_URL);
        Resource reviewResource = model.getResource(TripAdvisor_Config.REVIEW_BASE_URL);
        Property placeTypeProp = model.getProperty(TripAdvisor_Config.review_PlaceType_PROPERTY_URL);
        Property urlProp = model.getProperty(TripAdvisor_Config.Review_URL_NUMBER_PROPERTY_URL);
        Property ratingProp = model.getProperty(TripAdvisor_Config.Review_Rating_PROPERTY_URL);
        Property textProp = model.getProperty(TripAdvisor_Config.Review_Text_PROPERTY_URL);
        Property dateProp = model.getProperty(TripAdvisor_Config.Review_Date_PROPERTY_URL);


        BufferedReader csvReader = new BufferedReader(new FileReader(TripAdvisor_Config.REVIEW_PATH));
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(";");

            String reviewURI = row_data[2];
            String placeType = row_data[1];
            String url = row_data[3];
            String text = row_data[4];
            String date = row_data[5];
            String rating = row_data[6];

            Resource currentReview = model.createResource(TripAdvisor_Config.BASE_URL + reviewURI)
                    .addLiteral(placeTypeProp, placeType)
                    .addLiteral(urlProp, url)
                    .addLiteral(ratingProp, rating)
                    .addLiteral(textProp, text)
                    .addLiteral(dateProp, date);


            model.add(model.createStatement(currentReview, rdfType, reviewResource));
        }
        csvReader.close();
    }

    public static void build_has_tag(Model model) throws IOException {
        Property hasTag = model.getProperty(TripAdvisor_Config.has_tag_PROPERTY_URL);

        BufferedReader csvReader = new BufferedReader(new FileReader(TripAdvisor_Config.HAS_TAG_PATH));
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(";");

            String placeURI = row_data[0];
            String tagURI = row_data[1];

            Resource placeResource = model.getResource(TripAdvisor_Config.BASE_URL + placeURI);
            Resource tagResource = model.getResource(TripAdvisor_Config.BASE_URL + tagURI);

            model.add(model.createStatement(placeResource, hasTag, tagResource));
        }
        csvReader.close();
    }

    public static void build_is_about(Model model) throws IOException {
        Property isAbout = model.getProperty(TripAdvisor_Config.about_location_PROPERTY_URL);

        BufferedReader csvReader = new BufferedReader(new FileReader(TripAdvisor_Config.ABOUT_LOCATION_PATH));
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(";");

            String reviewURI = row_data[1];
            String placeURI = row_data[0];

            Resource reviewResource = model.getResource(TripAdvisor_Config.BASE_URL + reviewURI);
            Resource placeResource = model.getResource(TripAdvisor_Config.BASE_URL + placeURI);

            model.add(model.createStatement(reviewResource, isAbout, placeResource));
        }
        csvReader.close();
    }



}
