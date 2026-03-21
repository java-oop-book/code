package chap07.sect5;

/**
 * Demonstrates the use of initializer lists and for-each loops with 
 * two-dimensional arrays.
 *
 * @author Drue Coles
 */
public class AnimalTable {

   public static void main(String[] args) {

      // initialize 3x5 array of animal names 
      String[][] animals = {
         {"buffalo", "cheetah", "gorilla", "lemming", "lobster"},
         {"meerkat", "markhor", "octopus", "penguin", "quetzal"},
         {"raccoon", "tuatara", "vulture", "wallaby", "whippet"}
      };

      System.out.println(format(animals));

      // Remove vowels from animal names. 
      for (String[] row : animals) {
         for (int j = 0; j < row.length; j++) {
            row[j] = String.format("%7s", 
                    row[j].replaceAll("[aeiou]", ""));
         }
      }
      System.out.println(format(animals));
   }

   /**
    * Returns a string obtained by concatenating the elements of arr
    * with newline characters to preserve tabular structure.
    */
   private static String format(String[][] arr) {
      String result = "";
      for (String[] row : arr) {
         for (String item : row) {
            result += item + " ";
         }
         result += "\n";
      }
      return result;
   }
}
