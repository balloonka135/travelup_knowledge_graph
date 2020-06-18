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

    public void build_tweet(Model model) throws IOException {
        Property rdfType = model.getProperty(conf.RDFTYPE_URL);
        Resource tweetResource = model.getResource(conf.TWEET_BASE_URL);
        Property tweetContentProp = model.getProperty(conf.TWEET_CONTENT_PROPERTY_URL);
        Property tweetPublishedDateProp = model.getProperty(conf.PUBLISHED_DATE_PROPERTY_URL);

        BufferedReader csvReader = reader.open(conf.DATA_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String tweetURI = row_data[0];
            String tweetContent = row_data[2];
            String tweetPublishedDate = row_data[3];

            Resource currentTweet = model.createResource(conf.BASE_URL + tweetURI)
                    .addLiteral(tweetContentProp, tweetContent)
                    .addLiteral(tweetPublishedDateProp, tweetPublishedDate);
            model.add(model.createStatement(currentTweet, rdfType, tweetResource));
        }
        csvReader.close();
    }

    public void build_location(Model model) throws IOException {
        Property rdfType = model.getProperty(conf.RDFTYPE_URL);
        Resource locationResource = model.getResource(conf.LOCATION_BASE_URL);
        Property locationNameProp = model.getProperty(conf.LOCATION_NAME_PROPERTY_URL);

        BufferedReader csvReader = reader.open(conf.DATA_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String locationName = row_data[1];

            Resource currentLocation = model.createResource(conf.BASE_URL + locationName)
                    .addLiteral(locationNameProp, locationName);
            model.add(model.createStatement(currentLocation, rdfType, locationResource));
        }
        csvReader.close();
    }

    public void build_has_location(Model model) throws IOException {
        Property hasLocation = model.getProperty(conf.TWEET_LOCATION_PROPERTY_URL);

        BufferedReader csvReader =  reader.open(conf.DATA_PATH);
        String row;
        while ((row = csvReader.readLine()) != null) {
            String[] row_data = row.split(",");

            String tweetURI = row_data[0];
            String locationURI = row_data[1];

            Resource tweetResource = model.getResource(conf.BASE_URL + tweetURI);
            Resource locationResource = model.getResource(conf.BASE_URL + locationURI);

            model.add(model.createStatement(tweetResource, hasLocation, locationResource));
        }
        csvReader.close();
    }
}