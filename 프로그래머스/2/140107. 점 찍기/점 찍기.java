import java.io.*;
import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long x = 0;
        long dd = (long)d * d;

        while (x <= d) {
            long remain = dd - x * x;
            long y = (long)Math.sqrt(remain);
            answer += y / k + 1;
            x += k;
        }
        return answer;
    }
}