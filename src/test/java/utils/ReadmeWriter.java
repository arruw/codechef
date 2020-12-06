package utils;

import org.junit.jupiter.api.extension.*;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReadmeWriter implements TestWatcher {

    private static final String CODECHEF_URL = "https://www.codechef.com";

    public static void initialize() {
        try {
            Files.copy(Paths.get("./README-template.md"), Paths.get("./README.md"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
        final String[] problemCode = testDisplayName.split("] ")[1].split("/");
        final boolean isPractice = problemCode[0].equals("Practice");
        final String descriptionLinkMd = isPractice
                ? String.format("[description](%s/problems/%s) ", CODECHEF_URL, problemCode[1])
                : String.format("[description](%s/%s/problems/%s) ", CODECHEF_URL, problemCode[0], problemCode[1]);
        final String solutionLinkMd = String.format("[solution](src/main/java/%s/%s)", problemCode[0], problemCode[1]);
        final String submissionsLinkMd = isPractice
                ? String.format("[submissions](%s/status/%s,matjazmav)", CODECHEF_URL, problemCode[1])
                : String.format("[submissions](%s/%s/status/%s,matjazmav)", CODECHEF_URL, problemCode[0], problemCode[1]);
        FileWriter writer = null;
        try {
            writer = new FileWriter("./README.md", true);
            writer.append(String.format("| %s/%s | %s | %s | %s | %s |\n", isPractice ? "" : problemCode[0], problemCode[1], emoji, descriptionLinkMd, solutionLinkMd, submissionsLinkMd));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
