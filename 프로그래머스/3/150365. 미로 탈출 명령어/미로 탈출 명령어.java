import java.util.*;

class Solution {
    
    static HashMap<String, int[]> dir = new HashMap<>();
    static String[] order = {"d", "l", "r", "u"};
    
    static String answer = "impossible";
    static boolean found = false;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        dir.put("l", new int[]{0, -1});
        dir.put("r", new int[]{0, 1});
        dir.put("u", new int[]{-1, 0});
        dir.put("d", new int[]{1, 0});
        
        findPath(0, new StringBuilder(), n, m, x, y, r, c, k);
        return answer;
    }
    
    static void findPath(int depth, StringBuilder cur,
                         int n, int m, int x, int y, int r, int c, int k) {

        if (found) return;
        if (depth > k) return;
        
        int dist = Math.abs(r - x) + Math.abs(c - y);
        if (dist > (k - depth)) return;
        if (dist % 2 != (k - depth) % 2) return;
        
        if (depth == k) {
            if (x == r && y == c) {
                answer = cur.toString();
                found = true;
            }
            return;
        }
        
        for (String key : order) {
            int[] d = dir.get(key);
            int nx = x + d[0];
            int ny = y + d[1];
            
            if (nx <= 0 || ny <= 0 || nx > n || ny > m) continue;
            
            cur.append(key);
            findPath(depth + 1, cur, n, m, nx, ny, r, c, k);
            cur.deleteCharAt(cur.length() - 1);
            
            if (found) return;
        }
    }
}

/*
조건
- n x m 미로
- (x, y) -> (r, c) 탈출
- 총 이동거리가 k여야 한다.
- 재방문 가능.
- 경로 문자열이 사전순으로 가장 빠르게.

요구
- 탈출 경로 출력
- 불가능 경우 시 impossible

풀이
1. depth k까지 재귀로 탐색.
2. 결과 pq에 넣고 뽑기
*/