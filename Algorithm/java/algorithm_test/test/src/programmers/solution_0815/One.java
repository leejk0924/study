package programmers.solution_0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12947
 * 하샤드 수
 */

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();

        int inputNumber = Integer.parseInt(number);
        int[] arrNumber = Stream.of(number.split("")).mapToInt(Integer::parseInt).toArray();

        int sum = 0;
        for (int i : arrNumber) {
            sum += i;
        }

        if (inputNumber % sum == 0) {
            System.out.println(Boolean.TRUE);

        } else {
            System.out.println(Boolean.FALSE);
        }
    }
}
