package graph_ontology;

import graph_ontology.config.Config;
import graph_ontology.abox_schema.Builder;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        Model model = ModelFactory.createDefaultModel();
        Builder builder = new Builder(false);

        builder.build_category(model);
        builder.build_location(model);
        builder.build_place(model);
        builder.build_review(model);
        builder.build_has_category(model);
        builder.build_is_about(model);
        builder.build_located_at(model);

        model.write(new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream(Config.OUTPUT_PATH + "abox.ntD")), true), "NT");
    }
}