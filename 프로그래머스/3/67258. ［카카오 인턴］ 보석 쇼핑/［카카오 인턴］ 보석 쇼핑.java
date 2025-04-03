import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        HashMap<String, Integer> gemMap = new HashMap<>();
        HashSet<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int gemSize = gemSet.size();
        
        int left = 0, right = 0;
        int min = Integer.MAX_VALUE;
        int ansLeft = 0, ansRight = 0;
        
        gemMap.put(gems[0], 1);

        while (right < gems.length) {
            if (gemMap.size() == gemSize) { 
                if (right - left < min) {
                    min = right - left;
                    ansLeft = left;
                    ansRight = right;
                }
                
                gemMap.put(gems[left], gemMap.get(gems[left]) - 1);
                if (gemMap.get(gems[left]) == 0) {
                    gemMap.remove(gems[left]);
                }
                left++;
            } else { 
                right++;
                if (right < gems.length) {
                    gemMap.put(gems[right], gemMap.getOrDefault(gems[right], 0) + 1);
                }
            }
        }
        
        return new int[]{ansLeft + 1, ansRight + 1}; 
    }
}
