package chap07.sect4;

import java.util.ArrayList;

/**
 * Demonstrates basic operations of the ArrayList class.
 *
 * @author Drue Coles
 */
public class AnimalList {

   public static void main(String[] args) {
      ArrayList<String> animals = new ArrayList<>();
      animals.add("ant");
      animals.add("bat");
      animals.add("cow");
      animals.add("fox");
      animals.add("yak");
      System.out.println(animals); // [ant, bat, cow, fox, yak]

      animals.add(2, "emu");
      System.out.println(animals); // [ant, bat, emu, cow, fox, yak]

      animals.set(3, "pig");
      System.out.println(animals); // [ant, bat, emu, pig, fox, yak]

      animals.remove(3);
      System.out.println(animals); // [ant, bat, emu, fox, yak]

      animals.remove("fox");
      System.out.println(animals); // [ant, bat, emu, yak]
   }
}
