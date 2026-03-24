import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (String[] time : book_time) {
            int start = strToInt(time[0]);
            int end = strToInt(time[1]) + 10;
            
            while (!pq.isEmpty() && pq.peek() <= start) {
                pq.poll();
            }
            
            pq.add(end);
            // System.out.println(time[0] + " " + pq.size());
            
            answer = Math.max(answer, pq.size());
        }
        
        return answer;
    }
    
    static int strToInt(String s) {
        String[] temp = s.split(":");
        int h = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        
        return h * 60 + m;
    }
}

/*
조건
- 최소한의 객실 사용
- 10분간 청소 이용

요구
- 필요한 최소한의 객실 수 

풀이
- 필요하면 늘리는 방식
- 현재거를 봤을 때 가장 빨리 끝나는 방을 찾고 없으면 추가
*/