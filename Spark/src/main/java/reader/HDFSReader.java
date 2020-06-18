package reader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSReader implements Reader {

    private Configuration config;
    private FileSystem fs;

    public HDFSReader() {
        try {
            this.config = new Configuration();
            config.set("fs.hdfs.impl", org.apache.hadoop.hdfs.DistributedFileSystem.class.getName());
            config.set("fs.file.impl", org.apache.hadoop.fs.LocalFileSystem.class.getName());
            this.fs = FileSystem.get(new URI("hdfs://localhost:9000"), config);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public BufferedReader open(String file) throws IOException {
        Path path = new Path("/user/bdm/" + file);
        if (!this.fs.exists(path)) {
            System.out.println("File " + file + " does not exist!");
            System.exit(1);
        }
        return new BufferedReader(new InputStreamReader(this.fs.open(path)));
    }
}
