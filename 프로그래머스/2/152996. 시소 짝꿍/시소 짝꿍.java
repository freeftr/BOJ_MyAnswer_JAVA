import java.io.*;
import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> set = new ArrayList<>();

        for (int weight : weights) {
            map.put(weight, map.getOrDefault(weight, 0) + 1);
        }

        set = new ArrayList<>(map.keySet());

        for (int i = 0; i < set.size(); i++) {
            int curWeight = set.get(i);
            int curCount = map.get(curWeight);

            if (curCount > 1) {
                answer += (long)curCount * (curCount - 1) / 2;
            }

            for (int j = i + 1; j < set.size(); j++) {
                int nextWeight = set.get(j);
                int nextCount = map.get(nextWeight);

                if ((long)curWeight * 2 == (long)nextWeight * 3 ||
                    (long)curWeight * 2 == (long)nextWeight * 4 ||
                    (long)curWeight * 3 == (long)nextWeight * 2 ||
                    (long)curWeight * 3 == (long)nextWeight * 4 ||
                    (long)curWeight * 4 == (long)nextWeight * 2 ||
                    (long)curWeight * 4 == (long)nextWeight * 3) {
                    
                    answer += (long)curCount * nextCount;
                }
            }
        }

        return answer;
    }
}
