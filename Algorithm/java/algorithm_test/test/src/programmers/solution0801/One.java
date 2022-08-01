package programmers.solution0801;

import java.util.Arrays;


/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12944
 * 평균 구하기
 */

public class One {
    public static void main(String[] args) {
        One one = new One();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;

        }
        System.out.println(one.solution(arr));
    }

    public double solution(int[] arr) {
        double answer =  Arrays.stream(arr).average().getAsDouble();
        return answer;
    }

    public double solution2(int[] arr) {
        return Arrays.stream(arr).average().orElse(0);
    }

    public double solution3(int[] arr) {
        int sum = 0;
        for (int s : arr) {
            sum += s;
        }
        return sum / arr.length;
    }
}
