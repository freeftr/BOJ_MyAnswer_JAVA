import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[] x = new int[10];
        int[] y = new int[10];
        
        for (char c : X.toCharArray()) {
            x[c - '0']++;
        }

        for (char c : Y.toCharArray()) {
            y[c - '0']++;
        }

        int[] same = new int[10];
        boolean noSame = false;
        for (int i = 0; i < 10; i++) {
            same[i] = Math.min(x[i], y[i]);
            if (same[i] != 0) noSame = true;
        }
        
        if (!noSame) return "-1";
        
        StringBuilder sb = new StringBuilder();
        boolean zero = false;
        for (int i = 9; i >= 0; i--) {
            if (i == 0 && sb.length() == 0) {
                zero = true;
            }
            
            for (int j = 0; j < same[i]; j++) {
                sb.append(i);
            }
        }
        
        if (zero) return "0";
        
        return sb.toString();
    }
}

/*
조건
- 짝꿍: 두 정수 중 임의의 자리에서 공통으로 나타나는 정수로 만들 수 있는 가장 큰 수.
- 없으면 -1.
- 0만 있으면 0.

요구
- 짝꿍 구하기.
*/