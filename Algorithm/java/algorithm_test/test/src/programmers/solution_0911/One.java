package programmers.solution_0911;

import java.util.stream.IntStream;

public class One {
    public int solution(int n) {
        int answer = 0;
        for (int x = 2; x < n; x++) {
            if (n % x == 1) {
                answer = x;
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        One solution = new One();
        System.out.println(solution.solution(12));
    }

    public int solution1(int n) {
        // stream 은 함수비용이 적은 코드에서는 성능이 좋지 않으므로, 단순 반복문으로 사용하는 것은 좋지 않음
        return IntStream.range(2, n).filter(i -> n % i == 1).findFirst().orElse(0);
    }
}
