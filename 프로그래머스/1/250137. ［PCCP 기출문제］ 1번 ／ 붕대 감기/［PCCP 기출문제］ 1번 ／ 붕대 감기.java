class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];

        int curHealth = health;
        int curTime = 0;
        int continueTime = 0;

        for (int[] attack : attacks) {
            int attackTime = attack[0];
            int damage = attack[1];

            int diff = attackTime - curTime - 1;

            if (diff > 0) {
                int recovery = diff * x;
                continueTime += diff;
                recovery += (continueTime / t) * y;
                curHealth = Math.min(health, curHealth + recovery);
            }

            curHealth -= damage;
            if (curHealth <= 0) return -1;

            continueTime = 0;
            curTime = attackTime;
        }

        return curHealth;
    }
}
