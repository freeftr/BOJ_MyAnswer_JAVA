class Solution {
    static int result;
    static boolean[] visited = new boolean[8];
    static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    
    public int solution(int n, String[] data) {
        int answer = 0;
        result = 0;
        
        dfs(0, new StringBuilder(), data);
        
        answer = result;
        
        return answer;
    }
    
    static void dfs(int depth, StringBuilder cur, String[] data) {
        if (depth == 8) {
            if (check(cur.toString(), data)) result++;
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            cur.append(friends[i]);
            dfs(depth + 1, cur, data);
            cur.deleteCharAt(cur.length() - 1);
            visited[i] = false;
        }
    }
    
    static boolean check(String s, String[] data) {
        for (String d : data) {
            char a = d.charAt(0);
            char b = d.charAt(2);
            char type = d.charAt(3);
            int limit = Integer.parseInt(d.substring(4));
            
            int aIdx = s.indexOf(a);
            int bIdx = s.indexOf(b);
            int diff = Math.abs(aIdx - bIdx) - 1;
            
            if (type == '=' && limit != diff) return false;
            if (type == '<' && limit <= diff) return false;
            if (type == '>' && limit >= diff) return false;
        }
        
        return true;
    }
}