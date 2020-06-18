import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Main {
	
	public static void main(String[] args) throws Exception {
		SparkConf conf = new SparkConf().setAppName("SparkTraining").setMaster("local[*]");
        JavaSparkContext ctx = new JavaSparkContext(conf);
		
		if (args.length < 1) {
			throw new Exception("Wrong number of parameters, usage: (labels1,labels2)");
		}

		if (args[0].equals("labels1")) {
            System.out.println(Labeling.basicAnalysis(ctx));
        }
		else {
			throw new Exception("Wrong number of argument");
		}
	}
}

