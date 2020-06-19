package travelup.hdfs;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        FilenameFilter csvFilter = (f, name) -> name.endsWith(".csv");
        FilenameFilter jsonFilter = (f, name) -> name.endsWith(".json");

        List<String> files = new ArrayList<>();

        File tourpediaFolder = new File("src/main/resources/tourpedia");
        for (File file : tourpediaFolder.listFiles(csvFilter)) {
            files.add("tourpedia/" + file.getName());
        }

        File tripadvisorFolder = new File("src/main/resources/tripadvisor");
        for (File file : tripadvisorFolder.listFiles(jsonFilter)) {
            files.add("tripadvisor/" + file.getName());
        }

        File reviewsFolder = new File("src/main/resources/tripadvisor/reviews");
        for (File file : reviewsFolder.listFiles(jsonFilter)) {
            files.add("tripadvisor/reviews/" + file.getName());
        }

        System.out.printf("\n\n ==== Loading %d files into HDFS!\n\n", files.size());

        for (String fileName : files) {
            try {
                new HDFSWriter().run(fileName);
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("** Error for file: " + fileName);
            }
        }
    }
}
