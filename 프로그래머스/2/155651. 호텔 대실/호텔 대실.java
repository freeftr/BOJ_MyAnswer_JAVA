import java.io.*;
import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        ArrayList<int[]> bookList = new ArrayList<>();
        
        for (String[] book : book_time) {
            int start = Integer.parseInt(book[0].split(":")[0]) * 60 + Integer.parseInt(book[0].split(":")[1]);
            int end = Integer.parseInt(book[1].split(":")[0]) * 60 + Integer.parseInt(book[1].split(":")[1]) + 10;
            bookList.add(new int[]{start, end});
        }
        
        Collections.sort(bookList, (a, b) -> a[0] - b[0]);
        
        for (int i = 0; i < bookList.size(); i++) {
            if (!pq.isEmpty() && pq.peek() <= bookList.get(i)[0]) {
                pq.poll();
            }
            pq.add(bookList.get(i)[1]);
        }
        return pq.size();
    }
}