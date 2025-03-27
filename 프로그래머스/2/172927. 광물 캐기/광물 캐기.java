import java.util.*;

class Solution {
    
    static int diamond, iron, stone, N, result;
    
    public int solution(int[] picks, String[] minerals) {
        diamond = picks[0];
        iron = picks[1];
        stone = picks[2];
        N = minerals.length;
        result = Integer.MAX_VALUE;

        if (diamond > 0) {
            dfs(0, 0, 5, "diamond", diamond - 1, iron, stone, minerals);
        }
        if (iron > 0) {
            dfs(0, 0, 5, "iron", diamond, iron - 1, stone, minerals);
        }
        if (stone > 0) {
            dfs(0, 0, 5, "stone", diamond, iron, stone - 1, minerals);
        }

        return result;
    }
    static void dfs(int cost, int depth, int hp, String curPick, int di, int ir, int st, String[] minerals) {
        if (depth == N) {
            result = Math.min(result, cost);
            return;
        }
        if (di<=0 && ir<=0 && st<=0 && hp==0) {
            result = Math.min(result, cost);
            return;
        }
        
        // System.out.println("di: " + di + " st: " + st + " ir: " + ir + " cost: " + cost);
        
        if (hp == 0) {
            if (di > 0) {
                dfs(cost, depth, 5, "diamond", di - 1, ir, st, minerals);
            }
            if (ir > 0) {
                dfs(cost, depth, 5, "iron", di, ir - 1, st, minerals);
            }
            if (st > 0) {
                dfs(cost, depth, 5, "stone", di, ir, st - 1, minerals);
            }
            return;
        }

        int addCost = 1;
        if (curPick.equals("iron") && minerals[depth].equals("diamond")) {
            addCost = 5;
        } else if (curPick.equals("stone")) {
            if (minerals[depth].equals("diamond")) addCost = 25;
            else if (minerals[depth].equals("iron")) addCost = 5;
        }

        dfs(cost + addCost, depth + 1, hp - 1, curPick, di, ir, st, minerals);
    }
}
