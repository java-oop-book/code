package chap3.sect1;

/**
 * Demonstrates string immutability.
 *
 * @author Drue Coles
 */
public class ImmutabilityDemo {

   public static void main(String[] args) {
      String animal = "quokka";
      System.out.println(animal);

      // toUpperCase does not mutate the string
      System.out.println(animal.toUpperCase());
      System.out.println(animal);

      // changing the reference, not the referent
      animal = animal.toUpperCase();
      System.out.println(animal);
   }
}
