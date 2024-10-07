import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int ans = 0;
    static int[][] tempMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tempMap = new int[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 5; i++) {
            int[] chosen = new int[i];
            dfs(0, chosen, i);
        }
        System.out.println(ans);
    }

    static void dfs(int depth, int[] chosen, int target) {
        if (depth == target) {
            tempMap = new int[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, tempMap[i], 0, N);
            }

            for (int i = 0; i < target; i++) {
                int dir = chosen[i];
                noBlank(dir);
                plus(dir);
                noBlank(dir);
            }

            int max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    max = Math.max(max, tempMap[i][j]);
                }
            }
            ans = Math.max(ans, max);
            return;
        }

        for (int dir = 1; dir <= 4; dir++) {
            chosen[depth] = dir;
            dfs(depth + 1, chosen, target);
        }
    }

    static void noBlank(int dir) {
        if (dir == 1) {
            for (int i = 0; i < N; i++) {
                int[] temp = new int[N];
                int index = 0;
                for (int j = 0; j < N; j++) {
                    if (tempMap[j][i] != 0) {
                        temp[index++] = tempMap[j][i];
                    }
                }
                for (int j = 0; j < N; j++) {
                    tempMap[j][i] = temp[j];
                }
            }
        } else if (dir == 2) {
            for (int i = 0; i < N; i++) {
                int[] temp = new int[N];
                int index = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (tempMap[j][i] != 0) {
                        temp[index--] = tempMap[j][i];
                    }
                }
                for (int j = 0; j < N; j++) {
                    tempMap[j][i] = temp[j];
                }
            }
        } else if (dir == 3) {
            for (int i = 0; i < N; i++) {
                int[] temp = new int[N];
                int index = 0;
                for (int j = 0; j < N; j++) {
                    if (tempMap[i][j] != 0) {
                        temp[index++] = tempMap[i][j];
                    }
                }
                for (int j = 0; j < N; j++) {
                    tempMap[i][j] = temp[j];
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                int[] temp = new int[N];
                int index = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (tempMap[i][j] != 0) {
                        temp[index--] = tempMap[i][j];
                    }
                }
                for (int j = 0; j < N; j++) {
                    tempMap[i][j] = temp[j];
                }
            }
        }
    }

    static void plus(int dir) {
        if (dir == 1) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - 1; j++) {
                    if (tempMap[j][i] != 0 && tempMap[j][i] == tempMap[j + 1][i]) {
                        tempMap[j][i] *= 2;
                        tempMap[j + 1][i] = 0;
                        j++;
                    }
                }
            }
        } else if (dir == 2) {
            for (int i = 0; i < N; i++) {
                for (int j = N - 1; j > 0; j--) {
                    if (tempMap[j][i] != 0 && tempMap[j][i] == tempMap[j - 1][i]) {
                        tempMap[j][i] *= 2;
                        tempMap[j - 1][i] = 0;
                        j--;
                    }
                }
            }
        } else if (dir == 3) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - 1; j++) {
                    if (tempMap[i][j] != 0 && tempMap[i][j] == tempMap[i][j + 1]) {
                        tempMap[i][j] *= 2;
                        tempMap[i][j + 1] = 0;
                        j++;
                    }
                }
            }
        } else if (dir == 4) {
            for (int i = 0; i < N; i++) {
                for (int j = N - 1; j > 0; j--) {
                    if (tempMap[i][j] != 0 && tempMap[i][j] == tempMap[i][j - 1]) {
                        tempMap[i][j] *= 2;
                        tempMap[i][j - 1] = 0;
                        j--;
                    }
                }
            }
        }
    }
}