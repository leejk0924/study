package item6;

public class Strings {
    public static void main(String[] args) {
        String hello = "hello";
        // 해당 방법을 사용 X
        String hello2 = new String("hello");

        String hello3 = "hello";

        // false
        System.out.println(hello == hello2);
        System.out.println(hello.equals(hello2));

        System.out.println();
        // true
        System.out.println(hello == hello3);
        System.out.println(hello.equals(hello3));

    }
}
