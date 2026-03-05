import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        ArrayList<String> arr1 = new ArrayList<>();
        ArrayList<String> arr2 = new ArrayList<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            char a = str1.charAt(i);
            char b = str1.charAt(i + 1);
            
            if (Character.isLetter(a) && Character.isLetter(b)) {
                arr1.add("" + a + b);
            }
        }
        
        for (int i = 0; i < str2.length() - 1; i++) {
            char a = str2.charAt(i);
            char b = str2.charAt(i + 1);
            
            if (Character.isLetter(a) && Character.isLetter(b)) {
                arr2.add("" + a + b);
            }
        }
        
        boolean[] v1 = new boolean[arr1.size()];
        boolean[] v2 = new boolean[arr2.size()];
        
        int cnt = 0;
        
        for (int i = 0; i < arr1.size(); i++) {
            if (v1[i]) continue;
            
            for (int j = 0; j < arr2.size(); j++) {
                if (v2[j]) continue;
                
                if (arr1.get(i).equals(arr2.get(j))) {
                    v1[i] = true;
                    v2[j] = true;
                    cnt++;
                    break;
                }
            }
        }
        
        int total = arr1.size() + arr2.size();
        int hap = total - cnt;
        
        if (hap == 0) return 65536;
        
        answer = (cnt * 65536) / hap;
        
        return answer;
    }
}