import java.util.*;

class Solution {
    static int result = 0;
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        N = info.length;
        
        visited = new boolean[N];
        
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        
        dfs(0, 0, 1, info, new HashSet<Integer>());
        
        return result;
    }
    
    static void dfs(int v, int wolf, int sheep, int[] info, HashSet<Integer> next) {
        if (wolf >= sheep) return;
        result = Math.max(result, sheep);
        
        HashSet<Integer> newSet = new HashSet<>(next);
        
        for (int nv : graph.get(v)) {
            newSet.add(nv);
        }
        
        for (int nv : newSet) {
            if (visited[nv]) continue;
            visited[nv] = true;
            if (info[nv] == 1) dfs(nv, wolf + 1, sheep, info, newSet);
            if (info[nv] == 0) dfs(nv, wolf, sheep + 1, info, newSet);
            visited[nv] = false;
        }
    }
}

/*
조건
양보다 늑대의 수가 같거나 많아지면 return;

요구
최대한 많은 양을 모으게.

풀이
dfs로 돌면서 다음에 갈 곳들을 미리 저장해놓고 돌기.
*/