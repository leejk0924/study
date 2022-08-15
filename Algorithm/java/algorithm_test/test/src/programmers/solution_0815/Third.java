package programmers.solution_0815;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/1845
 * 포켓몬
 */
public class Third {
    public static void main(String[] args) {
        Third third = new Third();
        int[] nums = {3, 1, 2, 3};


        int solution = third.solution(nums);
        System.out.println(solution);

    }

    public int solution(int[] nums) {
        int answer = 0;
        int getPo = nums.length /2;
        int[] collect = Arrays.stream(nums).distinct().toArray();
        if (collect.length > getPo) {
            answer = getPo;
        } else {
            answer = collect.length;
        }
        return answer;
    }

    public int solution1(int[] nums) {
        HashSet<Integer> hs = new HashSet();
        for (int num : nums) {
            hs.add(num);
        }
        if (hs.size() > nums.length / 2) {
            return nums.length / 2;
        }
        return hs.size();
    }
}
