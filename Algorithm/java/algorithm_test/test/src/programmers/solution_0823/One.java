package programmers.solution_0823;

public class One {

    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/17682
     * [1차] 다트 게임
     *
     */
    public static void main(String[] args) {
        One one = new One();
        System.out.println(one.solution("1D2S#10S"));
    }

    public int solution(String dartResult) {

        int answer = 0;
        int n = 0, index = 0, nowInt = 0;

        int[] number = new int[3];

        char[] chars = dartResult.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            // Character.isDigit()  = Char 타입이 정수인지 확인
            if (Character.isDigit(chars[i])) {
                n = 0;
                if (chars[i] == '1' && chars[i + 1] == '0') {
                    n = 10;
                    i++;
                } else {
                    n = Character.getNumericValue(chars[i]);
                }
                nowInt++;
            } else {
                switch (chars[i]) {
                    case 'S':
                        number[index++] = (int)Math.pow(n, 1);
                        break;
                    case 'D':
                        number[index++] = (int)Math.pow(n, 2);
                        break;
                    case 'T':
                        number[index++] = (int)Math.pow(n, 3);
                        break;
                    case '*':
                        index = index - 2 < 0 ? 0 : index - 2;
                        while (index < nowInt) {
                            number[index++] *= 2;
                        }
                        n = 0;
                        break;
                    case '#':
                        number[index - 1] *= -1;
                        n = 0;
                        break;
                }
            }
        }

        for (int i : number) {
            answer += i;
        }
        return answer;
    }
}

