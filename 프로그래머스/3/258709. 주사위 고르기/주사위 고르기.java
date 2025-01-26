import java.util.*;

public class Solution {

    public static List<Integer> solution(int[][] dices) {
        Map<Integer, List<Integer>> dic = new HashMap<>();
        int L = dices.length;

        List<List<Integer>> combinations = combi(L, L / 2);

        for (List<Integer> aidx : combinations) {
            List<Integer> bidx = new ArrayList<>();
            for (int i = 0; i < L; i++) {
                if (!aidx.contains(i)) {
                    bidx.add(i);
                }
            }

            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();

            List<List<Integer>> orderProduct = generateProduct(L / 2, 6);
            for (List<Integer> order : orderProduct) {
                int sumA = 0, sumB = 0;
                for (int i = 0; i < aidx.size(); i++) {
                    sumA += dices[aidx.get(i)][order.get(i)];
                }
                for (int i = 0; i < bidx.size(); i++) {
                    sumB += dices[bidx.get(i)][order.get(i)];
                }
                A.add(sumA);
                B.add(sumB);
            }

            Collections.sort(B);

            int wins = 0;
            for (int num : A) {
                wins += bs(B, num);
            }

            dic.put(wins, aidx);
        }

        int maxKey = Collections.max(dic.keySet());
        List<Integer> result = new ArrayList<>();
        for (int index : dic.get(maxKey)) {
            result.add(index + 1); 
        }

        return result;
    }

    private static List<List<Integer>> combi(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combineHelper(result, new ArrayList<>(), 0, n, k);
        return result;
    }

    private static void combineHelper(List<List<Integer>> result, List<Integer> temp, int start, int n, int k) {
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < n; i++) {
            temp.add(i);
            combineHelper(result, temp, i + 1, n, k);
            temp.remove(temp.size() - 1);
        }
    }

    private static List<List<Integer>> generateProduct(int n, int range) {
        List<List<Integer>> result = new ArrayList<>();
        productHelper(result, new ArrayList<>(), n, range);
        return result;
    }

    private static void productHelper(List<List<Integer>> result, List<Integer> temp, int n, int range) {
        if (temp.size() == n) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < range; i++) {
            temp.add(i);
            productHelper(result, temp, n, range);
            temp.remove(temp.size() - 1);
        }
    }

    private static int bs(List<Integer> sortedList, int target) {
        int left = 0, right = sortedList.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (sortedList.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
