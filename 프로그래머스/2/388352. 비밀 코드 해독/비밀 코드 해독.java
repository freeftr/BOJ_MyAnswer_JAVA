import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        
        //11 12 13 14 15
        for (int a = 1; a <= n - 4; a++){
            for (int b = a + 1; b <= n - 3; b++){
                for (int c = b + 1; c <= n - 2; c++){
                    for (int d = c + 1; d <= n - 1; d++){
                        for (int e = d + 1; e <= n; e++){
                            
                            HashSet<Integer> set = new HashSet<>();
                            set.add(a);
                            set.add(b);
                            set.add(c);
                            set.add(d);
                            set.add(e);
                            
                            boolean flag = true;
                            
                            int idx = 0;
                            for (int[] candidate : q){
                                int cnt = 0;
                                
                                for (int i = 0; i < 5; i++){
                                    if (set.contains(candidate[i])){
                                        cnt++;
                                    }
                                }
                                
                                if (cnt!=ans[idx]){
                                    flag = false;
                                    break;
                                }
                                idx++;
                            }
                            
                            if (flag){
                                    answer++;
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}