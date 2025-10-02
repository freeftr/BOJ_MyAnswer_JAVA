import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);
        int left = 0;
        int right = people.length - 1;
        while (left <= right) {
            int sum = people[left] + people[right];
            
            if (sum > limit) {
                answer++;
                right--;
            } else if (sum <= limit) {
                left++;
                right--;
                answer++;
            }
        }
        
        return answer;
    }
}

/*
조건
- 2명씩 밖에 구명보트 못탐.
- 무게제한 있음.
- 최대한 적은 구명보트로 구출

요구
- 모든 사람을 구출하기 위해 필요한 구명보트 개수 최솟값 구하기.
*/