import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) throws Exception {
        Model model = ModelFactory.createDefaultModel();

        TripAdvisor_Builder.build_tag(model);
        TripAdvisor_Builder.build_location(model);
        TripAdvisor_Builder.build_review(model);
        TripAdvisor_Builder.build_has_tag(model);
        TripAdvisor_Builder.build_is_about(model);


        model.write(new PrintStream(
                new BufferedOutputStream(
                        new FileOutputStream(TripAdvisor_Config.OUTPUT_PATH + "abox.ntD")), true), "NT");
    }
}