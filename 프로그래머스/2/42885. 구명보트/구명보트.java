import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int right = people.length - 1;
        int left = 0;
        
        while (left <= right) {
            int sum = people[right] + people[left];
            // System.out.println(left + " " + right);
            
            if (sum > limit) {
                answer++;
                right--;
            } else if (sum == limit) {
                answer++;
                left++;
                right--;
            } else {
                answer++;
                right--;
                left++;
            }
        }
        
        return answer;
    }
}

/*
일단 people을 정렬
무거운 사람잡고 가벼운 사람부터 탐색해서 제거

50 50 70 80
50 80   1
50 70   1
50 50   1
50 70 80
50 80   1
50 70   1

*/