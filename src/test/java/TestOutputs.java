import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import utils.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import static utils.Assert.assertProblemSolution;

@ExtendWith(ReadmeWriter.class)
public class TestOutputs {

    static {
        ReadmeWriter.initialize();
    }

    static Stream<String> problemDataProvider() {
        return Arrays.stream(new File("src/main/java/").listFiles(File::isDirectory))
                .flatMap(d1 -> Arrays.stream(d1.listFiles(File::isDirectory)).map(d2 -> d1.getName() + "/" + d2.getName()))
                .sorted();
    }

    @ParameterizedTest
    @MethodSource("problemDataProvider")
    void testOutputs(final String problemCode) throws Exception {
        assertProblemSolution(problemCode);
    }
}
