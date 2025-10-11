import java.util.*;
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int N = cards.length;
        ArrayList<Integer> hold = new ArrayList<>();
        ArrayList<Integer> hand = new ArrayList<>();
        int idx = 0;
        for (int i = 0; i < N / 3; i++) {
            hand.add(cards[i]);
        }
        
        idx = N / 3;
        int turn = 1;
        
        while (idx < N) {
            if (idx < N) hold.add(cards[idx++]);
            if (idx < N) hold.add(cards[idx++]);
            
            boolean free = false;
            for (int i = 0; i < hand.size(); i++) {
                int a = hand.get(i);
                int b = N + 1 - a;
                if (hand.contains(b)) {
                    free = true;
                    hand.remove(Integer.valueOf(a));
                    hand.remove(Integer.valueOf(b));
                    break;
                }
            }
            if (free) {
                turn++;
                continue;
            }
            
            boolean one = false;
            for (int i = 0; i < hand.size(); i++) {
                int a = hand.get(i);
                int b = N + 1 - a;
                if (coin >= 1 && hold.contains(b)) {
                    one = true;
                    hand.remove(Integer.valueOf(a));
                    hold.remove(Integer.valueOf(b));
                    break;
                }
            }
            if (one) {
                coin--;
                turn++;
                continue;
            }
            
            boolean two = false;
            for (int i = 0; i < hold.size(); i++) {
                int a = hold.get(i);
                int b = N + 1 - a;
                if (coin >= 2 && hold.contains(b)) {
                    two = true;
                    hold.remove(Integer.valueOf(a));
                    hold.remove(Integer.valueOf(b));
                    break;
                }
            }
            if (two) {
                coin -= 2;
                turn++;
                continue;
            }
            
            break;
        }
        
        return turn;
    }
}
