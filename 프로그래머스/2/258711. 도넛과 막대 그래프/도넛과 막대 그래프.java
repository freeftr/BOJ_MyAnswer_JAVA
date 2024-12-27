import java.io.*;
import java.util.*;

class Solution {
    
    // 생성된 정점 = 나가는 간선 존재(2개이상), 들어오는 간선 없음
    // 막대 = 나가는 간선 없음, 들어오는 간선 1개
    // 8자 = 나가는 간선2개, 들어오는 간선 2개
    static int sv, stick = 0, eight = 0;
    static ArrayList<Integer> indeg = new ArrayList<>();
    static ArrayList<Integer> outdeg = new ArrayList<>();
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];  
        
        for (int[] edge : edges) {
            int max = Math.max(edge[0], edge[1]);
            if (indeg.size() <= max) {
                while (indeg.size() <= max) {
                    indeg.add(0);
                    outdeg.add(0);
                }
            }
        }
        
        // 간선 정보 처리
        for (int[] edge : edges) {
            indeg.set(edge[1], indeg.get(edge[1]) + 1);
            outdeg.set(edge[0], outdeg.get(edge[0]) + 1);
        }
        
        for (int i = 0; i < indeg.size(); i++) {
            // 생성된 정점
            if (indeg.get(i) == 0 && outdeg.get(i) >= 2) {
                sv = i;
            }
            // 막대 모양
            if (indeg.get(i) >= 1 && outdeg.get(i) == 0) {
                stick++;
            }
            // 8자 모양
            if (indeg.get(i) >= 2 && outdeg.get(i) >= 2) {
                eight++;
            }
        }
        
        answer[0] = sv;
        answer[1] = outdeg.get(sv) - stick - eight;
        answer[2] = stick;
        answer[3] = eight;
        
        return answer;
    }
}
