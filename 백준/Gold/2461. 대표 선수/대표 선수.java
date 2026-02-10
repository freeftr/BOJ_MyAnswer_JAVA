import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ArrayList<int[]> students = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                students.add(new int[]{i, Integer.parseInt(st.nextToken())});
            }
        }

        Collections.sort(students, (a, b) -> a[1] - b[1]);

        int[] teams = new int[N];
        HashSet<Integer> curTeams = new HashSet<>();

        int left = 0;
        int right = -1;
        int answer = Integer.MAX_VALUE;

        while (true) {

            if (curTeams.size() < N) {
                right++;
                if (right == N * M) break;

                int team = students.get(right)[0];
                if (teams[team] == 0) curTeams.add(team);
                teams[team]++;

            } else {
                int diff = students.get(right)[1] - students.get(left)[1];
                answer = Math.min(answer, diff);

                int team = students.get(left)[0];
                teams[team]--;
                if (teams[team] == 0) curTeams.remove(team);
                left++;
            }
        }

        System.out.println(answer);
    }
}
