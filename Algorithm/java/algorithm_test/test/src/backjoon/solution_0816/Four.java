package backjoon.solution_0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Four {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[] dists = new long[N-1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N-1; i++) {
            dists[i] = Long.parseLong(st.nextToken());
        }

        long[] prices = new long[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            prices[i] = Long.parseLong(st.nextToken());
        }

        long minCost = dists[0] * prices[0];
        long minPrice = prices[0];

        for (int i = 1; i < N - 1; i++) {
            if (minPrice > prices[i]) {
                minPrice = prices[i];
            }
            minCost += minPrice * dists[i];
        }
        System.out.println(minCost);
    }
}

/**
 * https://www.acmicpc.net/problem/13305
 * 주유소 (13305번)
 */