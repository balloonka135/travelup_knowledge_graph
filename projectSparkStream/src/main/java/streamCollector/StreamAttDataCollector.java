package streamCollector;

import org.apache.spark.streaming.api.java.JavaPairDStream;
import scala.Tuple2;
import twitter4j.Status;
import org.apache.spark.streaming.api.java.JavaDStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StreamAttDataCollector {

	public static void getTweets(JavaDStream<Status> statuses) throws FileNotFoundException, IOException
	{
		// filter tweets that talk about Barcelona attractions
		List<String> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/attractions_500.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(values[0]);
		    }
		}
		 JavaDStream<Status> BTweets = statuses
		.filter(tweet ->stringContainsItemFromList(tweet.getText(),records));
		
		// create pair of (location,content and date) from filtered tweets
			JavaPairDStream<String, String> BTweetsPairs = BTweets
			.mapToPair(
					tweet -> {
						String content = tweet.getText();
						String location = getLocation(content,records);
						return new Tuple2<String, String>(location, content + " , "+ tweet.getCreatedAt());
					}
					);
			BTweetsPairs.print();
			BTweetsPairs.foreachRDD(rdd ->{
	          if(!rdd.isEmpty()){
	        	  rdd.coalesce(1).saveAsTextFile("src/main/resources/resultsAttractions");
	          }
	      });
	}
	
	public static boolean stringContainsItemFromList(String inputStr, List<String> items) {
		return items.stream().anyMatch(s -> inputStr.contains(s));
	}
	public static String getLocation(String inputStr, List<String> items) {
		 String match = "";
		    for (String s : items) {
		       if(inputStr.contains(s)){
		           match = s;
		           break;
		       }
		    }
		    return match;
	}
	
}

