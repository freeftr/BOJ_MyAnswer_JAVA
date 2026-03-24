import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        Stack<int[]> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            int cur = numbers[i];
            
            while (!stack.isEmpty() && stack.peek()[1] < cur) {
                int[] temp = stack.pop();
                int idx = temp[0];
                
                answer[idx] = cur;
            }
            
            stack.add(new int[]{i, cur});
        }
        return answer;
    }
}

/*
조건
- 뒷 큰수 : 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수

요구
- 모든 원소에 대한 뒷큰수 구하기

풀이
- 

*/