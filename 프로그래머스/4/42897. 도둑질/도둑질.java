class Solution {
    public int solution(int[] money) {
        int answer = 0;
        
        if (money.length == 1) {
            return money[0];
        }
        
        if (money.length == 2) {
            return money[1];
        }
        
        int[] dp1 = new int[money.length];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        
        // 첫번째 것을 선택한 경우.
        for (int i = 2; i < money.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 2] + money[i], dp1[i - 1]);
        }
        
        
        int[] dp2 = new int[money.length];
        dp2[1] = money[1];
        dp2[2] = Math.max(money[1], money[2]);
        
        // 두번째 것을 선택한 경우.
        for (int i = 3; i < money.length; i++) {
            dp2[i] = Math.max(dp2[i - 2] + money[i], dp2[i - 1]);
        }
        
        return Math.max(dp1[money.length - 2], dp2[money.length - 1]);
    }
}


/*
인접한 두 집을 털면 경보 울림.
훔칠 수 있는 최댓값 구하기.

- 첫번째 선택 하냐 안하냐?
*/