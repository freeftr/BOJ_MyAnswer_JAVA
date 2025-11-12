import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    static ArrayList<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            result = new ArrayList<>();

            int[] arr = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                arr[i] = i;
            }

            dfs(new StringBuilder().append(arr[1]), N, arr, 2);

            Collections.sort(result);

            for (String s : result) {
                sb.append(s + "\n");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dfs(StringBuilder cur, int N, int[] arr, int arrIdx) {
        if (arrIdx == N + 1) {
            if (check(cur)) {
                result.add(cur.toString());
            }

            return;
        }
        
        int nextIdx = arrIdx + 1;
        
        cur.append('-').append(arr[arrIdx]);
        dfs(cur, N, arr, nextIdx);
        cur.deleteCharAt(cur.length() - 1);
        cur.deleteCharAt(cur.length() - 1);

        cur.append('+').append(arr[arrIdx]);
        dfs(cur, N, arr, nextIdx);
        cur.deleteCharAt(cur.length() - 1);
        cur.deleteCharAt(cur.length() - 1);

        cur.append(' ').append(arr[arrIdx]);
        dfs(cur, N, arr, nextIdx);
        cur.deleteCharAt(cur.length() - 1);
        cur.deleteCharAt(cur.length() - 1);
    }

    static boolean check(StringBuilder s) {
        int sum = 0;

        StringBuilder temp = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);

            if (cur == ' ') continue;

            if (Character.isDigit(cur)) {
                temp.append(cur);
            } else if (cur == '+') {
                list.add(temp.toString());
                list.add("+");

                temp = new StringBuilder();
            } else {
                list.add(temp.toString());
                list.add("-");

                temp = new StringBuilder();
            }
        }

        list.add(temp.toString());

        sum = Integer.parseInt(list.get(0));

        for (int i = 1; i < list.size(); i++) {
            String cur = list.get(i);

            if (cur.equals("+")) {
                sum += Integer.parseInt(list.get(i + 1));
                i++;
            }

            if (cur.equals("-")) {
                sum -= Integer.parseInt(list.get(i + 1));
                i++;
            }
        }

        return sum == 0;
    }
}

/*
조건
- 1 ~ N까지의 오름차순 수열.
- '+', '-'를 넣어서 수식을 만들어 0이 될 수 있는지 확인.

요구
- 0이되는 모든 수식 찾기.

풀이
- dfs 분기로 하면 될듯?
 */
