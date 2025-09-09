import java.util.*;

class Solution {
    
    static HashMap<Long, Long> roomMap = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        answer = new long[room_number.length];
        
        int idx = 0;
        for (long num : room_number) {
            answer[idx++] = dfs(num);
        }
        return answer;
    }
    
    static long dfs(long roomNum) {
        if (!roomMap.containsKey(roomNum)) {
            roomMap.put(roomNum, roomNum + 1);
            return roomNum;
        }
        
        long next = dfs(roomMap.get(roomNum));
        roomMap.put(roomNum, next);
        return next;
    }
}

/*
- 신청한 순서대로 방 배정
- 원하는 방 비었을 시 바로 배정
- 이미 있을 시 원하는 방보다 번호가 크면서 비어있는 것중 가장 작은 것 배정
- 방 개수 1 <= k <= 12

- 이미 검색했던 친구면 마지막 번호 저장해서 캐싱 -> 그 다음부터 검색
*/