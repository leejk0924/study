package programmers.solution_1116;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12945
public class Lessons_12945 {
    public int solution(int n) {

        int[] arr = new int[n];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i < n; i++) {
            arr[i] = add(arr[i - 2], arr[i - 1]);
        }

        return arr[n-1];
    }

    public int add(int a, int b) {
        return a + b;
    }

}
