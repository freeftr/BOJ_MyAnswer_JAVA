import java.util.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            String a = Integer.toBinaryString(arr1[i]);
            String b = Integer.toBinaryString(arr2[i]);
            
            a = pad(a, n);
            b = pad(b, n);
            
            String temp = "";
            
            for (int j = 0; j < n; j++) {
                if (a.charAt(j) == '0' && b.charAt(j) == '0') {
                    temp += " ";
                } else {
                    temp += "#";
                }
            }
            
            answer[i] = temp;
        }
        return answer;
    }
    
    static String pad(String s, int n) {
        
        while (s.length() < n) {
            s = "0" + s;
        }
        
        return s;
    }
}

/*
조건
- 지도는 한 변의 길이가 n인 정사각형
- 두 지도를 겹쳐 모두 공백인 부분만 공백 나머지는 벽
- 이진수로 부호화되어 있는 형태

요구
- 지도 해석
*/