package programmers.solution_1116;
// https://school.programmers.co.kr/learn/courses/30/lessons/12928
public class Lessons_12928 {
    public int mySolution(int n) {
        int answer = 0;
        for (int i = 1; i < n+1; i++) {
            if (n % i == 0) {
                answer += i;
            }
        }
        answer = answer + n;
        return answer;
    }

    // 약수 이므로 절반만 돌려도 된다.
    public int solution(int n) {
        int answer = 0;
        for (int i = 1; i < n/2+1; i++) {
            if (n % i == 0) {
                answer += i;
            }
        }
        answer = answer + n;
        return answer;
    }
}
