package chap11.sect6;

import java.io.IOException;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Reads a file containing color names and RGB values, and outputs the names and values for bright
 * shades of red. Each line of the input file is expected to consist of a name followed by three
 * integers in the range 0-255 denoting RGB components.
 *
 * @author Drue Coles
 */
public class ShadesOfRed {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter input file: ");
        Path inputPath = Path.of(keyboard.next());

        try (Scanner fileScanner = new Scanner(inputPath)) {
            while (fileScanner.hasNext()) {
                try {
                    String name = fileScanner.next();
                    int r = fileScanner.nextInt();
                    int g = fileScanner.nextInt();
                    int b = fileScanner.nextInt();
                    if (isBrightRed(r, g, b)) {
                        System.out.printf("%-10s %3d %3d %3d%n", name, r, g, b);
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Invalid number format. Skipping record.");
                    fileScanner.nextLine(); // discard rest of the line
                } catch (NoSuchElementException e) {
                    System.err.println("Incomplete record at end of file.");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error opening file: " + inputPath);
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns true if (r, g, b) denotes a bright shade of red.
     */
    private static boolean isBrightRed(int r, int g, int b) {
        return r - g >= 128 && r - b >= 128;
    }
}
