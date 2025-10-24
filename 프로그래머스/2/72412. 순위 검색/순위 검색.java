import java.util.*;

class Solution {
    static HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        
        for (String in : info) {
            String[] s = in.split(" ");
            makeKey(0, s, new StringBuilder());
        }
        
        for (String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        int idx = 0;
        for (String q : query) {
            StringBuilder sb = new StringBuilder();
            String[] s = q.split(" ");
            int score = Integer.parseInt(s[s.length - 1]);
                
            for (int i = 0; i < s.length - 1; i++) {
                if (s[i].equals("and")) continue;
                sb.append(s[i]);
            }
            
            ArrayList<Integer> scores = map.get(sb.toString());
            
            if (scores == null) {
                answer[idx++] = 0;
                continue;
            }
            
            int left = 0;
            int right = scores.size();
            
            while (left < right) {
                int mid = (left + right) / 2;
                
                if (scores.get(mid) >= score) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            
            answer[idx++] = scores.size() - left;
        }
        return answer;
    }
    
    static void makeKey(int depth, String[] s, StringBuilder cur) {
        if (depth == 4) {
            int score = Integer.parseInt(s[4]);
            map.computeIfAbsent(cur.toString(), k -> new ArrayList<>()).add(score);
            return;
        }

        cur.append("-");
        makeKey(depth + 1, s, cur);
        cur.deleteCharAt(cur.length() - 1);
        
        cur.append(s[depth]);   
        makeKey(depth + 1, s, cur);
        cur.setLength(cur.length() - s[depth].length());
    }

}

/*
조건
- 항목: 언어, 직무, 경력, 음식
- 항목을 가지고 몇점이상 맞은 사람이 몇명인지 구하는 것.

풀이
- 항목들을 그대로 집어넣음 문자열로
- <항목문자열, 점수들>
- 점수들을 이분탐색.
*/