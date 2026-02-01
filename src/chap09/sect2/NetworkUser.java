package chap09.sect2;

/**
 * An immutable network user with a login name and password.
 *
 * @author Drue Coles
 */
public class NetworkUser implements SimpleComparable {

   private final String login;
   private final String password;

   /**
    * Constructs a network user with a login name and password.
    */
   public NetworkUser(String login, String password) {
      this.login = login;
      this.password = password;
   }

   /**
    * Returns this user's login name.
    */
   public String getLogin() {
      return login;
   }

   /**
    * Returns this user's password.
    */
   public String getPassword() {
      return password;
   }

   /**
    * Returns the concatenation of this user's name and password.
    */
   @Override
   public String toString() {
      return login + " (" + password + ")";
   }

   /**
    * Returns a negative integer, zero, or a positive integer depending on whether this login name
    * is less than, equal to, or greater than another in ASCII order.
    */
   @Override
   public int compareTo(Object o) {
      NetworkUser other = (NetworkUser) o;
      return this.login.compareTo(other.login);
   }
}
