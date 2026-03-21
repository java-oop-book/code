package chap07.sect1;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Outputs a custom line of poetry of the following form:
 *
 *      Dear [name], I will [verb] your [adverb] [adjective] [noun] forever! ♥ ♥ ♥
 *
 * @author Drue Coles
 */
public class LoveLetterGenerator {

   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter recipient's name: ");
      String name = in.next();

      String[] verbs = {"cherish", "embrace", "endure", "fear", "follow", "seek", "treasure"};
      String[] adverbs = {"deceptively", "intensely", "ominously", "strangely", "unspeakably"};
      String[] adjectives = {"ancient", "blinding", "eternal", "overflowing", "shimmering"};
      String[] nouns = {"doom", "echo", "flame", "heart", "smile", "reflection", "shadow", "soul"};

      String verb = get(verbs);
      String adverb = get(adverbs);
      String adjective = get(adjectives);
      String noun = get(nouns);

      final String message = "Dear %s, I will %s your %s %s %s forever! ♥ ♥ ♥%n";
      System.out.printf(message, name, verb, adverb, adjective, noun);
   }

   /**
    * Returns a randomly chosen string from a given array.
    */
   private static String get(String[] array) {
      ThreadLocalRandom rand = ThreadLocalRandom.current();
      int index = rand.nextInt(array.length);
      return array[index];
   }
}
