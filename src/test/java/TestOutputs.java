import org.junit.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestOutputsLogger.class)
public class TestOutputs {

    static {
        try {
            Files.copy(Paths.get("./README-template.md"), Paths.get("./README.md"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

class TestOutputsLogger implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        write(context.getDisplayName(), "✔️");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        write(context.getDisplayName(), "❌️");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        write(context.getDisplayName(), "\uD83D\uDEA7️");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        write(context.getDisplayName(), "\uD83D\uDEA7️");
    }

    private static void write(final String testDisplayName, final String emoji) {
        final String problemCode = testDisplayName.split("] ")[1];
        FileWriter writer = null;
        try {
            writer = new FileWriter("./README.md", true);
            writer.append(String.format("* %s %s ", emoji, problemCode));
            writer.append(String.format("[description](https://www.codechef.com/problems/%s) ", problemCode));
            writer.append(String.format("[solution](src/main/java/%s)", problemCode));
            writer.append("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
