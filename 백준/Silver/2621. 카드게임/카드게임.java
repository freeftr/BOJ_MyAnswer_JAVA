import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, Integer> colorCnt = new HashMap<>();
    static HashMap<Integer, Integer> numCnt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String[] cards = new String[5];
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());

            String color = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            colorCnt.merge(color, 1, Integer::sum);
            numCnt.merge(num, 1, Integer::sum);
            nums.add(num);
        }

        Collections.sort(nums);

        boolean continuous = checkContinuous(nums);
        ArrayList<int[]> sameNum = checkSameNum();
        ArrayList<Integer> sameColor = checkSameColor();

        Collections.sort(sameNum, (a, b) -> a[1] - b[1]);

        int score = 0;

        // 1번 규칙
        if (sameColor.size() == 1 && continuous) {
            score = nums.get(4) + 900;
        }

        // 2번 규칙
        if (sameNum.size() == 2 && sameNum.get(1)[1] == 4) {
            score = Math.max(score, sameNum.get(1)[0] + 800);
        }

        // 3번 규칙
        if (sameNum.size() == 2 && sameNum.get(0)[1] == 2) {
            score = Math.max(score, sameNum.get(1)[0] * 10 + sameNum.get(0)[0] + 700);
        }

        // 4번 규칙
        if (sameColor.size() == 1) {
            score = Math.max(score, nums.get(4) + 600);
        }

        // 5번 규칙
        if (continuous) {
            score = Math.max(score, nums.get(4) + 500);
        }

        // 6번 규칙
        if (sameNum.get(sameNum.size() - 1)[1] == 3) {
            score = Math.max(score, sameNum.get(sameNum.size() - 1)[0] + 400);
        }

        // 7번 규칙
        if (sameNum.size() == 3 && sameNum.get(1)[1] == sameNum.get(2)[1]) {
            int a = sameNum.get(1)[0];
            int b = sameNum.get(2)[0];
            int plus = a > b ? a * 10 + b : b * 10 + a;
            score = Math.max(score, plus + 300);
        }

        // 8번 규칙
        for (int[] num : sameNum) {
            if (num[1] == 2) {
                score = Math.max(score, num[0] + 200);
                break;
            }
        }

        score = Math.max(score, nums.get(4) + 100);

        System.out.println(score);
    }

    static ArrayList<int[]> checkSameNum() {
        ArrayList<int[]> result = new ArrayList<>();

        for (int key : numCnt.keySet()) {
            int cnt = numCnt.get(key);

            result.add(new int[]{key, cnt});
        }

        return result;
    }

    static boolean checkContinuous(ArrayList<Integer> nums) {
        int prev = nums.get(0);

        for (int i = 1; i < 5; i++) {
            if (nums.get(i) != prev + 1) return false;
            prev = nums.get(i);
        }

        return true;
    }

    static ArrayList<Integer> checkSameColor() {
        ArrayList<Integer> result = new ArrayList<>();

        for (String key : colorCnt.keySet()) {
            int cnt = colorCnt.get(key);

            result.add(cnt);
        }

        return result;
    }
}

/*
조건
- 빨, 파, 노, 녹, 1 ~ 9
- 5장이 같은 색이면서 연속적 -> 900 + 가장 높은 숫자
- 5장 중 4장의 숫자 같으면 숫자 -> 800 + 같은 숫자
- 3장 같고 2장 같으면 -> 3장 숫자 * 10 + 2장 + 700
 */