package graph_ontology.reader;

import java.io.BufferedReader;
import java.io.IOException;

public interface Reader {
    BufferedReader open(String file) throws IOException;
}
