package programmers.solution0801;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12948
 * 핸드폰 번호 가리기
 */

public class Two {
    public static void main(String[] args) {
        System.out.println(Two.solution3("01036976098"));
    }
    public static String solution(String phone_number) {
        String answer = "";
        String[] split = phone_number.split("");
        for (int i = 0; i < split.length-4; i++) {
            split[i] = "*";
        }
        answer = String.join("",split);
        return answer;
    }

    public static String solution2(String phone_number) {
        char[] ch = phone_number.toCharArray();
        for(int i = 0; i < ch.length - 4; i ++){
            ch[i] = '*';
        }
        return String.valueOf(ch);
    }

    public static String solution3(String phone_number) {
        return phone_number.replaceAll(".(?=.{4})", "*");
    }
}
