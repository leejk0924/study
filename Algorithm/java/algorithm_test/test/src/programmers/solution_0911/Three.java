package programmers.solution_0911;

import java.util.stream.Stream;

public class Three {
    public String solution(String s) {
        String answer = "";

        String[] s1 = s.split(" ");
        Integer[] integers = Stream.of(s1).mapToInt(Integer::parseInt).sorted().boxed().toArray(Integer[]::new);
        StringBuffer sb = new StringBuffer();
        sb.append(integers[0]);
        sb.append(" ");
        sb.append(integers[integers.length-1]);
        answer = sb.toString();
        return answer;
    }

    public static void main(String[] args) {
        Three three = new Three();
        System.out.println(three.solution("-1 -2 -3 -4"));
    }
}
