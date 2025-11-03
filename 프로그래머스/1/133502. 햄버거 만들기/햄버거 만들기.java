class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        
        int[] stack = new int[ingredient.length];
        int top = 0;
        
        for (int a : ingredient) {
            stack[top++] = a;
            
            if (top >= 4) {
                if (stack[top - 1] == 1 && stack[top - 2] == 3 && stack[top - 3] == 2 && stack[top - 4] == 1) {
                    answer++;
                    top -= 4;
                }
            }
        }
        
        return answer;
    }
}
/*
조건
- 햄버거 포장함.
- 아래서부터 위로 쌓음.
- 정해진 순서로 쌓인거만 포장함.
- 빵 -> 야채 -> 고기 -> 빵

요구
- 햄버거 개수 구하기.

풀이
- 스택
*/