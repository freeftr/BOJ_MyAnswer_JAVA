import java.util.*;
class Solution {
    
    static HashMap<Long, Long> map = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        int idx = 0;
        for (long num : room_number) {
            answer[idx++] = dfs(num);
        }
        
        return answer;
    }
    
    static long dfs(long num) {
        if (!map.containsKey(num)) {
            map.put(num, num + 1);
            return num;
        }
        
        long next = dfs(map.get(num));
        map.put(num, next);
        return next;
    }
}

/*
원하는 방이 비어 있으면 즉시 배정.
있으면 번호 큰 방중 가장 작은 방 배정.

맵에다가 방번호, 다음방번호 이렇게 저장.
*/