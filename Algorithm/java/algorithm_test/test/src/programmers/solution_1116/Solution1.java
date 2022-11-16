package programmers.solution_1116;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/12931
public class Solution1 {
    public int mySolution(int n) {
        int answer = 0;


        String[] split = String.valueOf(n).split("");
        answer = Arrays.stream(split).mapToInt(Integer::parseInt).sum();
        return answer;
    }

    // 타입 변환 없이 풀수 있는 문제
    public int solution2(int n) {
        int answer = 0;
        while (true) {
            answer = n % 10;
            if (n < 0) {
                break;
            }
        }
        return answer;
    }
}
