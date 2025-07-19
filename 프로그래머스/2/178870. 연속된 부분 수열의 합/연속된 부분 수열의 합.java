import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer;
        int left = 0;
        int right = 0;
        int minL = 0;
        int minR = 0;
        int minLength = Integer.MAX_VALUE;

        int[] prefix = new int[sequence.length];
        prefix[0] = sequence[0];
        for (int i = 1; i < sequence.length; i++) {
            prefix[i] = prefix[i - 1] + sequence[i];
        }

        while (left <= right && right < sequence.length) {
            int sum;
            if (left > 0) {
                sum = prefix[right] - prefix[left - 1];
            } else {
                sum = prefix[right];
            }

            if (sum == k) {
                if (right - left < minLength) {
                    minLength = right - left;
                    minL = left;
                    minR = right;
                }
                right++;
            } else if (sum < k) {
                right++;
            } else {
                left++;
            }

            if (left > right) {
                right = left;
            }
        }

        answer = new int[]{minL, minR};

        return answer;
    }
}
