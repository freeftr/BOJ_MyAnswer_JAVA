class Solution {
    public int[] solution(int e, int[] starts) {
        int[] divCnt = new int[e + 1];
        
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                divCnt[j]++;
            }
        }
        
        int[] maxIdx = new int[e + 1];
        maxIdx[e] = e;
        for (int i = e - 1; i >= 1; i--) {
            if (divCnt[i] >= divCnt[maxIdx[i + 1]]) {
                maxIdx[i] = i;
            } else {
                maxIdx[i] = maxIdx[i + 1];
            }
        }
        
        int n = starts.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = maxIdx[starts[i]];
        }
        
        return answer;
    }
}
