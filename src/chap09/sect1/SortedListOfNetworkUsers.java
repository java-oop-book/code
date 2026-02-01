package chap09.sect1;

import java.util.ArrayList;

/**
 * A list of network users stored in ASCII order by login name.
 *
 * @author Drue Coles
 */
public class SortedListOfNetworkUsers {

   private final ArrayList<NetworkUser> users = new ArrayList<>();

   /**
    * Returns the size of the list.
    */
   public int size() {
      return users.size();
   }

   /**
    * Returns the network user at a given position in the list.
    */
   public NetworkUser get(int i) {
      return users.get(i);
   }

   /**
    * Adds a network user at correct position by login name.
    */
   public void add(NetworkUser user) {
      int insertionPoint = 0;
      String nameToAdd = user.getLogin();

      while (insertionPoint < size()) {
         String nameInList = get(insertionPoint).getLogin();
         if (nameToAdd.compareTo(nameInList) <= 0) {
            break;
         }
         insertionPoint++;
      }
      users.add(insertionPoint, user);
   }

   /**
    * Test program that adds four network users to a list and then traverses it to verify that the
    * objects are stored in order by login name.
    */
   public static void main(String[] args) {
      SortedListOfNetworkUsers users = new SortedListOfNetworkUsers();
      NetworkUser n1 = new NetworkUser("washington", "usa123");
      NetworkUser n2 = new NetworkUser("adams", "july4hello");
      NetworkUser n3 = new NetworkUser("jefferson", "virginia1743");
      NetworkUser n4 = new NetworkUser("madison", "password");
      users.add(n1);
      users.add(n2);
      users.add(n3);
      users.add(n4);

      // verify login order
      for (int i = 0; i < users.size(); i++) {
         System.out.println(users.get(i));
      }
   }
}
