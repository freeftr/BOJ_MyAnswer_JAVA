import java.util.*;

class Solution {
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        
        for (int a = 1; a <= n - 4; a++) {
            for (int b = a + 1; b <= n - 3; b++) {
                for (int c = b + 1; c <= n - 2; c++) {
                    for (int d = c + 1; d <= n - 1; d++) {
                        for (int e = d + 1; e <= n; e++) {
                            HashSet<Integer> set = new HashSet<>();
                            set.add(a);
                            set.add(b);
                            set.add(c);
                            set.add(d);
                            set.add(e);
                            
                            boolean flag = false;
                            int idx = 0;
                            for (int[] Q : q) {
                                int cnt = 0;
                                
                                for (int i : Q) {
                                    if (set.contains(i)) cnt++;
                                }
                                
                                if (ans[idx] != cnt) {
                                    flag = true;
                                    break;
                                }
                                idx++;
                            }
                            
                            if (!flag) {
                                answer++;
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
}

/*
조건
- 서로 다른 정수 5개 오름차순
- m번의 시도 -> 몇개가 비밀코드에 있는지 알려줌

요구
- 비밀코드로 가능한 조합 개수 구하기
*/