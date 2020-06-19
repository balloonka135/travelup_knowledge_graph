package travelup.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HDFSWriter {

    private FileSystem fs;


    public void run(String fileName) throws IOException {
        Configuration config = new Configuration();
        config.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
        config.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());

        try {
            this.fs = FileSystem.get(new URI("hdfs://localhost:9000"), config);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Path inputPath = new Path("src/main/resources/" + fileName);
        Path hdfsPath = new Path("/user/bdm/" + fileName);

        if (this.fs.exists(hdfsPath)) {
            System.out.println("File " + fileName + " already exists!");
            this.fs.close();
            return;
        }
        fs.copyFromLocalFile(inputPath, hdfsPath);
        this.fs.close();
    }

}
