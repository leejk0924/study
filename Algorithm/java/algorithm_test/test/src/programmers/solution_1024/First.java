package programmers.solution_1024;

import java.util.Stack;

public class First {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i : arr) {
            if (!stack.empty() && stack.peek() == i) {
                stack.pop();
            }
            stack.add(i);
        }

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        return stack.stream().mapToInt(i->i).toArray();
    }

    public static void main(String[] args) {
        First first = new First();
        int[] a = {1, 1, 3, 3, 0, 1, 1};
//        int[] b = {4,4,4,3,3};


        int[] solution1 = first.solution(a);
        for (int i : solution1) {
            System.out.println("i = " + i);
        }

        System.out.println("==========");
//        solution.solution(b);
    }
}
