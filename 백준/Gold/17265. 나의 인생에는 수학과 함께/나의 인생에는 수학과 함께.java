import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static String[][] board;
    static int[] dx = {0, 1};
    static int[] dy = {1, 0};
    static boolean[][] visited;
    static ArrayList<Integer> result = new ArrayList<>();
    static HashSet<String> opers = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        board = new String[N][N];
        visited = new boolean[N][N];

        opers.add("+");
        opers.add("-");
        opers.add("*");

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board[i][j] = s[j];
            }
        }

        visited[0][0] = true;
        dfs(0, 0, new StringBuilder(board[0][0]));

        Collections.sort(result);

        System.out.println(result.get(result.size() - 1) + " " + result.get(0));
    }

    static void dfs(int x, int y, StringBuilder sb) {
        if (x == N - 1 && y == N - 1) {
            result.add(calculate(sb.toString()));
            return;
        }

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (checkRange(nx, ny)) continue;

            sb.append(board[nx][ny]);
            dfs(nx, ny, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static int calculate(String  s) {
        String[] temp = s.split("");

        int cur = Integer.parseInt(temp[0]);
        for (int i = 2; i < temp.length; i+=2) {
            if (temp[i - 1].equals("+")) {
                cur += Integer.parseInt(temp[i]);
            }
            if (temp[i - 1].equals("-")) {
                cur -= Integer.parseInt(temp[i]);
            }
            if (temp[i - 1].equals("*")) {
                cur *= Integer.parseInt(temp[i]);
            }
        }
        return cur;
    }

    static boolean checkRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}
