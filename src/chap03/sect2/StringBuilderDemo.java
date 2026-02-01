package chap03.sect2;

/**
 * Demonstrates the use of StringBuilders for mutable character sequences.
 *
 * @author Drue Coles
 */
public class StringBuilderDemo {

   public static void main(String[] args) {
      StringBuilder country = new StringBuilder("FINLAND");
      System.out.println(country);
      country.replace(0, 3, "ICE"); // mutator
      System.out.println(country);

      StringBuilder word = new StringBuilder("GATEMAN");
      System.out.println(word);
      word.reverse(); // mutator
      System.out.println(word);
   }
}
