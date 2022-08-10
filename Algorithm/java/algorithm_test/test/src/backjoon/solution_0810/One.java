package backjoon.solution_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * https://www.acmicpc.net/problem/9095
 * 1,2,3 더하기
 */
public class One {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        for (int j = 0; j < i; j++) {
            System.out.println(cal(i));
        }
    }
    public static int cal(int data) {
        if (data == 1) {
            return 1;
        } else if (data == 2) {
            return 2;
        } else if (data == 3) {
            return 4;
        } else if (data <= 0) {
            return 0;
        }
        return cal(data - 1) + cal(data - 2) + cal(data - 3);
    }
}