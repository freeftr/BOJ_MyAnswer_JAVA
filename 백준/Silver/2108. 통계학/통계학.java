import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        HashMap<Integer, Integer> cnt = new HashMap<>();

        double sum = 0;

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            arr[i] = x;
            sum += x;
            cnt.merge(x, 1, Integer::sum);
        }

        // 1. 산술평균 (반올림)
        int mean = (int) Math.round(sum / N);

        // 2. 중앙값
        Arrays.sort(arr);
        int median = arr[N / 2];

        // 3. 최빈값
        int maxCnt = 0;
        for (int key : cnt.keySet()) {
            maxCnt = Math.max(maxCnt, cnt.get(key));
        }

        ArrayList<Integer> modes = new ArrayList<>();
        for (int key : cnt.keySet()) {
            if (cnt.get(key) == maxCnt) {
                modes.add(key);
            }
        }

        Collections.sort(modes);
        int mode = (modes.size() == 1) ? modes.get(0) : modes.get(1);

        // 4. 범위
        int range = arr[N - 1] - arr[0];

        StringBuilder sb = new StringBuilder();
        sb.append(mean).append("\n");
        sb.append(median).append("\n");
        sb.append(mode).append("\n");
        sb.append(range);

        System.out.println(sb);
    }
}
