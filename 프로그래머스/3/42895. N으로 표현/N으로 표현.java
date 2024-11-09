import java.util.*;

class Solution {
    public int solution(int N, int number) {
        ArrayList<Set<Integer>> dp = new ArrayList<>();
        
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }
        if(N==number){
            return 1;
        }
        
        dp.get(1).add(N);
        
        for (int i = 2; i <= 8; i++) {
            String n = Integer.toString(N).repeat(i);
            dp.get(i).add(Integer.parseInt(n));
            
            for (int j = 1; j < i; j++) {
                for (int a : dp.get(j)) {
                    for (int b : dp.get(i - j)) {
                        dp.get(i).add(a + b);
                        dp.get(i).add(a - b);
                        dp.get(i).add(a * b);
                        if (b != 0) dp.get(i).add(a / b);
                    }
                }
            }
            
            if (dp.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
}
