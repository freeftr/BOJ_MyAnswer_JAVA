import java.io.*;
import java.util.*;

public class Main {

    static int[][] match = {
            {0,1}, {0,2}, {0,3}, {0,4}, {0,5},
            {1,2}, {1,3}, {1,4}, {1,5},
            {2,3}, {2,4}, {2,5},
            {3,4}, {3,5},
            {4,5}
    };

    static boolean available;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < 4; tc++) {
            int[][] result = new int[6][3];
            int totalCnt = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                    totalCnt += result[i][j];
                }
            }

            if (totalCnt != 30) {
                sb.append("0 ");
                continue;
            }

            available = false;
            dfs(0, result, new int[6][3]);
            sb.append(available ? "1 " : "0 ");
        }

        System.out.println(sb.toString().trim());
    }

    static void dfs(int depth, int[][] result, int[][] score) {
        if (available) return;

        if (depth == 15) {
            if (check(score, result)) {
                available = true;
            }
            return;
        }

        int team1 = match[depth][0];
        int team2 = match[depth][1];

        if (result[team1][0] > score[team1][0] && result[team2][2] > score[team2][2]) {
            score[team1][0]++;
            score[team2][2]++;
            dfs(depth + 1, result, score);
            score[team1][0]--;
            score[team2][2]--;
        }

        if (result[team1][1] > score[team1][1] && result[team2][1] > score[team2][1]) {
            score[team1][1]++;
            score[team2][1]++;
            dfs(depth + 1, result, score);
            score[team1][1]--;
            score[team2][1]--;
        }

        if (result[team1][2] > score[team1][2] && result[team2][0] > score[team2][0]) {
            score[team1][2]++;
            score[team2][0]++;
            dfs(depth + 1, result, score);
            score[team1][2]--;
            score[team2][0]--;
        }
    }

    static boolean check(int[][] score, int[][] result) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (score[i][j] != result[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
