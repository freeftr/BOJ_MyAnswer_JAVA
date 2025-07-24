import java.io.*;
import java.util.*;

class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, 0, target);
        return answer;
    }
    
    static void dfs(int depth, int[] numbers, int sum, int target) {
        if (depth == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        
        dfs(depth + 1, numbers, sum - numbers[depth], target);
        dfs(depth + 1, numbers, sum + numbers[depth], target);
    }
}