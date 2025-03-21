import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // 오름차순 정렬
        // 겹치는 구간 갱신
        // 안겹치면 answer++
        // 반복
        
         Arrays.sort(targets, (o1,o2) -> {
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        int common = 0;
        
        for (int i = 0; i < targets.length; i++){
            if (common <= targets[i][0]){
                answer++;
            } else {
                continue;
            }
            common = targets[i][1];
            System.out.println(common);
        }
        
        return answer;
    }
}