import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, PriorityQueue<int[]>> map = new HashMap<>();
        
        HashMap<String, Integer> sumMap = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            sumMap.merge(genres[i], plays[i], Integer::sum);
            map.computeIfAbsent(genres[i], k -> new PriorityQueue<>((a,b) -> {
                if (b[1] == a[1]) {
                    return a[0] - b[0];
                }
                return b[1] - a[1];
            })).add(new int[]{i, plays[i]});
        }
        
        List<String> sumList = new ArrayList<>(sumMap.keySet());
        Collections.sort(sumList, (a, b) -> sumMap.get(b) - sumMap.get(a));
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < sumList.size(); i++) {
            String genre = sumList.get(i);
            
            PriorityQueue<int[]> q = map.get(genre);
            for (int j = 0; j < 2; j++) {
                if (q.isEmpty()) {
                    break;
                }
                int[] cur = q.poll();
                int idx = cur[0];
                result.add(idx);
            }
        }
        
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}