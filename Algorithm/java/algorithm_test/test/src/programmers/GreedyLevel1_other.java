package programmers;

public class GreedyLevel1_other {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] cloth = new int[n];

        for (int a : reserve) {
            cloth[a-1]++;
        }
        for (int b : lost) {
            cloth[b-1]--;
        }

        return answer;
    }

}
