package backjoon.solution_0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * https://www.acmicpc.net/problem/2753
 * 윤년
 */
public class Two {
    public static void main(String[] args) throws IOException {
//        // 자바 11버전이라 String block 기능 못사용함 ㅠㅠ
//        String inputData = """2000""";
//        System.setIn(new ByteArrayInputStream(inputData.getBytes()));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(four(N));
    }
    static int four(int a) {
        if (a % 4 == 0) {
            return hundred(a);
        } else {
            return 0;
        }
    }
    static int hundred(int a) {
        if (a % 100 == 0) {
            return fourHundred(a);
        } else {
            return 1;
        }
    }
    static int fourHundred(int a) {
        if (a % 400 == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}

