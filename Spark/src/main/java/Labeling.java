import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Labeling {
	
	public static String basicAnalysis(JavaSparkContext ctx) {
		String out = "";
		
		JavaRDD<String> attDesc = ctx.textFile("src/main/resources/Attraction_Description.csv");
		
		JavaRDD<Object> attDescPairs = attDesc
                .mapToPair(f -> 
                {
                	if (f.split(",").length>1)
                	{return new Tuple2<String, String>(f.split(",")[0],f.split(",")[1]);
                	}
                	else
                	{return new Tuple2<String, String>(f.split(",")[0],"");
                	}
                })
				.map(t -> {
					String content = t._2.replaceAll("[^a-zA-Z\\s]", "").trim().toLowerCase();;
					ArrayList<String> allWords = Stream.of(content.split(" ")).collect(Collectors.toCollection(ArrayList<String>::new));
					if (allWords.size()>0){
					allWords.removeAll(StopWords.getWords());
					}
					return new Tuple2<String, String>(t._1, allWords.stream().collect(Collectors.joining(" ")));
                });
		
		 attDescPairs.saveAsTextFile("src/main/resources/cleanedDesc.txt");
		
		return out;
	}
}

