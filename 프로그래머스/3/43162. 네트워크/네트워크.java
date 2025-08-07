import java.io.*;
import java.util.*;

class Solution {
    
    static int[] parent;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        parent = new int[n+1];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1 && i != j ) {
                    union(i, j);
                }
            }
        }
                    
        HashSet<Integer> set = new HashSet<>();
        
        for (int i = 0; i < n; i++) {
            set.add(find(i));
        }
        
        answer = set.size();
        
        return answer;
    }
    
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        parent[a] = b;
    }
    
    static int find(int v) {
        if (v == parent[v]) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}