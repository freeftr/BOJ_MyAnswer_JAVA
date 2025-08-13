import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        int a = arrayA[0];
        int b = arrayB[0];
        
        for (int i = 0; i < arrayA.length; i++) {
            a = gcd(a, arrayA[i]);
        }
        
        for (int i = 0; i < arrayB.length; i++) {
            b = gcd(b, arrayB[i]);
        }
        
        for (int i : arrayB) {
            if (i % a == 0) {
                a = 0;
                break;
            }
        }
        
        for (int i : arrayA) {
            if (i % b == 0) {
                b = 0;
                break;
            }
        }
        return a > b ? a : b;
    }
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}