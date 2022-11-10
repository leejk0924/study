package programmers.solution_1110;

import java.util.*;


// Queue 인터페이스를 간접적으로 구현한 클래스는 상당히 많다. (Deque, BlockingDeque, BlockingQueue, TransferQueue)
// 그중에서도 Deque 인터페이스를 구현한 LinkedList 클래스가 큐 메모리 구조를 구현하는데 가장 많이 사용됨
/*
* Queue 메서드
* boolean add(E e) : 맨 뒤에 요소 삽입 / 삽업 성공하면 true, 실패하면 IllegalStateException 발생
* boolean offer(E e) : 큐 맨 뒤에 전달된 요소 삽입
*
* E element() : 맨 앞의 요소 반환
* E peek() : 맨 앞의 요소를 반환 / 큐가 비어있으면 null 반환
*
* E poll() : 맨앞에 있는 요소를 반환하고, 큐에서 제거 / 큐가 비어있으면 null 반환
* E remove() : 맨앞에 있는 요소 제거거*
* */

public class Solution1 {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            var remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain);
            // 해당 인덱스보다 작은 값이 있는지 확인
            if (!q.isEmpty() && q.peek() < date) {
                answerList.add(q.size());
                q.clear();
            }
            q.offer(date);
        }
        answerList.add(q.size());

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public int[] solution2(int[] progresses, int[] speeds) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            var remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain);
            if (!queue.isEmpty() && queue.peek() < date) {
                answerList.add(queue.size());
                queue.clear();
            }
            queue.offer(date);
        }
        answerList.add(queue.size());
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        System.out.println(Arrays.toString(solution1.solution(progresses, speeds)));
    }
}
