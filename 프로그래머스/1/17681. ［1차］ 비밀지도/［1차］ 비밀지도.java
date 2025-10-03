class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            String a = Integer.toBinaryString(arr1[i]);
            String b = Integer.toBinaryString(arr2[i]);
            
            while (a.length() < n) {
                a = "0" + a;
            }
            
            while (b.length() < n) {
                b = "0" + b;
            }
            
            String temp = "";
            
            for (int j = 0; j < n; j++) {
                if (a.charAt(j) == '1' || b.charAt(j) == '1') {
                    temp += "#";
                } else {
                    temp += " ";
                }
            }
            
            answer[i] = temp;
        }
        return answer;
    }
}