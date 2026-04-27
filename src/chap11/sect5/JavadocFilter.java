package chap11.sect5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Reads a Java source file and outputs a copy with Javadoc comments removed using a line-based
 * heuristic: exclude lines equal to "/**" or starting with "*" (after trimming whitespace).
 *
 * @author Drue Coles
 */
public class JavadocFilter {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter input and output filenames: ");
        Path inputPath = Path.of(in.next());
        Path outputPath = Path.of(in.next());

        try {
            String content = Files.readString(inputPath);
            String stripped = stripCommentLines(content);
            Files.writeString(outputPath, stripped);
            System.out.println("Output written to: " + outputPath);
        } catch (NoSuchFileException e) {
            System.err.println("File not found: " + inputPath);
        } catch (IOException e) {
            System.err.printf("Error processing files: %s or %s %n", inputPath, outputPath);
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns a copy of the given text with Javadoc comments removed.
     */
    private static String stripCommentLines(String content) {
        StringBuilder result = new StringBuilder();
        String[] lines = content.split("\\R");

        for (String line : lines) {
            String trimmed = line.stripLeading();
            if (trimmed.equals("/**") || trimmed.startsWith("*")) {
                continue;
            }
            result.append(line).append(System.lineSeparator());
        }

        return result.toString();
    }
}
