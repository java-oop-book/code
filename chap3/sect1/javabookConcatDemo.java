package chap3.sect1;

/**
 * Demonstrates string concatenation.
 *
 * @author Drue Coles
 */
public class ConcatDemo {

    public static void main(String[] args) {
        String s1 = "butter";
        String s2 = "fly";
        String s3 = s1 + s2;
        System.out.println(s3);

        System.out.println(2 + 3 + 5);
        System.out.println("2 + 3 + 5");
        System.out.println("2" + "3" + "5");
        System.out.println("2" + 3 + "5");
        System.out.println("2" + 3 + 5);
        System.out.println(2 + 3 + "5");
    }
}
