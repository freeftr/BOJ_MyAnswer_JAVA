import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        int N = elements.length;
        int[] twice = new int[N * 2];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            twice[i] = elements[i];
            twice[i + N] = elements[i];
            set.add(elements[i]);
        }
        
        for (int i = 1; i < N; i++) {
             
            int left = 0;
            int right = left + i;
            int sum = 0;
            
            for (int j = left; j <= right; j++) {
                sum += twice[j];
            }
                
            while (left < N) {
                set.add(sum);
                sum -= twice[left++];
                sum += twice[++right];
            }
        }
        
        // for (int s : set) {
        //     System.out.println(s);
        // }
        
        return set.size();
    }
}