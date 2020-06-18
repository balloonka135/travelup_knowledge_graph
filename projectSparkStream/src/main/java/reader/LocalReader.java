package reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LocalReader implements Reader {
    public BufferedReader open(String file) throws IOException {
        return new BufferedReader(new FileReader(file));
    }
}
