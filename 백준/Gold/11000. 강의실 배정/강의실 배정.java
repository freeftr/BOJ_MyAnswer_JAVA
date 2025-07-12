import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] classes = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            classes[i][0] = Integer.parseInt(st.nextToken());
            classes[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(classes, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        endTime.add(classes[0][1]);

        for (int i = 1; i < N; i++) {
            if (classes[i][0] >= endTime.peek()) {
                endTime.poll();
            }
            endTime.add(classes[i][1]);
        }

        System.out.println(endTime.size());
    }
}
