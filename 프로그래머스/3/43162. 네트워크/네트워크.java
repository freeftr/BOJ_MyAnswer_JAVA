import java.util.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (i == j) continue;
                if (computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for (int p : parent) {
            set.add(find(p));
        }
        
        return set.size();
    }
    
    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        parent[b] = a;
    }
}