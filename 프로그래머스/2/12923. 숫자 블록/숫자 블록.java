class Solution {
    public int[] solution(long begin, long end) {
        int size = (int)(end - begin);
        int[] answer = new int[size + 1];

        for (int i = 0; i <= size; i++) {
            answer[i] = (int) getMaxBlock(begin + i);
        }

        return answer;
    }

    private long getMaxBlock(long num) {
        if (num == 1) return 0;

        long maxDivisor = 1;
        long sqrt = (long) Math.sqrt(num);

        for (long j = 2; j <= sqrt; j++) {
            if (num % j != 0) continue;

            long pair = num / j;

            if (pair <= 10000000) {
                return pair;  // 조건에 맞는 큰 약수
            }

            if (j <= 10000000 && j > maxDivisor) {
                maxDivisor = j;  // 조건을 만족하는 작은 약수 후보 저장
            }
        }

        return maxDivisor;
    }
}
