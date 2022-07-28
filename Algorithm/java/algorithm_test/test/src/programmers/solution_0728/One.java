package programmers.solution_0728;

import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12969
 * 직사각형 별찍기
 */
public class One {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] s = line.split(" ");

        Integer a = Integer.parseInt(s[0]);
        Integer b = Integer.parseInt(s[1]);

        for (int i = 0; i < b; i++) {
            for (int j = 0; j < a; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void Solution1() {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        /**
         * StringBuilder : 1개 이상의 문자열을 더할 경우 사용
         */
        IntStream.range(0, a).forEach(s -> sb.append("*"));
        IntStream.range(0, b).forEach(s->System.out.println(sb.toString()));
        /**
         * IntStream : int 타입의 스트림 생성
         * - range : 범위
         */
    }
}
