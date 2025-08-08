import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        int last = Integer.MIN_VALUE;

        for (int[] r : routes) {
            if (r[0] > last) {
                answer++;
                last = r[1];
            }
        }
        return answer;
    }
}
