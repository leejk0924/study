package programmers.solution_1102;

import java.util.Arrays;
import java.util.Stack;

public class Solution2 {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i : arr) {
            if (!stack.empty() && stack.peek() == i) {
                stack.pop();
            }
            stack.add(i);
        }

        return stack.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        int[] solution = solution2.solution(arr);
        System.out.println("solution = " + Arrays.toString(solution));
    }
}
