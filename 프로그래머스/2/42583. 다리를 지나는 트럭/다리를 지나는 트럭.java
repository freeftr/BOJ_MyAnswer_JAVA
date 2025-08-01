import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<int[]> bridge = new LinkedList<>();
        Queue<Integer> waiting = new LinkedList<>();

        for (int truck_weight : truck_weights) {
            waiting.add(truck_weight);
        }

        int sum = 0;
        while (!waiting.isEmpty() || !bridge.isEmpty()) {
            answer++;

            if (!bridge.isEmpty()) {
                int[] front = bridge.peek();
                if (answer - front[1] >= bridge_length) {
                    bridge.poll();
                    sum -= front[0];
                }
            }

            if (!waiting.isEmpty()) {
                int next = waiting.peek();
                if (sum + next <= weight && bridge.size() < bridge_length) {
                    bridge.add(new int[]{next, answer});
                    sum += next;
                    waiting.poll();
                }
            }
        }

        return answer;
    }
}
