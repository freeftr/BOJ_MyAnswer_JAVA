import java.io.*;
import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Integer>> infoMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (String s : info) {
            String[] elements = s.split(" ");
            int score = Integer.parseInt(elements[4]);
            comb(0, elements, "", score);
        }

        for (ArrayList<Integer> list : infoMap.values()) {
            Collections.sort(list);
        }

        for (int i = 0; i < query.length; i++) {
            String q = query[i].replaceAll(" and", "");
            String[] parts = q.split(" ");
            String key = parts[0] + parts[1] + parts[2] + parts[3];
            int targetScore = Integer.parseInt(parts[4]);

            ArrayList<Integer> scores = infoMap.getOrDefault(key, new ArrayList<>());
            int idx = lowerBound(scores, targetScore);
            answer[i] = scores.size() - idx;
        }

        return answer;
    }

    static void comb(int depth, String[] elements, String current, int score) {
        if (depth == 4) {
            infoMap.computeIfAbsent(current, k -> new ArrayList<>()).add(score);
            return;
        }

        comb(depth + 1, elements, current + elements[depth], score);
        comb(depth + 1, elements, current + "-", score);
    }

    static int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
