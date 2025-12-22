import java.util.*;

class Solution {
    
    static int n = 0;
    static int idx;
    static ArrayList<Integer> inhand = new ArrayList<>();
    static ArrayList<Integer> drawed = new ArrayList<>();
    
    public int solution(int coin, int[] cards) {
        int answer = 0;
        
        n = cards.length;
        
        for (int i = 0; i < n / 3; i++) {
            inhand.add(cards[i]);
        }
        
        idx = n / 3;
        
        while (true) {
            if (idx >= cards.length) break;
            
            draw(cards);
            
            if (zero()) {
                answer++;
                continue;
            }
            
            if (coin > 0 && one()) {
                answer++;
                coin--;
                continue;
            }
            
            if (coin > 1 && two()) {
                answer++;
                coin -= 2;
                continue;
            }
            
            break;
        }
        
        return answer + 1;
    }
    
    static void draw(int[] cards) {
        for (int i = 0; i < 2; i++) {
            if (idx >= cards.length) return;
            drawed.add(cards[idx++]);
        }
    }
    
    static boolean zero() {
        for (int i = 0; i < inhand.size() - 1; i++) {
            for (int j = i + 1; j < inhand.size(); j++) {
                int a = inhand.get(i);
                int b = inhand.get(j);
                
                if (a + b == n + 1) {
                    inhand.remove(inhand.indexOf(a));
                    inhand.remove(inhand.indexOf(b));
                    return true;
                }
            }
        }
        
        return false;
    }
    
    static boolean one() {
        for (int i = 0; i < inhand.size(); i++) {
            int a = inhand.get(i);
            
            if (drawed.contains(n + 1 - a)) {
                int b = n + 1 - a;
                
                drawed.remove(drawed.indexOf(b));
                return true;
            }
        }
        
        return false;
    }
    
    static boolean two() {
        for (int i = 0; i < drawed.size() - 1; i++) {
            for (int j = i + 1; j < drawed.size(); j++) {
                int a = drawed.get(i);
                int b = drawed.get(j);
                
                if (a + b == n + 1) {
                    drawed.remove(drawed.indexOf(a));
                    drawed.remove(drawed.indexOf(b));
                    return true;
                }
            }
        }
        
        return false;
    }
}