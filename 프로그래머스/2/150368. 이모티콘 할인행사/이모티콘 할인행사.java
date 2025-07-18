class Solution {
    static int maxPlus = 0;
    static int maxSell = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] discounts = new int[emoticons.length];
        dfs(0, discounts, users, emoticons);
        return new int[]{maxPlus, maxSell};
    }

    static void dfs(int depth, int[] discounts, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            int plus = 0;
            int sellAmount = 0;

            for (int[] user : users) {
                int discountThreshold = user[0]; 
                int priceLimit = user[1];        
                int sum = 0;

                for (int i = 0; i < emoticons.length; i++) {
                    if (discounts[i] >= discountThreshold) {
                        sum += emoticons[i] * (100 - discounts[i]) / 100;
                    }
                }

                if (sum >= priceLimit) {
                    plus++;
                } else {
                    sellAmount += sum;
                }
            }

            if (plus > maxPlus) {
                maxPlus = plus;
                maxSell = sellAmount;
            } else if (plus == maxPlus) {
                maxSell = Math.max(maxSell, sellAmount);
            }

            return;
        }

        for (int rate : new int[]{10, 20, 30, 40}) {
            discounts[depth] = rate;
            dfs(depth + 1, discounts, users, emoticons);
        }
    }
}
