package chap09.sect2;

import java.util.ArrayList;

/**
 * Sorted list of arbitrary comparable objects.
 *
 * @author Drue Coles
 */
public class SortedList {

   private final ArrayList<SimpleComparable> list = new ArrayList<>();

   /**
    * Returns the size of this list.
    */
   public int size() {
      return list.size();
   }

   /**
    * Returns the item at a given position of the list.
    */
   public SimpleComparable get(int i) {
      return list.get(i);
   }

   /**
    * Adds an item to the list at correct position in sorted order.
    */
   public void add(SimpleComparable itemToAdd) {
      int insertionPoint = 0;
      while (insertionPoint < size()) {
         SimpleComparable listItem = get(insertionPoint);
         if (itemToAdd.compareTo(listItem) <= 0) { // late binding 
            break;
         }
         insertionPoint++;
      }
      list.add(insertionPoint, itemToAdd);
   }
}
