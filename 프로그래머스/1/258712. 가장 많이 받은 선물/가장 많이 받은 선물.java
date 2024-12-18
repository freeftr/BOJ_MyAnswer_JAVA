import java.io.*;
import java.util.*;

// 두 사람이 선물을 주고 받은 기록이 있는 경우
// 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받음

// 두 사람이 선물을 주고 받은 기록이 하나도 없거나 같은 경우
// 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 하나 받음
// 선물 지수 = 이번 달 자신이 친구들에게 준 선물의 수 - 받은 선물
// 선물 지수도 같다면 다음 달에 주고받지 않는다.

// 선물을 가장 많이 받을 친구가 받을 선물의 수
class Solution {
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int length = friends.length;
        int[] result = new int[length];
        
        // 친구 목록 캐싱
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < length; i++) {
            map.put(friends[i], i);
        }
        
        //서로 주고 받은 선물의 개수 표현
        int[][] info = new int[length][length];
        //자기가 준 개수, 자기가 받은 개수 기록
        int[][] jisoo = new int[length][2];
        
        for (String gift : gifts) {
            String[] temp = gift.split(" ");
            String a = temp[0];
            String b = temp[1];
            
            info[map.get(a)][map.get(b)]++;
            jisoo[map.get(a)][0]++;
            jisoo[map.get(b)][1]++;
        }
        
        for (int i = 0; i < length; i++){
            for (int j = 0; j < length; j++){
                
                if (i==j) continue;
                
                // 같거나 주고 받은 경우가 없는 경우
                if (info[i][j]==info[j][i] || info[i][j]==0 && info[j][i]==0) {
                    if ((jisoo[i][0]-jisoo[i][1]) > (jisoo[j][0]-jisoo[j][1])) {
                        result[i]++;
                    } else if ((jisoo[i][0]-jisoo[i][1]) == (jisoo[j][0]-jisoo[j][1])) {
                        continue;
                    } else{
                        result[j]++;
                    }
                }
                
                //주고 받은 경우가 있는 경우
                else {
                    if (info[i][j] > info[j][i]) {
                        result[i]++;
                    }
                    else{
                        result[j]++;
                    }
                }
            }
        }
        
        for (int i = 0; i < length; i++) {
            answer = Math.max(answer, result[i]);
        }
        
        return answer/2;
    }
}