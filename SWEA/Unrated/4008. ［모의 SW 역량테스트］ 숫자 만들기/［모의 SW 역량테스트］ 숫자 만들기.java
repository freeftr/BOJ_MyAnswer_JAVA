import java.util.*;
import java.io.*;

public class Solution {
    static int N;
    static int[] com;
    static int[] arr;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){

            N = Integer.parseInt(br.readLine());
            com = new int[4];
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++){
                com[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            dfs(arr[0], 1);

            System.out.println("#" + t + " " + (max - min));
        }
    }


    public static void dfs(int sum, int depth) {
        if (depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (com[i] > 0) {
                com[i]--;
                switch(i) {
                    case 0:
                        dfs(sum + arr[depth], depth + 1);
                        break;
                    case 1:
                        dfs(sum - arr[depth], depth + 1);
                        break;
                    case 2:
                        dfs(sum * arr[depth], depth + 1);
                        break;
                    case 3:
                        dfs(sum / arr[depth], depth + 1);
                        break;
                }
                com[i]++;
            }
        }
    }
}