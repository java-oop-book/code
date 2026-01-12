package chap8.sect5;

import java.util.concurrent.ThreadLocalRandom;

/**
 * A collection of dice that can be rolled individually or all at once.
 *
 * @author Drue Coles
 */
public class CupOfDice {

   private final int[] dice;
   private final int sides;

   /**
    * Creates a cup of dice with random initial values.
    */
   public CupOfDice(int numDice, int sides) {
      this.dice = new int[numDice];
      this.sides = sides;
      roll();
   }

   /**
    * Creates a cup of 6-sided dice with random initial values.
    */
   public CupOfDice(int numDice) {
      this(numDice, 6);
   }

   /**
    * Returns the current dice values.
    */
   public int[] getDice() {
      return dice.clone();
   }

   /**
    * Rolls each die in the cup.
    */
   public void roll() {
      for (int i = 0; i < dice.length; i++) {
         roll(i);
      }
   }

   /**
    * Rolls a specified die.
    *
    * @param i index of the die to roll
    */
   public void roll(int i) {
      dice[i] = ThreadLocalRandom.current().nextInt(1, sides + 1);
   }
}
