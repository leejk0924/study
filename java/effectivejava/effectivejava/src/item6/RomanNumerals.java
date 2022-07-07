package item6;

import java.util.regex.Pattern;

public class RomanNumerals {
    // 정규표현식은 만드는 비용이 비싸다 (cpu 리소스를 많이 사용)

    static boolean isRomanNumeralSlow(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    
    // 값비싼 객체를 재사용해 성능 개선
    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches();
    }

    public static void main(String[] args) {
        boolean result = false;
        long start = System.nanoTime();
        for (int j = 0; j < 100; j++) {
            // 성능 차이를 확인하려면  xxxSlow 메서드를 xxxFast 메서드로 바꿔 실행
            result = isRomanNumeralSlow("MCMLXXVI");
//            result = isRomanNumeralFast("MCMLXXVI");

        }
        long end = System.nanoTime();
        System.out.println(end - start);
        System.out.println("result = " + result);
    }
}
