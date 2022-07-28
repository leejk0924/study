package programmers.solution_0728;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12954
 * x만큼 간격이 있는 n개의 숫자
 */

public class Two {
    public static void main(String[] args) {
        Two two = new Two();
        long[] solution = two.solution(2, 5);
        for (long l : solution) {
            System.out.println(l);
        }
    }
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long result =0;
        for (int i = 0; i < n; i ++) {
            result += x;
            answer[i] = result;
        }
        return answer;
    }
}
