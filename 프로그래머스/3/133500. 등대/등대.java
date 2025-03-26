import java.io.*;
import java.util.*;

class Solution {
    
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int N, ans = 0;
    static boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        N = n;
        visited = new boolean[N+1];
        
        for (int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        
        for (int[] light : lighthouse){
            graph.get(light[0]).add(light[1]);
            graph.get(light[1]).add(light[0]);
        }
        
        dfs(1,1);
        
        answer = ans;
        
        return answer;
    }
    
    static void dfs(int v, int prev){
        
        for (int nv : graph.get(v)){
            if (nv==prev){
                continue;
            }
            
            dfs(nv, v);
            
            if (!visited[nv] && !visited[v]){
                ans++;
                visited[v] = true;
            }
        }
    }
}