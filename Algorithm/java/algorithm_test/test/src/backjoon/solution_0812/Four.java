package backjoon.solution_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * https://www.acmicpc.net/problem/1110
 * 더하기 사이클(1110번)
 */

public class Four {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int updateNum = N;
        int count = 0;
        int A = 0;
        int B = 0;
        int C = 0;

        while (true) {
            A = updateNum / 10; // 10의 자리수
            B = updateNum % 10; // 1의 자리수
            C = (A + B) % 10;
            updateNum = (B * 10) + C;
            count++;
            if (updateNum == N) {
                break;
            }
        }
        System.out.println(count);
    }
}
