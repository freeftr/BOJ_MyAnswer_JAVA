class Solution {
    
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        
        visited = new boolean[words.length];
        
        dfs(begin, words, target, 0);
        
        if (answer == Integer.MAX_VALUE){
            answer = 0;
        }
        
        return answer;
    }
    
    static void dfs(String cur, String[] words, String target, int depth) {
        
        if (cur.equals(target)) {
            answer = Math.min(answer, depth);
        }
        
        for (int i = 0; i < words.length; i++) {
            if (visited[i]) continue;
            
            int cnt = 0;
            for (int j = 0; j < words[i].length(); j++) {
                if (cur.charAt(j) != words[i].charAt(j)) {
                    cnt++;
                    if (cnt > 2) break;
                }
            }
            
            if (cnt == 1) {
                visited[i] = true;
                dfs(words[i], words, target, depth + 1);
                visited[i] = false;
            }
        }
    }
}