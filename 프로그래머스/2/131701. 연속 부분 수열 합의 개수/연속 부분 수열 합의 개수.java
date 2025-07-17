import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int length = elements.length;
        int[] doubleElements = new int[length * 2];
        int[] prefix = new int[length * 2 + 1];

        for (int i = 0; i < length * 2; i++) {
            doubleElements[i] = elements[i % length];
        }

        for (int i = 1; i <= length * 2; i++) {
            prefix[i] = prefix[i - 1] + doubleElements[i - 1];
        }

        Set<Integer> set = new HashSet<>();

        for (int subLen = 1; subLen <= length; subLen++) {
            for (int start = 0; start < length; start++) {
                int end = start + subLen;
                int sum = prefix[end] - prefix[start];
                set.add(sum);
            }
        }

        return set.size();
    }
}
