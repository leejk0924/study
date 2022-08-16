package backjoon.solution_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/11047
 * 동전 0 (11047번)
 */

public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K= Integer.parseInt(st.nextToken());

        Integer[] coins = new Integer[N];
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(coins, Collections.reverseOrder());

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (K >= coins[i]) {
                count += (K / coins[i]);
                K %= coins[i];
                if (K <= 0) {
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
