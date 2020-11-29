package utils;

import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assert {
    public static void assertProblemSolution(final String problemCode) throws Exception {
        final String rootPath = "src/main/java/" + problemCode;

        for (final File stdinFile: new File(rootPath).listFiles((dir, name) -> name.endsWith("stdin"))) {
            final String stdinPath = stdinFile.getPath();
            final String stdoutPath = stdinPath.replace("stdin", "stdout");

            final String actualStdout;
            final PrintStream sout = System.out;
            try (
                    final ByteArrayOutputStream stdout = new ByteArrayOutputStream();
                    final PrintStream ps = new PrintStream(stdout);
            ) {
                System.out.flush();
                System.setOut(ps);
                runMainMethod(problemCode, new FileInputStream(stdinPath));
                System.out.flush();
                actualStdout = stdout.toString().trim();
            } finally {
                System.setOut(sout);
            }

            final String expectedStdout = new String(Files.readAllBytes(Paths.get(stdoutPath))).trim();
            assertEquals(expectedStdout, actualStdout, String.format("Test failed on problem %s and input %s.", problemCode, stdinPath));
        }
    }

    private static void runMainMethod(final String problemCode, final InputStream inputStream) throws Exception {
        Class<?> cls = Class.forName(problemCode + ".Main");
        setFinalStatic(cls.getDeclaredField("_in"), new Scanner(inputStream));
        cls.getMethod("main", String[].class)
                .invoke(null, (Object)null);
    }

    private static void setFinalStatic(Field field, Object newValue) throws Exception {
        field.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.set(null, newValue);
    }
}
