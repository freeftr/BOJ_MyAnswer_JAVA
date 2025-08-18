import java.io.*;
import java.util.*;

class Solution {
    
    static int r, c;
    static HashMap<Integer, ArrayList<String>> map = new HashMap<>();
    static ArrayList<HashSet<Integer>> results = new ArrayList<>();
    
    public int solution(String[][] relation) {
        
        r = relation.length;
        c = relation[0].length;
        
        dfs(0, relation, new ArrayList<>());
        
        return results.size();
    }
    
    static void dfs(int idx, String[][] relation, ArrayList<Integer> candidates) {
        
        if (check(candidates, relation)) {
            HashSet<Integer> temp = new HashSet<>(candidates);
            
            for (HashSet<Integer> result : results) {
                if (candidates.containsAll(result)) {
                    return;
                } 
            }
            results.removeIf(result -> result.containsAll(temp));

            results.add(temp);
            return;
        }
        
        if (idx >= c) return;
        
        for (int i = idx; i < c; i++) {
            candidates.add(i);
            dfs(i + 1, relation, candidates);
            candidates.remove(candidates.size() - 1);
        }
    }
    
    static boolean check(ArrayList<Integer> candidates, String[][] relations) {
        HashSet<String> set = new HashSet<>();
        
        for (int i = 0; i < r; i++) {
            String s = "";
            for (int col : candidates) {
                s += relations[i][col] + " ";
            }
            set.add(s);
        }
        
        return set.size() == r;
    }
}

// 유일성: 유일키
// 복합키 가능인데 이제 더 적은 속성으로 유일 보장가능하면 안댐