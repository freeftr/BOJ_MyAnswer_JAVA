class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Long.toString(n, k); // n이 클 수 있으므로 long 사용
        String[] ps = s.split("0+");

        for (String p : ps) {
            if (p.trim().isEmpty()) continue;

            long a = Long.parseLong(p); // int → long로 변경
            if (checkPrime(a)) {
                answer++;
            }
        }
        return answer;
    }

    static boolean checkPrime(long a) {
        if (a < 2) return false;
        if (a == 2) return true;
        if (a % 2 == 0) return false;

        for (long i = 3; i <= Math.sqrt(a); i += 2) {
            if (a % i == 0) return false;
        }
        return true;
    }
}
