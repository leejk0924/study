package backjoon.solution_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.acmicpc.net/problem/2012
 * 등수매기기 (2012번)
 */

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] rankgings = new Integer[N];

        // int 넣으면 틀림, 이렇게 안되는 경우 결과값을 long 으로 사용
        long min = 0;

        for (int i = 0; i < N; i++) {
            rankgings[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rankgings);

        for (int i = 0; i < N; i++) {
            min += Math.abs((i+1) - rankgings[i]);
        }
        System.out.println(min);
    }
}
