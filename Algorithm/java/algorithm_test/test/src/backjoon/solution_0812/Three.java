package backjoon.solution_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2501
 * 약수구하기 (2501 번)
 */

public class Three {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (N % i == 0) {
                result.add(i);
            }
        }
        if (result.size()<K) {
            System.out.println(0);
        } else {
            System.out.println(result.get(K-1));
        }
    }
}
