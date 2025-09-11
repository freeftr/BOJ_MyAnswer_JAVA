class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] s1 = new String[n];
        String[] s2 = new String[n];
        
        for (int i = 0; i < n; i++) {
            s1[i] = Integer.toBinaryString(arr1[i]);
            s2[i] = Integer.toBinaryString(arr2[i]);
            int len1 = s1[i].length();
            int len2 = s2[i].length();
            if (s1[i].length() < n) {
                for (int j = 0; j < n - len1; j++) {
                    s1[i] = "0" + s1[i];
                }
            }
            if (s2[i].length() < n) {
                for (int j = 0; j < n - len2; j++) {
                    s2[i] = "0" + s2[i];
                }
            }
            String s = "";
            for (int j = 0; j < n; j++) {
                if (s1[i].charAt(j) == '1' || s2[i].charAt(j) == '1') {
                    s += "#";
                } else {
                    s += " ";
                }
            }
            
            answer[i] = s;
        }
        return answer;
    }
}

/*
n * n
" ", # = 벽

모두 공백만 공백
*/