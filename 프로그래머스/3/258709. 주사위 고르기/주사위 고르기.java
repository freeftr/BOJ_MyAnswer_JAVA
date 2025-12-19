import java.util.*;

class Solution {

    static int N;
    static int[][] diceInfo;
    static int cnt = 0;
    static int max = 0;
    static ArrayList<Integer> result = new ArrayList<>();

    public int[] solution(int[][] dice) {
        int[] answer = {};

        N = dice.length;
        diceInfo = new int[N][6];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 6; j++) {
                diceInfo[i][j] = dice[i][j];
            }
        }

        combinations(0, 0, new ArrayList<Integer>());

        Collections.sort(result);
        answer = new int[N / 2];

        for (int i = 0; i < N / 2; i++) {
            answer[i] = result.get(i) + 1;
        }

        return answer;
    }

    static void combinations(int depth, int start, ArrayList<Integer> selected) {
        if (depth == N / 2) {
            calculate(selected);
            if (max < cnt) {
                max = cnt;
                result = new ArrayList<>();
                result.addAll(selected);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            selected.add(i);
            combinations(depth + 1, i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    static void calculate(ArrayList<Integer> A) {
        ArrayList<Integer> B = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (!A.contains(i)) B.add(i);
        }

        ArrayList<Integer> sumAList = new ArrayList<>();
        ArrayList<Integer> sumBList = new ArrayList<>();

        getSumA(0, 0, A, sumAList);
        getSumB(0, 0, B, sumBList);

        Collections.sort(sumBList);

        cnt = 0;
        for (int a : sumAList) {
            cnt += lowerBound(sumBList, a);
        }
    }

    static void getSumA(int depth, int sum, ArrayList<Integer> A, ArrayList<Integer> list) {
        if (depth == N / 2) {
            list.add(sum);
            return;
        }

        int dice = A.get(depth);
        for (int i = 0; i < 6; i++) {
            getSumA(depth + 1, sum + diceInfo[dice][i], A, list);
        }
    }

    static void getSumB(int depth, int sum, ArrayList<Integer> B, ArrayList<Integer> list) {
        if (depth == N / 2) {
            list.add(sum);
            return;
        }

        int dice = B.get(depth);
        for (int i = 0; i < 6; i++) {
            getSumB(depth + 1, sum + diceInfo[dice][i], B, list);
        }
    }

    static int lowerBound(ArrayList<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int m = (l + r) / 2;
            if (list.get(m) < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
