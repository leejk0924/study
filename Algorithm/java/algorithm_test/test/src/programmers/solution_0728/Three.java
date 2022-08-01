package programmers.solution_0728;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/12950
 * 행렬의 덧셈
 */
public class Three {
    public static void main(String[] args) {

    }

    public int[][] solution(int[][] arr1, int[][] arr2) {
        // 행과 열의 크기는 동일
        int[][] answer = new int[arr1.length][arr1[0].length];

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                answer[i][j] = answer[i][j] + arr2[i][j];
            }
        }
        return answer;
    }
}
