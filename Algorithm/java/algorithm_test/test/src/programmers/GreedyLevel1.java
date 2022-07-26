package programmers;

public class GreedyLevel1 {
    public static void main(String[] args) {
        GreedyLevel1 greedyLevel1 = new GreedyLevel1();
        int[] lost = {3};
        int[] reserve = {1};
        System.out.println(greedyLevel1.solution(3, lost, reserve));
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int[] totalStudent = new int[n];

        for (int a : reserve) {
            totalStudent[a-1]++;
        }
        for (int b : lost) {
            totalStudent[b-1]--;
        }

        if (totalStudent[0] == -1) {
            if (totalStudent[1] == 1) {
                totalStudent[0]++;
                totalStudent[1]--;
            }
        }
        if (totalStudent[n-1] == -1) {
            if (totalStudent[n - 1] == 1) {
                totalStudent[n]++;
                totalStudent[n - 1]--;
            }
        }
        for (int i = 1; i < n-1; i++) {
            if (totalStudent[i] == -1) {
                if (totalStudent[i - 1] == 1) {
                    totalStudent[i - 1]--;
                    totalStudent[i]++;
                }
                if (totalStudent[i + 1] == 1) {
                    totalStudent[i + 1]--;
                    totalStudent[i]++;
                }
            }
        }
        int lostPeople = 0;
        for (int i : totalStudent) {
            if (i == -1) {
                lostPeople++;
            }
        }
        int answer = n-lostPeople;

        return answer;
    }
}




