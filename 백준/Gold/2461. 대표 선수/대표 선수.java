import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int a = Integer.parseInt(st.nextToken());
                list.add(new int[]{i, a});
            }
        }

        Collections.sort(list, (a, b) -> a[1] - b[1]);
        int left = 0;
        int right = M - 1;
        int[] teamCnt = new int[N];

        for (int i = left; i <= right; i++) {
            teamCnt[list.get(i)[0]]++;
        }

        int answer = Integer.MAX_VALUE;

        while (left < N * M && right < N * M) {
            int max = list.get(right)[1];
            int min = list.get(left)[1];

            boolean covered = true;
            for (int t = 0; t < N; t++) {
                if (teamCnt[t] == 0) {
                    covered = false; break;
                }
            }

            if (covered) {
                answer = Math.min(answer, max - min);
                teamCnt[list.get(left)[0]]--;
                left++;
            } else {
                right++;
                if (right >= N * M) break;
                teamCnt[list.get(right)[0]]++;
            }
        }

        System.out.println(answer);
    }
}
