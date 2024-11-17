import java.util.*;
class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliverySum = 0;
        int pickupSum = 0;

        for (int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            deliverySum += deliveries[i];
            pickupSum += pickups[i];

            while (deliverySum > 0 || pickupSum > 0) {
                deliverySum -= cap;
                pickupSum -= cap;
                cnt++;
            }

            answer += (i + 1) * 2L * cnt;
        }

        return answer;
    }
}