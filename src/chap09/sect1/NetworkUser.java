package chap09.sect1;

/**
 * An immutable network user with a login name and password.
 *
 * @author Drue Coles
 */
public class NetworkUser {

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
}
