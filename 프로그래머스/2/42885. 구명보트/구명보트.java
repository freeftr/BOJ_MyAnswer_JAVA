import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        boolean[] visited = new boolean[people.length];
        int prev = 0;
        
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                right--;
                left++;
                answer++;
            } else {
                right--;
                answer++;
            }
        }
        
        return answer;
    }
}