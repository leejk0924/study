package programmers.solution_1102;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 완주하지 못한 선수
public class Solution1 {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> marathon = new HashMap();
        for (int i = 0; i < participant.length; i++) {
            marathon.put(participant[i], marathon.getOrDefault(participant[i], 0)+1);
        }
        for (int i = 0; i < completion.length; i++) {
            marathon.put(completion[i], marathon.get(completion[i]) - 1);
        }
        for (String s : marathon.keySet()) {
            if (marathon.get(s) != 0) {
                answer = s;
                break;
            }
        }
        return answer;
    }

    public String solution2(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        int i;
        for (i = 0; i < completion.length; i++) {
            if (!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return participant[i];
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        String[] participant = {"mislav", "stanko", "mislav", "ana"};
        String[] completion = {"stanko", "ana", "mislav"};
        String solution1 = solution.solution(participant, completion);
        System.out.println("result = " + solution1);

    }
}
