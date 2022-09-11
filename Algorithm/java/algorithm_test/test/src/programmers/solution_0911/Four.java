package programmers.solution_0911;

import java.util.Stack;

public class Four {
    boolean solution(String s) {
        boolean answer = false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.push(s.charAt(i));
            } else {
                if (stack.empty()) {
                    return answer;
                } else if (stack.pop() != '(') {
                    return answer;
                }
            }
        }
        answer = (stack.empty()) ? true : false;
        return answer;
    }

    public static void main(String[] args) {
        Four four = new Four();
        System.out.println(four.solution("(()))"));

    }
}

//            th:
//            switch (s1[i]) {
//                case "(":
//                    stack.push("{");
//                    break;
//                case ")":
//                    if (stack.empty()) {
//                        answer = Boolean.FALSE;
//                        break th;
//                    }
//                    stack.pop();
//                    break;
//            }
