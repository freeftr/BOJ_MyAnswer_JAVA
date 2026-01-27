import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Stack;

public class Main {
    static String S;
    static HashSet<String> result = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();

        ArrayList<int[]> pairs = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char cur = S.charAt(i);

            if (cur == '(') {
                stack.push(i);
            }

            if (cur == ')') {
                int b = stack.pop();
                pairs.add(new int[]{i, b});
            }
        }

        dfs(0, pairs, new ArrayList<>());

        ArrayList<String> answer = new ArrayList<>(result);
        Collections.sort(answer);
        for (String s : answer) {
            if (S.equals(s)) continue;
            System.out.println(s);
        }
    }

    static void dfs(int depth, ArrayList<int[]> pairs, ArrayList<Integer> chosen) {
        if (depth == pairs.size()) {
            result.add(makeStr(chosen, pairs));
            return;
        }

        dfs(depth + 1, pairs, chosen);
        chosen.add(depth);
        dfs(depth + 1, pairs, chosen);
        chosen.remove(chosen.size() - 1);
    }

    static String makeStr(ArrayList<Integer> chosen, ArrayList<int[]> pairs) {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[S.length()];

        for (int c : chosen) {
            int[] pair = pairs.get(c);
            visited[pair[0]] = true;
            visited[pair[1]] = true;
        }

        for (int i = 0; i < S.length(); i++) {
            if (!visited[i]) sb.append(S.charAt(i));
        }

        return sb.toString();
    }
}

/*
조건
- 괄호를 제거해서 나올 수 있는 서로 다른 식의 개수 계산
- 괄호는 항상 싸응로 제거해야 함.

요구
- 서로 다른 식의 개수 구하기

풀이
- 일단 set으로 답 개수 세기
- 일단 괄호 개수 세고, 나중에 넣을 수 있는 부분 구하는게 맞을듯?
 */