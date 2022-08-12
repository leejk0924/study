package backjoon.solution_0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2884
 * 알람 시계 (2884번)
 */
public class One {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str, " ");

        int H = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        M = M - 45;
        if (M <0) {
            M = M + 60;
            H = H-1;
            if (H < 0) {
                H = H+24;
            }
        }
        System.out.println(H + " " + M);
    }

    // 강사님 풀이
    void OtherSolution(int M, int H) {
        if (M >= 45) {
            System.out.println(H +" " + (M-45));
        } else if (H > 0 && M < 45) {
            System.out.println((H - 1) + " " + (M + 15));
        } else {
            System.out.println(23 + " " +(M + 15));
        }
    }
}
