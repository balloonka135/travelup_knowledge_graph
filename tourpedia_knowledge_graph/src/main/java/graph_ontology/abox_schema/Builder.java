package graph_ontology.abox_schema;

import graph_ontology.config.Config;
import graph_ontology.reader.HDFSReader;
import graph_ontology.reader.LocalReader;
import graph_ontology.reader.Reader;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.BufferedReader;
import java.io.IOException;

public class Builder {

    private static Reader reader;
    private static Config conf;

    public Builder(Boolean isDev) {
        if (isDev) {
            reader = new LocalReader();
        } else {
            reader = new HDFSReader();
        }
        conf = new Config(isDev);
    }

    public  void build_category(Model model) throws IOException {
        Property rdfType = model.getProperty(conf.RDFTYPE_URL);
        Resource categoryResource = model.getResource(conf.CATEGORY_BASE_URL);
        Property categoryNameProp = model.getProperty(conf.CATEGORY_NAME_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.CATEGORY_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String categoryURI = row_data[0];
            String categoryName = row_data[1];

            Resource currentCategory = model.createResource(conf.BASE_URL + categoryURI)
                    .addLiteral(categoryNameProp, categoryName);
            model.add(model.createStatement(currentCategory, rdfType, categoryResource));
        }
        csvReader.close();
    }

    public  void build_location(Model model) throws IOException {
        Property rdfType = model.getProperty(conf.RDFTYPE_URL);
        Resource locationResource = model.getResource(conf.LOCATION_BASE_URL);
        Property locationAddressProp = model.getProperty(conf.ADDRESS_PROPERTY_URL);
        Property locationCityProp = model.getProperty(conf.CITY_PROPERTY_URL);
        Property locationLatProp = model.getProperty(conf.LATITUDE_PROPERTY_URL);
        Property locationLongProp = model.getProperty(conf.LONGITUDE_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.LOCATION_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String locationURI = row_data[0];
            String locationAddr = row_data[1];
            String locationCity = row_data[2];
            String locationLat = row_data[3];
            String locationLong = row_data[4];

            String locationAddress = locationAddr.replace(" ", "_");

            Resource currentLocation = model.createResource(conf.BASE_URL + locationURI)
                    .addLiteral(locationCityProp, locationCity)
                    .addLiteral(locationLatProp, locationLat)
                    .addLiteral(locationLongProp, locationLong);

            if (!(locationAddr.equals("NaN"))) {
                currentLocation.addLiteral(locationAddressProp, locationAddress);
            }

            model.add(model.createStatement(currentLocation, rdfType, locationResource));
        }
        csvReader.close();
    }

    public  void build_place(Model model) throws IOException {
        Property rdfType = model.getProperty(conf.RDFTYPE_URL);
        Resource placeResource = model.getResource(conf.PLACE_BASE_URL);
        Property placeNameProp = model.getProperty(conf.PLACE_NAME_PROPERTY_URL);
        Property placeDescriptionProp = model.getProperty(conf.DESCRIPTION_PROPERTY_URL);
        Property placePhoneNumberProp = model.getProperty(conf.PHONE_NUMBER_PROPERTY_URL);
        Property placeIntPhoneNumberProp = model.getProperty(conf.INT_PHONE_NUMBER_PROPERTY_URL);
        Property placeWebsiteProp = model.getProperty(conf.WEBSITE_PROPERTY_URL);
        Property placeIconProp = model.getProperty(conf.ICON_PROPERTY_URL);
        Property placeSubcategoryProp = model.getProperty(conf.SUB_CATEGORY_PROPERTY_URL);
        Property placeStatisticsProp = model.getProperty(conf.STATISTICS_PROPERTY_URL);
        Property placePolarityProp = model.getProperty(conf.PLACE_POLARITY_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.PLACE_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String placeURI = row_data[0];
            String pName = row_data[1];
            String placeDesc = row_data[2];
            String placePhoneNumber = row_data[3];
            String placeInPhoneNumber = row_data[4];
            String placeWebsite = row_data[5];
            String placeIcon = row_data[6];
            String placeSubcategory = row_data[7];
            String placeStatistics = row_data[8];
            String placePolarity = row_data[9];

            String placeName = pName.replace(" ", "_");
            String placeDescription = placeDesc.replace(" ", "_");

            Resource currentPlace = model.createResource(conf.BASE_URL + placeURI)
                    .addLiteral(placeNameProp, placeName)
                    .addLiteral(placeDescriptionProp, placeDescription)
                    .addLiteral(placeStatisticsProp, placeStatistics);

            if (!(placePhoneNumber.equals("NaN"))) {
                currentPlace.addLiteral(placePhoneNumberProp, placePhoneNumber);
            }

            if (!(placeInPhoneNumber.equals("NaN"))) {
                currentPlace.addLiteral(placeIntPhoneNumberProp, placeInPhoneNumber);
            }

            if (!(placeWebsite.equals("NaN"))) {
                currentPlace.addLiteral(placeWebsiteProp, placeWebsite);
            }

            if (!(placeIcon.equals("NaN"))) {
                currentPlace.addLiteral(placeIconProp, placeIcon);
            }

            if (!(placeSubcategory.equals("NaN"))) {
                currentPlace.addLiteral(placeSubcategoryProp, placeSubcategory);
            }

            if (!(placePolarity.equals("NaN"))) {
                currentPlace.addLiteral(placePolarityProp, placePolarity);
            }

            model.add(model.createStatement(currentPlace, rdfType, placeResource));
        }
        csvReader.close();
    }

    public  void build_review(Model model) throws IOException {
        Property rdfType = model.getProperty(conf.RDFTYPE_URL);
        Resource reviewResource = model.getResource(conf.REVIEW_BASE_URL);
        Property languageProp = model.getProperty(conf.LANGUAGE_PROPERTY_URL);
        Property polarityProp = model.getProperty(conf.REVIEW_POLARITY_PROPERTY_URL);
        Property ratingProp = model.getProperty(conf.RATING_PROPERTY_URL);
        Property sourceProp = model.getProperty(conf.REVIEW_SOURCE_PROPERTY_URL);
        Property textProp = model.getProperty(conf.REVIEW_TEXT_PROPERTY_URL);
        Property timeProp = model.getProperty(conf.REVIEW_TIME_PROPERTY_URL);
        Property wordsCountProp = model.getProperty(conf.WORDS_COUNT_PROPERTY_URL);
        Property detailsProp = model.getProperty(conf.REVIEW_DETAILS_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.PLACE_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String reviewURI = row_data[0];
            String language = row_data[1];
            String polarity = row_data[2];
            String rating = row_data[3];
            String source = row_data[4];
            String text = row_data[5];
            String time = row_data[6];
            String wordsCount = row_data[7];
            String details = row_data[8];

            Resource currentReview = model.createResource(conf.BASE_URL + reviewURI)
                    .addLiteral(languageProp, language)
                    .addLiteral(polarityProp, polarity)
                    .addLiteral(ratingProp, rating)
                    .addLiteral(sourceProp, source)
                    .addLiteral(textProp, text)
                    .addLiteral(timeProp, time)
                    .addLiteral(wordsCountProp, wordsCount)
                    .addLiteral(detailsProp, details);

            model.add(model.createStatement(currentReview, rdfType, reviewResource));
        }
        csvReader.close();
    }

    public  void build_has_category(Model model) throws IOException {
        Property hasCategory = model.getProperty(conf.PLACE_CATEGORY_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.HAS_CATEGORY_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String placeURI = row_data[0];
            String categoryURI = row_data[1];

            Resource placeResource = model.getResource(conf.BASE_URL + placeURI);
            Resource categoryResource = model.getResource(conf.BASE_URL + categoryURI);

            model.add(model.createStatement(placeResource, hasCategory, categoryResource));
        }
        csvReader.close();
    }

    public  void build_is_about(Model model) throws IOException {
        Property isAbout = model.getProperty(conf.REVIEW_PLACE_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.IS_ABOUT_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String reviewURI = row_data[0];
            String placeURI = row_data[1];

            Resource reviewResource = model.getResource(conf.BASE_URL + reviewURI);
            Resource placeResource = model.getResource(conf.BASE_URL + placeURI);

            model.add(model.createStatement(reviewResource, isAbout, placeResource));
        }
        csvReader.close();
    }

    public  void build_located_at(Model model) throws IOException {
        Property locatedAt = model.getProperty(conf.PLACE_LOCATION_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.LOCATED_AT_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String placeURI = row_data[0];
            String locationURI = row_data[1];

            Resource placeResource = model.getResource(conf.BASE_URL + placeURI);
            Resource locationResource = model.getResource(conf.BASE_URL + locationURI);

            model.add(model.createStatement(placeResource, locatedAt, locationResource));
        }
        csvReader.close();
    }

}
