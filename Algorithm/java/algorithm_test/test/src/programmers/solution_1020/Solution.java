package programmers.solution_1020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    // contains 에서 하나 틀린다.  -> 접두사만 체크하는 것이 아니므로 틀림   startsWith 사용
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].contains(phone_book[i])) {
                return false;
            }
        }
        return true;
    }
    // startsWith
    public boolean solution2(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean solution3(String[] phone_book) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            map.put(phone_book[i], i);
        }
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 0; j < phone_book[i].length(); j++) {
                if (map.containsKey(phone_book[i].substring(0, j))) {
                    return false;
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] a = {"119", "97674223", "1195524421"};
        String[] b = {"123", "456", "789"};
        String[] c = {"12", "123", "1235", "567", "88"};
        System.out.println("a = " + solution.solution3(a));
        System.out.println("b = " + solution.solution3(b));
        System.out.println("c = " + solution.solution3(c));
    }
}
