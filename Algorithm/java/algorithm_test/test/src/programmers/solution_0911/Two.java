package programmers.solution_0911;

public class Two {
    public String solution(String s) {
        String answer = "";
        // split limit 0인 경우 , zero length string 은 무시된다. , 양수인 경우 limit 을 넘지 못하고, 음수인 경우 zero length string 도 포함한다.
        String[] s1 = s.split(" ", -1);
        for (int i = 0; i < s1.length; i++) {
            char[] chars = s1[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if (j % 2 == 1) {
                    chars[j] = Character.toLowerCase(chars[j]);
                } else {
                    chars[j] = Character.toUpperCase(chars[j]);

                }
            }
            s1[i] = String.valueOf(chars);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s1.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            sb.append(s1[i]);
        }
        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        Two two = new Two();

        System.out.println(two.solution("maybe this problem     "));
    }

    public String solution1(String s) {

        String answer = "";
        int cnt = 0;
        String[] array = s.split("");

        for(String ss : array) {
            cnt = ss.contains(" ") ? 0 : cnt + 1;
            answer += cnt%2 == 0 ? ss.toLowerCase() : ss.toUpperCase();
        }
        return answer;
    }
}
