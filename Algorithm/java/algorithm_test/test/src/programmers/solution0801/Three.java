package programmers.solution0801;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/68644
 * 두 개 뽑아서 더하기
 */
public class Three {
    public static void main(String[] args) {
        int[] a = {5,0,2,7};


        int[] solution = Three.solution(a);
        for (int i : solution) {
            System.out.println(i);
        }
    }

    public static int[] solution(int[] numbers) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j < numbers.length; j++) {

                int a = numbers[i] + numbers[j];
                if (list.indexOf(a) < 0) {
                    list.add(a);
                }
            }
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
}
