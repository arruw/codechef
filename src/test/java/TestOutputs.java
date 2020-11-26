import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestOutputs {

    static Stream<String> problemCodeDataProvider() {
        final File[] dirs = new File("src/main/java/")
                .listFiles(File::isDirectory);
        return Arrays.stream(dirs)
                .map(d -> d.getName())
                .sorted();
    }

    @ParameterizedTest
    @MethodSource("problemCodeDataProvider")
    void testOutputs(final String problem) throws Exception {
        TestOutputs.assertProblemOutput(problem);
    }

    private static void assertProblemOutput(final String problemCode) throws Exception {
        final String rootPath = "src/main/java/" + problemCode;
        final String stdinPath = rootPath + "/.stdin";
        final String stdoutPath = rootPath + "/.stdout";

        final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        System.setIn(new FileInputStream(stdinPath));
        System.setOut(new PrintStream(stdout));

        runMainMethod(problemCode);

        final String expectedStdout = new String(Files.readAllBytes(Paths.get(stdoutPath)));

        assertEquals(expectedStdout.trim(), stdout.toString().trim());
    }

    private static void runMainMethod(final String problemCode) throws Exception {
        Class<?> cls = Class.forName(problemCode + ".Main");
        Method meth = cls.getMethod("main", String[].class);
        String[] params = null;
        meth.invoke(null, (Object) params);
    }
}
