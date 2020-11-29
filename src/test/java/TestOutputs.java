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
                .map(d -> d.getName())
                .sorted();
    }

    @ParameterizedTest
    @MethodSource("problemDataProvider")
    void testOutputs(final String problemCode) throws Exception {
        assertProblemSolution(problemCode);
    }
}
