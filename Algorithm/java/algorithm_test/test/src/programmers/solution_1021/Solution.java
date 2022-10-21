package programmers.solution_1021;

import java.util.*;

public class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        int value = 1;
        Map<String, Integer> cloth = new HashMap<>();
        Set<String> name = new HashSet<>();

        for (String[] clothe : clothes) {
            name.add(clothe[1]);
        }

        for (String[] clothe : clothes) {
            if (cloth.containsKey(clothe[1])) {
                value++;
                cloth.put(clothe[1], value);
            }
            cloth.put(clothe[1], value);
        }
        Iterator<String> iterator = name.iterator();
        while (iterator.hasNext()) {
            answer *= (1+cloth.get(iterator.next()));
        }
        return answer-1;
    }

    // 해답 -> 풀이는 거의 유사했으나 기능을 몰라서 못풀었다고 생각
    public int solution1(String[][] clothes) {
        Map<String, Integer> cloth = new HashMap<>();
        for (String[] clothe : clothes) {
            // getOrDefault() : 찾는 키가 존재한다면 찾는 키의 값을 반환하고 없다면 기본 값을 반환하는 메서드
            cloth.put(clothe[1], cloth.getOrDefault(clothe[1], 0) + 1);
        }
        
        // map 을 iterator()
        Iterator<Integer> it = cloth.values().iterator();
        int answer = 1;

        while (it.hasNext()) {
            answer *= it.next().intValue() + 1;
        }
        return answer - 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[][] a = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] b = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};

        System.out.println(solution.solution(a));
        System.out.println("============");
        System.out.println(solution.solution(b));

    }
}
