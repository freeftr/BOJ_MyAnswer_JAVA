import java.util.*;

class Solution {
    
    static int result = 0;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }
        
        visited = new boolean[info.length+1];
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        HashSet<Integer> state = new HashSet<>();
        
        dfs(0, 1, 0, info, state);
        
        return result;
    }
    
    static void dfs(int now, int sheep, int wolf, int[] info, HashSet<Integer> state) {
        if (sheep <= wolf) return;
        
        result = Math.max(result, sheep);
        
        HashSet<Integer> newState = new HashSet<>(state);

        for (int nv : graph.get(now)) {
            newState.add(nv);
        }

        for (int next : newState) {
            if (visited[next]) continue;
            visited[next] = true;
            if (info[next] == 1) {
                dfs(next, sheep, wolf + 1, info, newState);
            } else {
                dfs(next, sheep + 1, wolf, info, newState);
            }
            visited[next] = false;
        }
    }
}
