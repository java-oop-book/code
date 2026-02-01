package chap03.sect1;

/**
 * Demonstrates instance methods of the String class and method chaining.
 *
 * @author Drue Coles
 */
public class InstanceMethodDemo {

   public static void main(String[] args) {
        String s1 = "capybara";
        System.out.println(s1);
        System.out.println(s1.replace('a', 'o'));

        // swap halves
        int n = s1.length();
        String left = s1.substring(0, n / 2);
        String right = s1.substring(n / 2);
        System.out.println(right + left);

        System.out.println(s1.replace("pyba", "").repeat(2));
    }
}
